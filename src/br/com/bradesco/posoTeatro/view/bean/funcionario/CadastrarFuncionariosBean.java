package br.com.bradesco.posoTeatro.view.bean.funcionario;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.FuncionarioDao;
import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarFuncionariosBean")
@SessionScoped

public class CadastrarFuncionariosBean extends PosoBean implements BeanInterface {

	private Funcionario funcionario;

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

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Funcionarios", "cadastrarFuncionarios", titulosBread, urlsBread);
		setFuncionario(new Funcionario());
		return "cadastrarFuncionario";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Funcionario", "cadastrarFuncionario");
		setFuncionario(new Funcionario());
		return "cadastrarFuncionario";
	}
	
	

	public void cadastrar() {
		if (validarDados())
			setMensagem(new FuncionarioDao().cadastrar(getFuncionario()));
	}

	public boolean validarDados() {
		if (getFuncionario().getNome().equals(null) || getFuncionario().getNome().isEmpty()) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_nome"));
			return false;
		}

		if (getFuncionario().getCpf().equals(null) || getFuncionario().getCpfFormatado().length() < 11) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_cpf"));
			return false;
		} else if (!getFuncionario().cpfValido()) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_cpfInvalido"));
			return false;
		}

		if (getFuncionario().getDataNascimento() == null || !getFuncionario().nascimentoValido()) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_data"));
			return false;
		}

		if (getFuncionario().getTelefone().equals(null) || getFuncionario().getTelefoneFormatado().isEmpty()) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_telefone"));
			return false;
		}

		if (getFuncionario().getEmail().equals(null)) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_email"));
			return false;
		} else if (!getFuncionario().emailValido()) {
			setMensagem(getMessageProperties("msg_cadastrarFuncionarios_erro_emailInvalido"));
			return false;
		}
		return true;
	}
}
