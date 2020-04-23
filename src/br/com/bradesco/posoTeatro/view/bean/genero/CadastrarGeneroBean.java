package br.com.bradesco.posoTeatro.view.bean.genero;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EmpresaDao;
import br.com.bradesco.posoTeatro.dao.GeneroDao;
import br.com.bradesco.posoTeatro.model.EmpresaResponsavel;
import br.com.bradesco.posoTeatro.model.Genero;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarGeneroBean")
@SessionScoped

public class CadastrarGeneroBean extends PosoBean {

	private Genero genero;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;


	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Genero", "cadastrarGenero", titulosBread, urlsBread);
		setGenero(new Genero());
		return "cadastrarGenero";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Genero", "cadastrarGenero");
		setGenero(new Genero());
		return "cadastrarGenero";
	}

	public void cadastrar() {
		if (validarDados())
			setMensagem(new GeneroDao().cadastrar(getGenero()));
	}

	public boolean validarDados() {
		if (getGenero().getNome().equals(null) || getGenero().getNome().equals("")) {
			setMensagem("O preenchimento do campo é obrigatório.");
			return false;
		}
		return true;
	}

}
