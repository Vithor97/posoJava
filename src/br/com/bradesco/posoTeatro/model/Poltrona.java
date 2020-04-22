package br.com.bradesco.posoTeatro.model;

public class Poltrona {
	private String codigo;
	private int selecionada;
	private int tipoPoltrona;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(int selecionada) {
		this.selecionada = selecionada;
	}

	public int getTipoPoltrona() {
		return tipoPoltrona;
	}

	public void setTipoPoltrona(int tipoPoltrona) {
		this.tipoPoltrona = tipoPoltrona;
	}

	public Poltrona(String codigo, int selecionada, int tipoPoltrona) {
		setCodigo(codigo);
		setSelecionada(selecionada);
		setTipoPoltrona(tipoPoltrona);
	}
	
	public Poltrona() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + tipoPoltrona;
		result = prime * result + selecionada;
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
		Poltrona other = (Poltrona) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
