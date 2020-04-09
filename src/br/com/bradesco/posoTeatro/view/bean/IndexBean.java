package br.com.bradesco.posoTeatro.view.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="indexBean")
@SessionScoped

public class IndexBean extends PosoBean implements BeanInterface{
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Index", "index", titulosBread, urlsBread);
		return "index";
	}
	
	public String iniciarPagina() {
		super.iniciarPagina("", "");
		return "index";
	}
	
}
