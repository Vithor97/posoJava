package br.com.bradesco.posoTeatro.view.bean.pessoa;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarPessoasBean")
@SessionScoped

public class CadastrarPessoasBean extends PosoBean implements BeanInterface {

	private Pessoa pessoa;
	private String mensagem;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Pessoas", "cadastrarPessoas", titulosBread, urlsBread);
		setPessoa(new Pessoa());
		return "cadastrarPessoas";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Pessoas", "cadastrarPessoas");
		setPessoa(new Pessoa());
		return "cadastrarPessoas";
	}

	public void cadastrar() {
		if (validarDados())
			setMensagem(new PessoaDao().cadastrar(getPessoa()));
		showDialog();
	}

	public boolean validarDados() {
		if (getPessoa().getNome().equals(null) || getPessoa().getNome().isEmpty()) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_nome"));
			return false;
		}

		if (getPessoa().getCpf().equals(null) || getPessoa().getCpfFormatado().length() < 11) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_cpf"));
			return false;
		} else if (!getPessoa().cpfValido()) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_cpfInvalido"));
			return false;
		}

		if (getPessoa().getDataNascimento() == null || !getPessoa().nascimentoValido()) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_data"));
			return false;
		}

		if (getPessoa().getTelefone().equals(null) || getPessoa().getTelefoneFormatado().isEmpty()) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_telefone"));
			return false;
		}

		if (getPessoa().getEmail().equals(null)) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_email"));
			return false;
		} else if (!getPessoa().emailValido()) {
			setMensagem(getMessageProperties("msg_cadastrarPessoas_erro_emailInvalido"));
			setMensagem("");
			return false;
		}
		return true;
	}
}
