	package br.com.bradesco.posoTeatro.view.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.model.filter.GlobalFilterConstraint;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;

@ManagedBean(name = "consultarIngressosDetalheBean")
@SessionScoped

public class IngressosDetalheBean extends PosoBean implements BeanInterface{
	
	private Ingresso ingresso;

	private String mensagem;

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

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Ingressos detalhe", "consultarIngressosDetalhe", titulosBread, urlsBread);
		return "consultarIngressosDetalhe";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Ingressos detalhe", "consultarIngressosDetalhe");
		return "consultarIngressosDetalhe";
	}

}