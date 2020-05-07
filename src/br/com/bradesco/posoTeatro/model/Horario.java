package br.com.bradesco.posoTeatro.model;

public class Horario implements Comparable<Horario> {

	private int hora;
	private int minutos;
	
	public Horario() {
		hora = 0;
		minutos = 0;
	}
	
	public String getHoraTexto() {
		return String.format("%02d", hora);
	}
	
	public String getMinutosTexto() {
		return String.format("%02d", minutos);
	}
	
	public String getHorarioCompleto() {
		return this.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hora;
		result = prime * result + minutos;
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
		Horario other = (Horario) obj;
		if (hora != other.hora)
			return false;
		if (minutos != other.minutos)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Horario o) {
		if(this.hora > o.hora) {
			return 1;
		}
		if(this.hora < o.hora) {
			return -1;
		}
		if(this.minutos > o.hora) {
			return 1;
		}
		if(this.minutos < o.hora) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return this.getHoraTexto() + ":" + this.getMinutosTexto();
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

}
