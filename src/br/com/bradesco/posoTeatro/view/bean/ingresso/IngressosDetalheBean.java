	package br.com.bradesco.posoTeatro.view.bean.ingresso;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "ingressosDetalheBean")
@SessionScoped

public class IngressosDetalheBean extends PosoBean implements BeanInterface{
	
	private Ingresso ingresso;
	
	private String mensagem;
	@ManagedProperty(value = "#{reembolsoIngressoBean}")
	private ReembolsoIngressoBean excluir;
	
	public ReembolsoIngressoBean getExcluir() {
		return excluir;
	}
	
	public void setExcluir(ReembolsoIngressoBean excluir) {
		this.excluir = excluir;
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
