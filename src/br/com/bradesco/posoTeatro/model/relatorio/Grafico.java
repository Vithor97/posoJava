package br.com.bradesco.posoTeatro.model.relatorio;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public List<Grafico<T>> ordenar(List<Grafico<T>> lista){
        Collections.sort(lista, new Comparator<Grafico<T>>() {
			@Override
			public int compare(Grafico<T> o1, Grafico<T> o2) {
                return o2.getValor().compareTo(o1.getValor());
			}
           
        });
        return lista;
	}
}


