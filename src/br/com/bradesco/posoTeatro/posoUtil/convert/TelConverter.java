package br.com.bradesco.posoTeatro.posoUtil.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class TelConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		
		TelData telData = new TelData(value.toString());
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
