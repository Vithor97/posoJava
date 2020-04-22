	package br.com.bradesco.posoTeatro.view.bean.funcionario;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.FuncionarioDao;
import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "consultarFuncionarioBean")
@SessionScoped

public class ConsultarFuncionarioBean extends PosoBean {
	
	public ConsultarFuncionarioBean(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar funcionários", "consultarFuncionario", titulosBread, urlsBread);
	}

	public ConsultarFuncionarioBean() {
		super.iniciarPagina("Consultar funcionários", "consultarFuncionario");
	}
	
	@ManagedProperty(value = "#{consultarFuncionarioDetalheBean}")
	private FuncionarioDetalheBean detalhe;
	
	private Funcionario funcionario;

	private String mensagem;

	public String iniciarPagina() {
		setFuncionario(new Funcionario());
		return "consultarFuncionario";
	}

	public String consultar() {
		Funcionario funcionarioRetorno = new FuncionarioDao().consultarFuncionario(getFuncionario());
		PrimeFaces current = PrimeFaces.current();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		if(funcionarioRetorno == null) {
			setMensagem(messageBundle.getString("msg_funcionarioInexistente"));
			current.executeScript("PF('dlg1').show();");
			return "";
		}
		else {
			funcionario = funcionarioRetorno;
			detalhe.setFuncionario(getFuncionario());
			return detalhe.iniciarPagina(getTitulosBread(),getUrlsBread());
		}
		
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public FuncionarioDetalheBean getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(FuncionarioDetalheBean detalhe) {
		this.detalhe = detalhe;
	}

}
