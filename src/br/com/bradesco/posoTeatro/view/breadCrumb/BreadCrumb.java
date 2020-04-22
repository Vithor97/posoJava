package br.com.bradesco.posoTeatro.view.breadCrumb;

import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;

public class BreadCrumb {
	public DefaultMenuModel bread(List<String> titulos, List<String> urls) {
		DefaultMenuModel menuModel = new DefaultMenuModel();
		DefaultMenuItem item = new DefaultMenuItem();
		item.setUrl(getFacesUrl("index"));
		menuModel.addElement(item);

		int i = 0;
		
		for (String string : titulos) {
			item = new DefaultMenuItem(string);
			item.setDisabled(true);
			item.setStyle("opacity: 1");
//			if(!urls.get(i).equals("") && i != titulos.size() - 1)
//				item.setUrl(getFacesUrl(urls.get(i)));
			menuModel.addElement(item);
			i++;
		}
		
		return menuModel;
	}
	
	public String getFacesUrl(String pagina) {
		FacesContext context = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler cHandler = (ConfigurableNavigationHandler)context.getApplication().getNavigationHandler();
		NavigationCase nCase = cHandler.getNavigationCase(context, null, pagina);
		return "/posoTeatro" + nCase.getToViewId(context);
	}
}
