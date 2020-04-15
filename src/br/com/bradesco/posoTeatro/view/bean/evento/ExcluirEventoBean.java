package br.com.bradesco.posoTeatro.view.bean.evento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name="excluirEventoBean")
@SessionScoped

public class ExcluirEventoBean extends PosoBean implements Serializable,  BeanInterface{
	
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private String mensagem;

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

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Excluir Evento", "excluirEvento");
		return "excluirEvento";
	}

}
