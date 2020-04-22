package br.com.bradesco.posoTeatro.model;

public class Estacionamento {
	private int codigo;
	private Pessoa pessoa;
	private Sessao sessao;

	public Estacionamento() {
		setPessoa(new Pessoa());
		setSessao(new Sessao());
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
}
