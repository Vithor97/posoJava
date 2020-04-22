package br.com.bradesco.posoTeatro.model;

public class Horario {

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
