package br.com.bradesco.posoTeatro.view.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;


@ManagedBean(name = "cadastrarIngressosBean")
@SessionScoped

public class CadastrarIngressosBean extends PosoBean {

	private Ingresso ingresso;
	
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

	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Ingressos", "cadastrarIngressos", titulosBread, urlsBread);
		setIngresso(new Ingresso());
		return "cadastrarIngressos";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Ingressos", "cadastrarIngressos");
		setIngresso(new Ingresso());
		return "cadastrarIngressos";
	}
	
	

	public void cadastrar() {
		if (validarDados())
			setMensagem(new IngressoDao().cadastrar(getIngresso()));
	}

	public boolean validarDados() {
		if (getIngresso().getCodigoSessao() == 0) {
			setMensagem("O preenchimento do C�digo da Sess�o � obrigat�rio.");
			return false;
		}
		
		if (getIngresso().getCodigoPessoa() == 0) {
			setMensagem("O preenchimento do C�digo da Pessoa � obrigat�rio.");
			return false;
		}
		
		if (getIngresso().getCodigoPoltrona().equals(null)|| getIngresso().getCodigoPoltrona().isEmpty()) {
			setMensagem("O preenchimento do C�digo da Poltrona � obrigat�rio.");
			return false;
		}
		
		return true;
	}
	

	}

