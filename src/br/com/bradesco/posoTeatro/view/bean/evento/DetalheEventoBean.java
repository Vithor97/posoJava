package br.com.bradesco.posoTeatro.view.bean.evento;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "detalheEventoBean")
@SessionScoped

public class DetalheEventoBean extends PosoBean implements BeanInterface{

	private Evento evento;
	private List<Pessoa> pessoas;

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Detalhe Evento", "detalheEvento", titulosBread, urlsBread);
		setEvento(new EventoDao().consultar(evento));
		setPessoas(new EventoDao().listarPessoas(getEvento()));
		return "detalheEvento";}

	public String iniciarPagina() {
		super.iniciarPagina("Detalhe Evento", "detalheEvento");
		setEvento(new EventoDao().consultar(evento));
		setPessoas(new EventoDao().listarPessoas(getEvento()));
		return "detalheEvento";
	}


}
