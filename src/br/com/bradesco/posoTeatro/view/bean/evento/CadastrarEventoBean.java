package br.com.bradesco.posoTeatro.view.bean.evento;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Genero;
import br.com.bradesco.posoTeatro.model.TipoEvento;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarEventoBean")
@SessionScoped

public class CadastrarEventoBean extends PosoBean {

	private Evento evento;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	private List<TipoEvento> tiposEvento;
	
	private List<Genero> generos;
	

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> genero) {
		this.generos = genero;
	}
	
	public List<TipoEvento> getTiposEvento() {
		return tiposEvento;
	}

	public void setTiposEvento(List<TipoEvento> tiposEvento) {
		this.tiposEvento = tiposEvento;
	}
	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Evento", "cadastrarEvento", titulosBread, urlsBread);
		setMensagem("");
		setTiposEvento(new EventoDao().listarTipos());
		setGeneros(new EventoDao().listarGeneros());
		setEvento(new Evento());
		return "cadastrarEvento";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Evento", "cadastrarEvento");
		setMensagem("");
		setTiposEvento(new EventoDao().listarTipos());
		setGeneros(new EventoDao().listarGeneros());
		setEvento(new Evento());
		return "cadastrarEvento";
	}

	public void retornaCnpj() {
		if (evento.getEmpresaResponsavel().getCnpj().equals("")) {
			evento.getEmpresaResponsavel().setNome("");
		} else {
			new EventoDao().voltarCnpj(getEvento());
		}
	}
	
	public void retornaCpf() {
		if (evento.getFuncionario().getCpf().equals("")) {
			evento.getFuncionario().setNome("");
		} else {
			new EventoDao().voltarCpf(getEvento());
		}
	}

	public void cpfPessoaEvento() {
		if (evento.getPessoa().getCpf().equals("") || evento.getPessoa().getCpf().equals(null)) {
			evento.getPessoa().setNome("");
		} else {
			setMensagem("");
			new EventoDao().voltarPessoaEvento(getEvento());
			if (evento.getPessoa().getNome().equals("*")) {
				setMensagem("CPF nao encontrado.");
			}
			if (evento.getPessoaEvento().contains(evento.getPessoa().getNome())) {
				setMensagem("Pessoa ja inserida na lista");
			}
		}
		if (getMensagem().equals("")) {
			evento.getPessoaEvento().add(evento.getPessoa().getNome());
		}
	}

	public void cadastrar() {
		if (validarDados()) {
			setMensagem(new EventoDao().cadastrar(getEvento()));
			setMensagem(new EventoDao().cadastrarPessoaEvento(getEvento()));
		}
	}

	public boolean validarDados() {
		if (getEvento().getEmpresaResponsavel().getCnpj().equals(null) || getEvento().getEmpresaResponsavel().getCnpj().isEmpty()) {
			setMensagem("O preenchimento do campo CNPJ � obrigat�rio.");
			return false;
		} else if (!getEvento().getEmpresaResponsavel().cnpjValido()) {
			setMensagem("O CNPJ digitado � inv�lido.");
			return false;
		}
		if (getEvento().getFuncionario().getCpf().equals(null) || getEvento().getFuncionario().getCpf().length() < 11) {
			setMensagem("O preenchimento do campo CPF � obrigat�rio.");
			return false;
		} else if (!getEvento().getFuncionario().cpfValido()) {
			setMensagem("O CPF digitado � inv�lido.");
			return false;
		}
		if (getEvento().getDescricao().equals(null) || getEvento().getDescricao().isEmpty()) {
			setMensagem("O preenchimento do campo descricao � obrigat�rio");
			return false;
		}
//		if (getEvento().getTipoEvento().equals(null) || getEvento().getTipo().isEmpty()) {
//			setMensagem("O preenchimento do campo tipo � obrigat�rio");
//			return false;
//		}
//		if (getEvento().getGenero().equals(null) || getEvento().getGenero().isEmpty()) {
//			setMensagem("A selecao do campo genero � obrigat�ria");
//			return false;
//		}
		if (getEvento().getPessoaEvento().isEmpty()) {
			setMensagem("O elenco esta vazio.");
			return false;
		}
		return true;
	}




}
