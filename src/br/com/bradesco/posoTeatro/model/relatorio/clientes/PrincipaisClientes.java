package br.com.bradesco.posoTeatro.model.relatorio.clientes;

import br.com.bradesco.posoTeatro.model.Pessoa;

public class PrincipaisClientes {
	private Pessoa pessoa;
	private int quantidadeCompras;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getQuantidadeCompras() {
		return quantidadeCompras;
	}

	public void setQuantidadeCompras(int quantidadeCompras) {
		this.quantidadeCompras = quantidadeCompras;
	}

	public PrincipaisClientes() {
		setPessoa(new Pessoa());
	}
}
