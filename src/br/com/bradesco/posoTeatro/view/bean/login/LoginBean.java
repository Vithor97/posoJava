package br.com.bradesco.posoTeatro.view.bean.login;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.LoginDao;
import br.com.bradesco.posoTeatro.model.Funcionario;

@ManagedBean(name = "loginBean")
@ViewScoped

public class LoginBean {
	
	private Funcionario funcionario = new Funcionario();
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public String validaLogin() {
		setFuncionario(new LoginDao().validarLogin(getFuncionario()));
		
		PrimeFaces current = PrimeFaces.current();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		
		if(getFuncionario().getCpf() != null) {
			facesContext.getExternalContext().getSessionMap().put("funcionarioLogado", this.funcionario);
			return "index";
		}
		else {
			setMensagem(messageBundle.getString("msg_login_loginInvalido"));
			current.executeScript("PF('dlg1').show();");
			return "";
		}
	}

	public String desconectar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getSessionMap().remove("funcionarioLogado");
		return "login";
	}
}

