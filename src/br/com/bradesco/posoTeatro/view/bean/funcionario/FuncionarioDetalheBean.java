	package br.com.bradesco.posoTeatro.view.bean.funcionario;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "consultarFuncionarioDetalheBean")
@SessionScoped

public class FuncionarioDetalheBean extends PosoBean implements BeanInterface{
	
	private Funcionario funcionario;

	private String mensagem;

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

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Funcionario detalhe", "consultarFuncionarioDetalhe", titulosBread, urlsBread);
		return "consultarFuncionarioDetalhe";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Funcionario detalhe", "consultarFuncionarioDetalhe");
		return "consultarFuncionarioDetalhe";
	}

}
