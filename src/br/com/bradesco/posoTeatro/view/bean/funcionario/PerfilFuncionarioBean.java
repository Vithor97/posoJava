package br.com.bradesco.posoTeatro.view.bean.funcionario;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "perfilFuncionarioBean")
@SessionScoped

public class PerfilFuncionarioBean extends PosoBean implements BeanInterface{
	
	private Funcionario funcionario;
	private String senhaAtual;
	private String novaSenha;
	private String confirmarNovaSenha;
	private String mensagem;
	
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Perfil", "perfil", titulosBread, urlsBread);
		FacesContext context = FacesContext.getCurrentInstance();
		setFuncionario((Funcionario) context.getExternalContext().getSessionMap().get("funcionarioLogado"));
		return "perfil";
	}
	
	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Perfil", "perfil");
		FacesContext context = FacesContext.getCurrentInstance();
		setFuncionario((Funcionario) context.getExternalContext().getSessionMap().get("funcionarioLogado"));
		return "perfil";
	}
	
	public void alterarSenha() {
		PrimeFaces current = PrimeFaces.current();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		setMensagem(messageBundle.getString("msg_perfil_senhaAlterada"));
		current.executeScript("PF('dlg1').show();");
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarNovaSenha() {
		return confirmarNovaSenha;
	}

	public void setConfirmarNovaSenha(String confirmarNovaSenha) {
		this.confirmarNovaSenha = confirmarNovaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
