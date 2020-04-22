package br.com.bradesco.posoTeatro.view.bean.evento;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "listarEventosBean")
@SessionScoped

public class ListarEventosBean extends PosoBean implements BeanInterface{

	@ManagedProperty("#{detalheEventoBean}")
	private DetalheEventoBean detalheEventoBean;
	
	private String mensagem;
	private ArrayList<Evento> eventos;
	private String pesquisaEvento = "";
	
	public void setDetalheEventoBean(DetalheEventoBean detalheEventoBean) {
		this.detalheEventoBean = detalheEventoBean;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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
	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Listar Eventos", "listarEventos", titulosBread, urlsBread);
		setEventos(new EventoDao().listar(getPesquisaEvento()));
		return "listarEventos";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Listar Eventos", "listarEventos");
		setEventos(new EventoDao().listar(getPesquisaEvento()));
		return "listarEventos";
	}
	

	
	public void pesquisarEventos() {
		setEventos(new EventoDao().listar(getPesquisaEvento()));
	}
	
 	public String detalhar(Evento evento) {
		detalheEventoBean.setEvento(new EventoDao().consultar(evento));
		detalheEventoBean.setTelaAnterior(this);
		return detalheEventoBean.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
}
