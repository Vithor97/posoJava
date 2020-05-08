	package br.com.bradesco.posoTeatro.view.bean.ingresso;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.Poltrona;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.posoUtil.enums.TipoPoltrona;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "alterarIngressosBean")
@SessionScoped

public class AlterarIngressosBean extends PosoBean implements BeanInterface{
	
	private Ingresso ingresso;
	
	private Poltrona poltrona;
	
	private ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
	
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	private ArrayList<Poltrona> poltronas = new ArrayList<Poltrona>();
	
	private TipoPoltrona[] tiposPoltronas;
	
	private String mensagem;

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Alterar ingressos", "alterarIngressos", titulosBread, urlsBread);
		pesquisarPoltronas(getIngresso().getSessao());
		setPoltrona(getIngresso().getPoltrona());
		setTiposPoltronas(TipoPoltrona.getTiposPoltrona());
		return "alterarIngressos";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Alterar ingressos", "alterarIngressos");
		pesquisarPoltronas(getIngresso().getSessao());
		setPoltrona(getIngresso().getPoltrona());
		setTiposPoltronas(TipoPoltrona.getTiposPoltrona());
		return "alterarIngressos";
	}
	
	public void pesquisarSessoes() {
		setSessoes(new SessaoDao().listarSessoesAtivas());
	}
	
	public void pesquisarPessoas() {
		setPessoas(new PessoaDao().listarPessoas(""));
	}
	
	public void pesquisarPoltronas(Sessao sessaoEntrada){
		getIngresso().setSessao(sessaoEntrada);
		setPoltronas((ArrayList<Poltrona>) new IngressoDao().verificarPoltronas(sessaoEntrada));
	}
	
	public void alterar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		boolean retorno = new IngressoDao().alterarIngresso(getIngresso());
		if(retorno) {
			setMensagem(messageBundle.getString("msg_ingressos_alteracaoSucesso"));
		}
		else {
			setMensagem(messageBundle.getString("msg_ingressos_alteracaoErro"));
		}
	}

	public ArrayList<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(ArrayList<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public ArrayList<Poltrona> getPoltronas() {
		return poltronas;
	}

	public void setPoltronas(ArrayList<Poltrona> poltronas) {
		this.poltronas = poltronas;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(Poltrona poltrona) {
		this.poltrona = poltrona;
	}

	public TipoPoltrona[] getTiposPoltronas() {
		return tiposPoltronas;
	}

	public void setTiposPoltronas(TipoPoltrona[] tiposPoltronas) {
		this.tiposPoltronas = tiposPoltronas;
	}
}
