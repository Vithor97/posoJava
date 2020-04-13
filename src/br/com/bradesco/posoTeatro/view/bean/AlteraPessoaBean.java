package br.com.bradesco.posoTeatro.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.model.Pessoa;

@ManagedBean(name="alteraPessoaBean")
@SessionScoped
public class AlteraPessoaBean extends PosoBean implements Serializable,  BeanInterface {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public String teste() {
		setMensagem("Teste");
		return getMensagem();
	}
	
	
	public void altera() {
		
		System.out.println(getMensagem());
		if(validarDados()) {
			setMensagem("Dados validados");
			System.out.println(getMensagem());
		}else {
			setMensagem("Não valida nada");
		}
			
		
	}
	
	public boolean validarDados() {
		if (getPessoa().getNome().equals(null) || getPessoa().getNome().isEmpty()) {
			setMensagem("O preenchimento do campo nome é obrigatório.");
			return false;
		}

		if (getPessoa().getCpf().equals(null) || getPessoa().getCpfFormatado().length() < 11) {
			setMensagem("O preenchimento do campo cpf é obrigatório.");
			return false;
		} else if (!getPessoa().cpfValido()) {
			setMensagem("O cpf digitado é inválido.");
			return false;
		}

		if (getPessoa().getDataNascimento() == null || !getPessoa().nascimentoValido()) {
			setMensagem(
					"O preenchimento do campo data de nascimento é obrigatório e a idade deve ser superior a 18 anos.");
			return false;
		}

		if (getPessoa().getTelefone().equals(null) || getPessoa().getTelefoneFormatado().isEmpty()) {
			setMensagem("O preenchimento do campo telefone é obrigatório");
			return false;
		}

		if (getPessoa().getEmail().equals(null)) {
			setMensagem("O preenchimento do campo email é obrigatório");
			return false;
		} else if (!getPessoa().emailValido()) {
			setMensagem("O email digitado é inválido.");
			return false;
		}
		return true;
	}
	
	
	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Altera Pessoa", "alteraPessoas");
		return "alteraPessoas";
	}
	
	

}
