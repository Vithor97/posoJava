	package br.com.bradesco.posoTeatro.view.bean.ingresso;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "consultarIngressosBean")
@SessionScoped

public class ConsultarIngressosBean extends PosoBean implements BeanInterface {
	
	public ConsultarIngressosBean(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos", titulosBread, urlsBread);
	}

	public ConsultarIngressosBean() {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos");
	}
	
	@ManagedProperty(value = "#{ingressosDetalheBean}")
	private IngressosDetalheBean detalhe;
	
	@ManagedProperty(value = "#{alterarIngressosBean}")
	private AlterarIngressosBean alterar;
	
	private Ingresso ingresso;
	
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();

	private String mensagem;
	
	public String iniciarPagina() {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos");
		setIngressos(new IngressoDao().listarIngressosAtivos());
		return "consultarIngressos";
	}
	
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos", titulosBread, urlsBread);
		setIngressos(new IngressoDao().listarIngressosAtivos());
		return "consultarIngressos";
	}

	public String consultar(Ingresso ingressoEntrada) {
		detalhe.setIngresso(ingressoEntrada);
		detalhe.setTelaAnterior(this);
		return detalhe.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
	
	public String alterar(Ingresso ingressoEntrada) {
		alterar.setIngresso(ingressoEntrada);
		alterar.setTelaAnterior(this);
		return alterar.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
	
	public void excluir(Ingresso ingressoEntrada) {
		boolean exclusao = new IngressoDao().reembolsoIngresso(ingressoEntrada);
		PrimeFaces current = PrimeFaces.current();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		if(exclusao) {
			setMensagem(messageBundle.getString("msg_ingressos_exclusaoSucesso"));
		}
		else {
			setMensagem(messageBundle.getString("msg_ingressos_exlusaoErro"));
		}
		current.executeScript("PF('dlg1').show();");
	}
	
	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public IngressosDetalheBean getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(IngressosDetalheBean detalhe) {
		this.detalhe = detalhe;
	}

	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public AlterarIngressosBean getAlterar() {
		return alterar;
	}

	public void setAlterar(AlterarIngressosBean alterar) {
		this.alterar = alterar;
	}



}
