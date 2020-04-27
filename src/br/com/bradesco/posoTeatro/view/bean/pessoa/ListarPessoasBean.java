package br.com.bradesco.posoTeatro.view.bean.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "listarPessoasBean")
@SessionScoped
public class ListarPessoasBean extends PosoBean implements BeanInterface{
	
	
	private Pessoa pessoa;
	private String mensagem;
	private PessoaDao dao;
	private ArrayList<Pessoa> pessoas;
	private String pesquisaPessoa = "";
	
	
	@ManagedProperty(value = "#{consultarPessoaDetalheBean}")
	private DetalharPessoaBean detalhePessoa;
	
	@ManagedProperty(value = "#{alteraPessoaBean}")
	private AlteraPessoaBean alteraPessoaBean;
	
	
	public void setDetalhePessoa(DetalharPessoaBean detalhePessoa) {
		this.detalhePessoa = detalhePessoa;
	}
	
	public void setAlteraPessoaBean(AlteraPessoaBean alteraPessoaBean) {
		this.alteraPessoaBean = alteraPessoaBean;
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
	public PessoaDao getDao() {
		return dao;
	}
	public void setDao(PessoaDao dao) {
		this.dao = dao;
	}
	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public String getPesquisaPessoa() {
		return pesquisaPessoa;
	}
	public void setPesquisaPessoa(String pesquisaPessoa) {
		this.pesquisaPessoa = pesquisaPessoa;
	}
	
	public void load() {
		setPessoas(new PessoaDao().listarPessoas(getPesquisaPessoa()));
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Listar Pessoas", "listarPessoas", titulosBread, urlsBread);
		setPessoas(new PessoaDao().listarPessoas(getPesquisaPessoa()));
		return "listarPessoas";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Listar Pessoas", "listarPessoas");
		setPessoas(new PessoaDao().listarPessoas(getPesquisaPessoa()));
		return "listarPessoas";
	}
	
	public void pesquisarPessoas() {
		setPessoas(new PessoaDao().listarPessoas(getPesquisaPessoa()));
	}
	
	public String detalhaPessoa(Pessoa pessoa) {
		detalhePessoa.setPessoa(new PessoaDao().consultar(pessoa));
		detalhePessoa.setTelaAnterior(this);
		return detalhePessoa.iniciarPagina();
	}
	
	public String alteracao(Pessoa pessoa) {

			alteraPessoaBean.setPessoa(new PessoaDao().consultar(pessoa));
			alteraPessoaBean.setTelaAnterior(this);
			return alteraPessoaBean.iniciarPagina();

		
	}
	
	

}
