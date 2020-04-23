package br.com.bradesco.posoTeatro.view.bean.pessoa;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;


@ManagedBean(name="consultarPessoaDetalheBean")
@SessionScoped
public class DetalharPessoaBean extends PosoBean implements BeanInterface {
	
	private Pessoa pessoa;
	private String mensagem;
	private PessoaDao dao;
	
//	@Inject
//	@Named("consultarPessoasBean")
//	private ConsultarPessoasBean consultaPessoaBean;
//	
//	public ConsultarPessoasBean getConsultaPessoaBean() {
//		return consultaPessoaBean;
//	}
//
//
//	public void setConsultaPessoaBean(ConsultarPessoasBean consultaPessoaBean) {
//		this.consultaPessoaBean = consultaPessoaBean;
//	}

	@ManagedProperty(value = "#{alteraPessoaBean}")
	private AlteraPessoaBean alteraPessoaBean;
	
	
	public DetalharPessoaBean() {
		dao = new PessoaDao();
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
	
	public AlteraPessoaBean getAlteraPessoaBean() {
		return alteraPessoaBean;
	}
	public void setAlteraPessoaBean(AlteraPessoaBean alteraPessoaBean) {
		this.alteraPessoaBean = alteraPessoaBean;
	}
	public PessoaDao getDao() {
		return dao;
	}


	public void setDao(PessoaDao dao) {
		this.dao = dao;
	}


	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Pessoas Detalhe", "detalhePessoas", titulosBread, urlsBread);
		return "detalhePessoas";
	}
	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Pessoas Detalhe", "detalhePessoas");
		return "detalhePessoas";
	}
	
	public String alteracao() {
		alteraPessoaBean.setPessoa(pessoa);
		return alteraPessoaBean.iniciarPagina();
	}
	
//	  public void readBookStudent() {
//	        FacesContext context = FacesContext.getCurrentInstance();
//	        Application application = context.getApplication();
//	        ValueBinding binding = application.createValueBinding("#{otherBean}");
//	        om.jwt.jsf.BookBean book = (com.jwt.jsf.BookBean)binding.getValue(context);
//	        book.readBook();
//	    }
	
	public String excluir() {
		System.out.println("Dentro do metodo de exclusão");
		int RowAffected = 0;
		
		RowAffected = dao.deletaPessoa(pessoa);
		pessoa = dao.consultar(pessoa);
		if(RowAffected !=0) {
			if(pessoa.getSituacaoPessoa() !=1) {				
				setMensagem("Exclusão realizada");
				return "consultarPessoas?faces-redirect=true";
			}
			else {
				return iniciarPagina();
			}
//			FacesContext context = FacesContext.getCurrentInstance();
//	        Application application = context.getApplication();
//	        ValueBinding binding = application.createValueBinding("#{consultarPessoaDetalheBean}");
//	        ConsultarPessoasBean consulta = (ConsultarPessoasBean)binding.getValue(context);
//			
//	        return consulta.iniciarPagina()+"?faces-redirect=true";
			//setPessoa(dao.consultar(pessoa));
		}
		else {
			setMensagem("Exclusão não realizada");
			return iniciarPagina();
		}
		
	}
	
	

}
