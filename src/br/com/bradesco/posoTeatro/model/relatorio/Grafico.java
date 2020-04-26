package br.com.bradesco.posoTeatro.model.relatorio;

public class Grafico<T> {

	private T item;
	private Double valor;

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Grafico(T item){
		setItem(item);
	}
	
	    
	

}


