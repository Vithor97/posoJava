	package br.com.bradesco.posoTeatro.view.bean.ingresso;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "alterarIngressosBean")
@SessionScoped

public class AlterarIngressosBean extends PosoBean implements BeanInterface{
	
	private Ingresso ingresso;
	
	private ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
	
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	
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
		super.iniciarPagina("Alterar ingressos", "alterarIngressos", titulosBread, urlsBread);
		return "alterarIngressos";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Alterar ingressos", "alterarIngressos");
		return "alterarIngressos";
	}
	
	public void pesquisarSessoes() {
		setSessoes(new SessaoDao().listarSessoesAtivas());
		
	}

	public ArrayList<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(ArrayList<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
