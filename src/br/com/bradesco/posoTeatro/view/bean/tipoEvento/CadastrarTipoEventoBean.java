package br.com.bradesco.posoTeatro.view.bean.tipoEvento;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EmpresaDao;
import br.com.bradesco.posoTeatro.dao.GeneroDao;
import br.com.bradesco.posoTeatro.dao.TipoEventoDao;
import br.com.bradesco.posoTeatro.model.EmpresaResponsavel;
import br.com.bradesco.posoTeatro.model.Genero;
import br.com.bradesco.posoTeatro.model.TipoEvento;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarTipoEventoBean")
@SessionScoped

public class CadastrarTipoEventoBean extends PosoBean {

	private TipoEvento tipoEvento;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;


	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar TipoEvento", "cadastrarTipoEvento", titulosBread, urlsBread);
		setTipoEvento(new TipoEvento());
		return "cadastrarTipoEvento";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar TipoEvento", "cadastrarTipoEvento");
		setTipoEvento(new TipoEvento());
		return "cadastrarTipoEvento";
	}

	public void cadastrar() {
		if (validarDados())
			setMensagem(new TipoEventoDao().cadastrar(getTipoEvento()));
	}

	public boolean validarDados() {
		if (getTipoEvento().getNome().equals(null) || getTipoEvento().getNome().equals("")) {
			setMensagem("O preenchimento do campo é obrigatório.");
			return false;
		}
		return true;
	}


}
