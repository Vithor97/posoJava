
package br.com.bradesco.posoTeatro.view.bean;

import java.text.ParseException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.swing.text.MaskFormatter;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Pessoa;

@ManagedBean(name = "consultarPessoasBean")
@SessionScoped
public class ConsultarPessoasBean extends PosoBean implements BeanInterface{

	private Pessoa pessoa;
	private String DtNasc;
	private String mensagem;
	public 	PessoaDao dao;
	private Integer telNumber;
	
	@ManagedProperty(value = "#{consultarPessoaDetalheBean}")
	private DetalharPessoaBean detalhePessoa;
	
	public Integer getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(Integer telNumber) {
		this.telNumber = telNumber;
	}

	
	
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
	
	public String consultaPessoa() {
		System.out.println("cpf do individuo na tela  " + pessoa.getCpfFormatado());
		if(validarDados()) {
			pessoa =  new PessoaDao().consultar(pessoa);	
			
			if(pessoa.getNome() != null) {
				setDtNasc(pessoa.getDataNascimentoString());
				pessoa.setTelefone(formatString(pessoa.getTelefone(), "(##) ####-####"));
				detalhePessoa.setMensagem("Consulta realixada com sucesso");
				detalhePessoa.setPessoa(pessoa);
				return detalhePessoa.iniciarPagina();
				
			}
			else {
				setMensagem("Não existe essa pessoa");
				return "";
			}
			
		}
		
		return iniciarPagina();
			
	}
	

	
	public boolean validarDados() {
	

		if (getPessoa().getCpf().equals(null) || getPessoa().getCpfFormatado().length() < 11) {
			setMensagem("O preenchimento do campo cpf é obrigatório.");
			return false;
		} else if (!getPessoa().cpfValido()) {
			setMensagem("O cpf digitado é inválido.");
			return false;
		}

		
		return true;
	}

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar Pessoa", "consultarPessoas", titulosBread, urlsBread);
		setPessoa(new Pessoa());
		return "consultarPessoas";
		
	}
	
	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Consultar Pessoa", "consultarPessoas");
		setPessoa(new Pessoa());
		return "consultarPessoas";
	}

	public String getDtNasc() {
		return DtNasc;
	}

	public void setDtNasc(String dtNasc) {
		DtNasc = dtNasc;
	}
	
	 public static String formatString(String value, String pattern) {
	        MaskFormatter mf;
	        try {
	            mf = new MaskFormatter(pattern);
	            mf.setValueContainsLiteralCharacters(false);
	            return mf.valueToString(value);
	        } catch (ParseException ex) {
	            return value;
	        }
	    }

	public DetalharPessoaBean getDetalhePessoa() {
		return detalhePessoa;
	}

	public void setDetalhePessoa(DetalharPessoaBean detalhePessoa) {
		this.detalhePessoa = detalhePessoa;
	}
	
	
}