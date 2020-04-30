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
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "consultarIngressosBean")
@SessionScoped

public class ConsultarIngressosBean extends PosoBean {
	
	public ConsultarIngressosBean(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos", titulosBread, urlsBread);
	}

	public ConsultarIngressosBean() {
		super.iniciarPagina("Consultar ingressos", "consultarIngressos");
	}
	
	@ManagedProperty(value = "#{ingressosDetalheBean}")
	private IngressosDetalheBean detalhe;
	
	private Ingresso ingresso;
	
	private ArrayList<Ingresso> ingressos;

	private String mensagem;

	public String iniciarPagina() {
		setIngressos(new IngressoDao().listarIngressosAtivos());
		return "consultarIngressos";
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

}
