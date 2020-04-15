	package br.com.bradesco.posoTeatro.view.bean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;

@ManagedBean(name = "consultarIngressosBean")
@SessionScoped

public class ConsultarIngressosBean extends PosoBean {
	
	public ConsultarIngressosBean(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos", titulosBread, urlsBread);
	}

	public ConsultarIngressosBean() {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos");
	}
	
	@ManagedProperty(value = "#{consultarIngressosDetalheBean}")
	private IngressosDetalheBean detalhe;
	
	private Ingresso ingresso;

	private String mensagem;

	public String iniciarPagina() {
		setIngresso(new Ingresso());
		return "consultarIngressos";
	}

	public String consultar() {
		Ingresso ingressoRetorno = new IngressoDao().cosultarIngressos(getIngresso());
		PrimeFaces current = PrimeFaces.current();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		if(ingressoRetorno == null) {
			setMensagem(messageBundle.getString("msg_ingressoNaoCadastrado"));
			current.executeScript("PF('dlg1').show();");
			return "";
		}
		else {
			ingresso = ingressoRetorno;
			detalhe.setIngresso(getIngresso());
			return detalhe.iniciarPagina(getTitulosBread(),getUrlsBread());
		}
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

}
