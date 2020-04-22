package br.com.bradesco.posoTeatro.model;

import br.com.bradesco.posoTeatro.posoUtil.Validacoes;

public class EmpresaResponsavel {
	private int codigo;
	private String cnpj;
	private String nome;


	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpjFormatado() {
		if(!getCnpj().equals(null)) {
			return getCnpj().replace(".", "").replace("-", "").replace("/", "");
		}
		return "";
	}

	public boolean cnpjValido() {
		if (!getCnpj().equals(null)) {
			return new Validacoes().cnpjValido(getCnpj().replace(".", "").replace("-", "").replace("/", ""));
		}
		return false;
	}

}
