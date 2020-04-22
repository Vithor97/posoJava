package br.com.bradesco.posoTeatro.view.bean.evento;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name="alterarEventoBean")
@SessionScoped

public class AlterarEventoBean extends PosoBean implements Serializable,  BeanInterface {
	
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
	
	
	
	public String altera() {
		int linhasAfetadas;
		System.out.println(getMensagem());
		if(validarDados()) {
			linhasAfetadas	= new EventoDao().alterar(getEvento());
			if(linhasAfetadas != 0) {
				setMensagem("Alteração realizada com sucesso");					
				
				FacesContext.getCurrentInstance().addMessage(
				        null, new FacesMessage(getMensagem()));
				 
				  FacesContext.getCurrentInstance().getExternalContext()
				      .getFlash().setKeepMessages(true);
							
	
				return "listarEventos?faces-redirect=true";			
			}
			else {
				setMensagem("Alteração não realizada");
				return iniciarPagina();
			}
		}
		else {
			
			return iniciarPagina();
		}
			
		
	}
	
		public boolean validarDados() {
			
			return true;
		}
		
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Alterar Evento", "alterarEvento");
		return "alterarEvento";
	}
	
	
}
