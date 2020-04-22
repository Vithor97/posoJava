package br.com.bradesco.posoTeatro.view.bean.sessao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarSessaoBean")
@SessionScoped
public class CadastrarSessaoBean extends PosoBean {

	private Sessao sessao;
	private String mensagem;

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		inicializaSessao();
		super.iniciarPagina("Cadastrar Sessão", "cadastrarSessao", titulosBread, urlsBread);
		return "cadastrarSessao";
	}

	public String iniciarPagina() {
		inicializaSessao();
		super.iniciarPagina("Cadastrar Sessão", "cadastrarSessao");
		return "cadastrarSessao";
	}
	
	public void inicializaMensagem() {
		setMensagem(null);
	}
	
	public void inicializaSessao() {
		setSessao(new Sessao());
	}
	
	public String limparPagina() {
		if(!validarDados()) {
			return iniciarPagina();
		}
		
		return "";
	}

	public void cadastrar() {
		inicializaMensagem();
		if (validarDados()) {
			setMensagem(new SessaoDao().cadastrar(this.getSessao()));
			this.showDialog();
		}
	}

	private boolean validarDados() {

		if (!sessao.validaCodEvento()) {
			setMensagem("Código do evento deve ser positivo (maior que zero)!");
			this.showDialog();
			return false;
		}
		if (sessao.getDia() == null) {
			setMensagem("Data e hora da nova sessão devem ser válidas!");
			this.showDialog();
			return false;
		}
		if (!sessao.validaDiaSessao()) {
			setMensagem("Data da nova sessão devem ser após a data atual!");
			this.showDialog();
			return false;
		}

		return true;
	}
	
	public Date getDataAtual() {
		return new Date(); 
	}
	
	public List<Date> getDataAtualList(){
		List<Date> resp = new ArrayList<Date>();
		resp.add(getDataAtual());
		return resp;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
