package br.com.bradesco.posoTeatro.view.bean.sessao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "listarSessoesBean")
@SessionScoped
public class ListarSessoesBean extends PosoBean implements BeanInterface {

	private List<Sessao> sessoes;
	private String mensagem;
	
	@ManagedProperty(value = "#{consultaSessaoDetalheBean}")
	private SessaoDetalheBean detalhe;
	
	@ManagedProperty(value = "#{excluirSessaoBean}")
	private ExcluirSessaoBean excluir;
	
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Listar Sessões", "listarSessoes", titulosBread, urlsBread);
		this.sessoes = new ArrayList<Sessao>();
		this.listar();
		return "listarSessoes";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Listar Sessões", "listarSessoes");
		this.sessoes = new ArrayList<Sessao>();
		this.listar();
		return "listarSessoes";
	}
	
	public void listar() {
		this.sessoes = new SessaoDao().listarSessoesAtivas();
	}
	
	public String detalhar(Sessao sessao) {
		this.detalhe.setSessao(sessao);
		this.detalhe.setTelaAnterior(this);
		return this.detalhe.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
	
	public String alterar(Sessao sessao) {
		this.detalhe.setSessao(sessao);
		this.detalhe.setTelaAnterior(this);
		this.detalhe.setDesabilitado(false);
		return this.detalhe.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
	
	public void excluir(Sessao sessao) {
		excluir.setSessao(sessao);
		excluir.excluir();
		this.sessoes.remove(sessao);
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public SessaoDetalheBean getDetalhe() {
		return detalhe;
	}
	
	public void setDetalhe(SessaoDetalheBean detalhe) {
		this.detalhe = detalhe;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ExcluirSessaoBean getExcluir() {
		return excluir;
	}

	public void setExcluir(ExcluirSessaoBean excluir) {
		this.excluir = excluir;
	}

}
