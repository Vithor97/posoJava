package br.com.bradesco.posoTeatro.view.bean.empresaResponsavel;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.sun.faces.config.Verifier;

import br.com.bradesco.posoTeatro.dao.EmpresaDao;
import br.com.bradesco.posoTeatro.model.EmpresaResponsavel;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "excluirEmpresaBean")
@SessionScoped

public class ExcluirEmpresaBean extends PosoBean implements Serializable, BeanInterface {

	private static final long serialVersionUID = 1L;

	private EmpresaResponsavel empresaResponsavel = new EmpresaResponsavel();
	private String mensagem;

	public EmpresaResponsavel getEmpresaResponsavel() {
		return empresaResponsavel;
	}

	public void setEmpresaResp(EmpresaResponsavel empresaResponsavel) {
		this.empresaResponsavel = empresaResponsavel;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String excluir() {
		int linhasAfetadas;
		if (validarDados()) {
			linhasAfetadas = new EmpresaDao().excluir(getEmpresaResponsavel());
			if (linhasAfetadas != 0) {
			} else {
				setMensagem("Exclusão realizada com sucesso");

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getMensagem()));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}

		} else {
			setMensagem("Exclusão não realizada");
		}
		return "tralala";
	}

	public boolean validarDados() {
		if (getEmpresaResponsavel().getCodigo() == 0) {
			setMensagem("O preenchimento do campo Código é obrigatório.");
			return false;
		} else if (getEmpresaResponsavel().getCodigo() < 0) {
			setMensagem("Código inválido.");
			return false;
		}
		return true;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		return null;
	}

	public String iniciarPagina() {
		super.iniciarPagina("Excluir Empresas", "excluirEmpresas");
		return "excluirEmpresas";
	}
}
