package br.com.bradesco.posoTeatro.posoUtil;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.bradesco.posoTeatro.model.Funcionario;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event ) {
		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		
		if("/content/login.xhtml".equals(nomePagina)) {
			return;
		}
		Funcionario funcionarioLogado = (Funcionario) context.getExternalContext().getSessionMap().get("funcionarioLogado");
		
		if(funcionarioLogado != null) {
			return;
		}
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, "", "login");;
		context.getRenderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
