package br.com.bradesco.posoTeatro.model;

public class Ingresso {
	private int codigoIngresso;
	private int codigoSessao;
	private int codigoPessoa;
	private String codigoPoltrona;
	
	public int getCodigoIngresso() {
		return codigoIngresso;
	}
	
	public void setCodigoIngresso(int codigoIngresso) {
		this.codigoIngresso = codigoIngresso;
	}
	
	public int getCodigoSessao() {
		return codigoSessao;
	}
	
	public void setCodigoSessao(int codigoSessao) {
		this.codigoSessao = codigoSessao;
	}
	
	public int getCodigoPessoa() {
		return codigoPessoa;
	}
	
	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}
	
	public String getCodigoPoltrona() {
		return codigoPoltrona;
	}
	
	public void setCodigoPoltrona(String codigoPoltrona) {
		this.codigoPoltrona = codigoPoltrona;
	}

	@Override
	public String toString() {
		return "Ingresso [codigoIngresso=" + codigoIngresso + ", codigoSessao=" + codigoSessao + ", codigoPessoa="
				+ codigoPessoa + ", codigoPoltrona=" + codigoPoltrona + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoIngresso;
		result = prime * result + codigoPessoa;
		result = prime * result + ((codigoPoltrona == null) ? 0 : codigoPoltrona.hashCode());
		result = prime * result + codigoSessao;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingresso other = (Ingresso) obj;
		if (codigoIngresso != other.codigoIngresso)
			return false;
		if (codigoPessoa != other.codigoPessoa)
			return false;
		if (codigoPoltrona == null) {
			if (other.codigoPoltrona != null)
				return false;
		} else if (!codigoPoltrona.equals(other.codigoPoltrona))
			return false;
		if (codigoSessao != other.codigoSessao)
			return false;
		return true;
	}
	
	
}
