package br.com.bradesco.posoTeatro.view.bean;

import java.text.ParseException;
import java.util.List;

import javax.faces.bean.ManagedBean;
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
	
	public void consultaPessoa() {
		System.out.println("cpf do individuo na tela  " + pessoa.getCpfFormatado());
		if(validarDados()) {
			pessoa =  new PessoaDao().consultar(pessoa.getCpfFormatado());
			
			setDtNasc(pessoa.getDataNascimentoString());
			pessoa.setTelefone(formatString(pessoa.getTelefone(), "(##) ####-####"));
//			setTelNumber(Integer.parseInt(pessoa.getTelefone()));
			
//			System.out.println(pessoa);
//			pessoa.setDataNascimento(new DataUtil().converteData(pessoa.getDataNascimento(), "dd/MM/yyyy"));
			
			if(pessoa.getNome() != null) {
				setMensagem("Consulta realizada com sucesso");				
			}else {
				setMensagem("Não existe essa pessoa");
			}
		}
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
	
	
}
