package br.com.bradesco.posoTeatro.posoUtil;

public class Conversoes {

	public String getReal(double valor) {
		return "R$ " + String.format("%.2f", valor);
	}
	
}
