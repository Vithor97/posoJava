package br.com.bradesco.posoTeatro.model;

public class Ingresso {
	private int codigoIngresso;
	private Sessao sessao = new Sessao();
	private Pessoa pessoa = new Pessoa();
	private Poltrona poltrona = new Poltrona();
	
	public int getCodigoIngresso() {
		return codigoIngresso;
	}

	public void setCodigoIngresso(int codigoIngresso) {
		this.codigoIngresso = codigoIngresso;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(Poltrona poltrona) {
		this.poltrona = poltrona;
	}

	public Ingresso() {
		setSessao(new Sessao());
		setPessoa(new Pessoa());
		setPoltrona(new Poltrona());
	}

	@Override
	public String toString() {
		return "Ingresso [codigoIngresso=" + codigoIngresso + ", codigoSessao=" + sessao.getCodigo() + ", codigoPessoa="
				+ pessoa.getCodigo() + ", codigoPoltrona=" + poltrona.getCodigo() + "]";
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
		if (pessoa.getCodigo() != other.pessoa.getCodigo())
			return false;
		if (poltrona.getCodigo() == null) {
			if (other.poltrona.getCodigo() != null)
				return false;
		} else if (!poltrona.getCodigo().equals(other.poltrona.getCodigo()))
			return false;
		if (sessao.getCodigo() != other.sessao.getCodigo())
			return false;
		return true;
	}
	
	
}
