package br.com.bradesco.posoTeatro.view.bean.evento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

//Vitor esteve aqui

@ManagedBean(name = "listarEventosBean")
@SessionScoped

public class ListarEventosBean extends PosoBean implements BeanInterface{

	@ManagedProperty("#{detalheEventoBean}")
	private DetalheEventoBean detalheEventoBean;
	
	private ArrayList<Evento> eventos;
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public void setDetalheEventoBean(DetalheEventoBean detalheEventoBean) {
		this.detalheEventoBean = detalheEventoBean;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Listar Eventos", "listarEventos", titulosBread, urlsBread);
		setEventos(new EventoDao().listar());
		return "listarEventos";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Listar Eventos", "listarEventos");
		setEventos(new EventoDao().listar());
		return "listarEventos";
	}
	
	public String detalhar(Evento evento) {
		detalheEventoBean.setEvento(new EventoDao().consultar(evento));
		detalheEventoBean.setTelaAnterior(this);
		return detalheEventoBean.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
}
