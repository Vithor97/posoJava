package br.com.bradesco.posoTeatro.view.bean.pessoa;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name="alteraPessoaBean")
@SessionScoped
public class AlteraPessoaBean extends PosoBean implements Serializable,  BeanInterface {
	
	
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
	
	
	public String altera() {
		int linhasAfetadas;
		System.out.println(getMensagem());
		if(validarDados()) {
			linhasAfetadas	= new PessoaDao().alterar(getPessoa());
			if(linhasAfetadas != 0) {
				setMensagem("Altera��o realizada com sucesso");					
				
				FacesContext.getCurrentInstance().addMessage(
				        null, new FacesMessage(getMensagem()));
				 
				  FacesContext.getCurrentInstance().getExternalContext()
				      .getFlash().setKeepMessages(true);
							
	
				return "consultarPessoas?faces-redirect=true";			
			}
			else {
				setMensagem("Altera��o n�o realizada");
				return iniciarPagina();
			}
		}
		else {
			
			return iniciarPagina();
		}
			
		
	}
	
	public boolean validarDados() {
		if (getPessoa().getNome().equals(null) || getPessoa().getNome().isEmpty()) {
			setMensagem("O preenchimento do campo nome � obrigat�rio.");
			return false;
		}

		if (getPessoa().getCpf().equals(null) || getPessoa().getCpfFormatado().length() < 11) {
			setMensagem("O preenchimento do campo cpf � obrigat�rio.");
			return false;
		} else if (!getPessoa().cpfValido()) {
			setMensagem("O cpf digitado � inv�lido.");
			return false;
		}

		if (getPessoa().getDataNascimento() == null || !getPessoa().nascimentoValido()) {
			setMensagem(
					"O preenchimento do campo data de nascimento � obrigat�rio e a idade deve ser superior a 18 anos.");
			return false;
		}

		if (getPessoa().getTelefone().equals(null) || getPessoa().getTelefoneFormatado().isEmpty()) {
			setMensagem("O preenchimento do campo telefone � obrigat�rio");
			return false;
		}

		if (getPessoa().getEmail() == "") {
			setMensagem("O preenchimento do campo email � obrigat�rio");
			return false;
		} else if (!getPessoa().emailValido()) {
			setMensagem("O email digitado � inv�lido.");
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
