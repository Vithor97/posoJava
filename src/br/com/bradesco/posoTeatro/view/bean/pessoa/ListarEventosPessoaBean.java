package br.com.bradesco.posoTeatro.view.bean.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "listarEventosPessoaBean")
@SessionScoped


public class ListarEventosPessoaBean extends PosoBean implements BeanInterface{

	private Pessoa pessoa;
	private Evento evento;
	private String mensagem;
	private PessoaDao dao;
	private ArrayList<Evento> eventos;
	private String pesquisaEvento = "";
	
	@ManagedProperty(value = "#{consultarPessoaDetalheBean}")
	private DetalharPessoaBean detalhePessoa;
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public PessoaDao getDao() {
		return dao;
	}

	public void setDao(PessoaDao dao) {
		this.dao = dao;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getPesquisaEvento() {
		return pesquisaEvento;
	}

	public void setPesquisaEvento(String pesquisaEvento) {
		this.pesquisaEvento = pesquisaEvento;
	}

	public DetalharPessoaBean getDetalhePessoa() {
		return detalhePessoa;
	}

	public void setDetalhePessoa(DetalharPessoaBean detalhePessoa) {
		this.detalhePessoa = detalhePessoa;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String iniciarPagina() {
		// TODO Auto-generated method stub
		return null;
	}

}
