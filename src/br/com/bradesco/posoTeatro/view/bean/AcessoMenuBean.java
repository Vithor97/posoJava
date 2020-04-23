package br.com.bradesco.posoTeatro.view.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.model.MenuCatalog;


@ManagedBean(name="acessoMenuBean")
@SessionScoped

public class AcessoMenuBean{		

	public AcessoMenuBean() {
	}
	
	private MenuCatalog menuCatalog;
	
	public void validaAcesso(ComponentSystemEvent event) {
		
		menuCatalog = new MenuCatalog();
		
		FacesContext context = FacesContext.getCurrentInstance();
		Funcionario funcionario = (Funcionario) context.getExternalContext().getSessionMap().get("funcionarioLogado");
		if(funcionario.getSenha().equals("123456")) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Por favor vá no seu perfil e altere sua senha para ter acesso ao menu!",""));
		}
		else {
			switch (funcionario.getCargo().getCodigo()) {
			case 1:
				liberarGerente();
				break;
			case 2:
				liberarCoordenadorEventos();
				break;
			case 3:
				liberarCaixa();
				break;
			case 4:
				liberarCoordenadorGeralEventos();
				break;
			case 5:
				liberarCoordenadorGeralSessoes();
				break;
			default:
				break;
			}
		}
	}

	private void liberarGerente(){
		getMenuCatalog().setMenuEventos(true);
		getMenuCatalog().setCadastrarEventos(true);
		getMenuCatalog().setListarEventos(true);
		
		getMenuCatalog().setMenuSessoes(true);
		getMenuCatalog().setCadastrarSessoes(true);
		getMenuCatalog().setConsultarSessoes(true);
		
		getMenuCatalog().setMenuPessoas(true);
		getMenuCatalog().setCadastrarPessoas(true);
		getMenuCatalog().setConsultarPessoas(true);
		
		getMenuCatalog().setMenuIngressos(true);
		getMenuCatalog().setCadastrarIngressos(true);
		getMenuCatalog().setConsultarIngressos(true);
		
		getMenuCatalog().setMenuEmpresas(true);
		getMenuCatalog().setCadastrarEmpresas(true);
		getMenuCatalog().setConsultarEmpresas(true);
		
		getMenuCatalog().setMenuFuncionarios(true);
		getMenuCatalog().setCadastrarFuncionarios(true);
		getMenuCatalog().setConsultarFuncionarios(true);
		
		getMenuCatalog().setMenuGenero(true);
		getMenuCatalog().setCadastrarGenero(true);
		
		getMenuCatalog().setMenuTipoEvento(true);
		getMenuCatalog().setCadastrarTipoEvento(true);
		
		
	}
	
	private void liberarCoordenadorEventos() {
		getMenuCatalog().setMenuEventos(true);
		getMenuCatalog().setListarEventos(true);
	}
	
	private void liberarCaixa() {
		getMenuCatalog().setMenuEventos(true);
		getMenuCatalog().setListarEventos(true);
		
		getMenuCatalog().setMenuSessoes(true);
		getMenuCatalog().setConsultarSessoes(true);
		
		getMenuCatalog().setMenuIngressos(true);
		getMenuCatalog().setConsultarIngressos(true);
	}
	
	private void liberarCoordenadorGeralEventos() {
		getMenuCatalog().setMenuEventos(true);
		getMenuCatalog().setCadastrarEventos(true);
		getMenuCatalog().setListarEventos(true);
		
		getMenuCatalog().setMenuEmpresas(true);
		getMenuCatalog().setCadastrarEmpresas(true);
		getMenuCatalog().setConsultarEmpresas(true);
		
		getMenuCatalog().setMenuPessoas(true);
		getMenuCatalog().setCadastrarPessoas(true);
		getMenuCatalog().setConsultarPessoas(true);
		
		getMenuCatalog().setMenuFuncionarios(true);
		getMenuCatalog().setConsultarFuncionarios(true);
	}
	
	private void liberarCoordenadorGeralSessoes() {
		getMenuCatalog().setMenuSessoes(true);
		getMenuCatalog().setCadastrarSessoes(true);
		getMenuCatalog().setConsultarSessoes(true);
		
		getMenuCatalog().setMenuEventos(true);
		getMenuCatalog().setListarEventos(true);
	}
	public MenuCatalog getMenuCatalog() {
		return menuCatalog;
	}

	public void setMenuCatalog(MenuCatalog menuCatalog) {
		this.menuCatalog = menuCatalog;
	}
	
}
