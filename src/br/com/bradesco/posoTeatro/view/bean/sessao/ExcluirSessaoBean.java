package br.com.bradesco.posoTeatro.view.bean.sessao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "excluirSessaoBean")
@SessionScoped
public class ExcluirSessaoBean extends PosoBean{

	private Sessao sessao;

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
	public void excluir() {
		boolean deuCerto = new SessaoDao().excluirSessao(this.getSessao());
		PrimeFaces current = PrimeFaces.current();
		if(deuCerto) {
			current.dialog()
					.showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Sessão com código " + sessao.getCodigo() + " excluída"));
		} else {
			current.dialog()
				.showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Houve um erro na exclusão da Sessão!"));
		}
		
	}
}
