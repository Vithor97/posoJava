package br.com.bradesco.posoTeatro.view.bean.ingresso;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "reembolsoIngressoBean")
@SessionScoped
public class ReembolsoIngressoBean extends PosoBean {

	private Ingresso ingresso;
	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
	
	public void excluir() {
		int linhasAfetadas;
		
			linhasAfetadas = new IngressoDao().reembolsoIngresso(getIngresso());
			if (linhasAfetadas != 0) {

				setMensagem("Reembolso realizado com sucesso");

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getMensagem()));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			} else {
				setMensagem("Reembolso não realizado, 0 linhas afetadas!");
			}
			
		}

	}

