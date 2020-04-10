package br.com.bradesco.posoTeatro.view.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.model.Pessoa;


@ManagedBean(name="consultarPessoaDetalheBean")
@SessionScoped
public class DetalharPessoaBean extends PosoBean implements BeanInterface {
	
	private Pessoa pessoa;
	private String mensagem;
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Pessoas Detalhe", "detalhePessoas", titulosBread, urlsBread);
		return "detalhePessoas";
	}
	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Pessoas Detalhe", "detalhePessoas");
		return "detalhePessoas";
	}
	
	

}
