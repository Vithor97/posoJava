package br.com.bradesco.posoTeatro.view.bean.sessao;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "consultaSessaoBean")
@SessionScoped

public class ConsultaSessaoBean extends PosoBean implements BeanInterface{

	public ConsultaSessaoBean(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Consultar Sessão", "consultaSessao", titulosBread, urlsBread);
	}

	public ConsultaSessaoBean() {
		super.iniciarPagina("Consultar Sessão", "consultaSessao");
	}
	
	@ManagedProperty(value = "#{consultaSessaoDetalheBean}")
	private SessaoDetalheBean detalhe;
	

	private Sessao sessao;
	private String mensagem;
	
		
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Sessao getSessao() {
		return sessao;
	}
	
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	
	public String iniciarPagina() {
		setSessao(new Sessao());
		return "consultaSessao";
	}

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		return iniciarPagina();
	}
	
	public String consultar() {
		Sessao sessaoRetorno = new SessaoDao().consultaSessao(getSessao());
		PrimeFaces current = PrimeFaces.current();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		if(sessaoRetorno == null) {
			setMensagem(messageBundle.getString("msg_sessaoNaoCadastrada"));
			current.executeScript("PF('dlg1').show();");
			return "";
		}
		else {
			sessao = sessaoRetorno;
			detalhe.setSessao(getSessao());
			detalhe.setTelaAnterior(this);
			return detalhe.iniciarPagina(getTitulosBread(),getUrlsBread());
		}
	}
	public SessaoDetalheBean getDetalhe() {
		return detalhe;
	}
	
	public void setDetalhe(SessaoDetalheBean detalhe) {
		this.detalhe = detalhe;
	}



	
		

	
	}
	

