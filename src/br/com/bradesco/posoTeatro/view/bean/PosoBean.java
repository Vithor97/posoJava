package br.com.bradesco.posoTeatro.view.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.MenuModel;

import br.com.bradesco.posoTeatro.view.breadCrumb.BreadCrumb;

@ManagedBean(name = "posoBean")
@SessionScoped

public class PosoBean {

	private MenuModel menuModel;
	private List<String> titulosBread;
	private List<String> urlsBread;
	private BeanInterface telaAnterior;


	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public List<String> getTitulosBread() {
		return titulosBread;
	}

	public void setTitulosBread(List<String> titulosBread) {
		this.titulosBread = titulosBread;
	}

	public List<String> getUrlsBread() {
		return urlsBread;
	}

	public void setUrlsBread(List<String> urlsBread) {
		this.urlsBread = urlsBread;
	}

	public BeanInterface getTelaAnterior() {
		return telaAnterior;
	}

	public void setTelaAnterior(BeanInterface telaAnterior) {
		this.telaAnterior = telaAnterior;
	}

	public String iniciarPagina(String titulo, String url) {
		setTitulosBread(new ArrayList<String>());
		setUrlsBread(new ArrayList<String>());
		getTitulosBread().add(titulo);
		getUrlsBread().add(url);
		setMenuModel(new BreadCrumb().bread(getTitulosBread(), getUrlsBread()));
		return "";
	}

	public String iniciarPagina(String titulo, String url, List<String> titulosBread, List<String> urlsBread) {
		if (!titulosBread.contains(titulo)) {
			setTitulosBread(titulosBread);
			setUrlsBread(urlsBread);
			getTitulosBread().add(titulo);
			getUrlsBread().add(url);
			setMenuModel(new BreadCrumb().bread(getTitulosBread(), getUrlsBread()));
		}
		return "";
	}

	public String voltar() {
		if (telaAnterior != null) {
			getTitulosBread().remove(getTitulosBread().size() - 1);
			getUrlsBread().remove(getUrlsBread().size() - 1);
			return telaAnterior.iniciarPagina(getTitulosBread(), getUrlsBread());
		}else {
			for (int i = 1; i < getTitulosBread().size(); i++) {
				getTitulosBread().remove(i);
				getUrlsBread().remove(i);
			}
			return new IndexBean().iniciarPagina(getTitulosBread(), getUrlsBread());
		}
	}

	public String getMessageProperties(String label) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = context.getApplication().getResourceBundle(context, "msgs");
		return messageBundle.getString(label);
	}
	
	public String getMessageProperties(String prefixo, String label) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle messageBundle = context.getApplication().getResourceBundle(context, prefixo);
		return messageBundle.getString(label);
	}
	
	public void showDialog() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dlg1').show();");
	}

	public void showDialog(String dialog) {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('" + dialog + "').show();");
	}
}
