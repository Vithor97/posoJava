package br.com.bradesco.posoTeatro.view.bean.empresaResponsavel;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;

import br.com.bradesco.posoTeatro.dao.EmpresaDao;
import br.com.bradesco.posoTeatro.model.EmpresaResponsavel;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "alterarEmpresaBean")
@SessionScoped

public class AlterarEmpresaBean extends PosoBean implements Serializable, BeanInterface {
// aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	private static final long serialVersionUID = 1L;

	private EmpresaResponsavel empresaResponsavel = new EmpresaResponsavel();
	private String mensagem;
	
	public EmpresaResponsavel getEmpresaResponsavel() {
		return empresaResponsavel;
	}

	public void setEmpresaResponsavel(EmpresaResponsavel empresaResponsavel) {
		this.empresaResponsavel = empresaResponsavel;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void alterar() {
		int linhasAfetadas;
		if (validarDados()) {
			linhasAfetadas = new EmpresaDao().alterar(getEmpresaResponsavel());
			if (linhasAfetadas != 0) {
				setMensagem("Alteração realizada com sucesso");

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(getMensagem()));

				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

			} else {
				setMensagem("Alteração não realizada");
			}
		} else {

		}

	}

	public boolean validarDados() {
		if (getEmpresaResponsavel().getNome().equals(null) || getEmpresaResponsavel().getNome().isEmpty()) {
			setMensagem("O preenchimento do campo nome é obrigatório.");
			return false;
		}

		if (getEmpresaResponsavel().getCnpj().equals(null) || getEmpresaResponsavel().getCnpjFormatado().length() < 14) {
			setMensagem("O preenchimento do campo CNPJ é obrigatório.");
			return false;
		} else if (!getEmpresaResponsavel().cnpjValido()) {
			setMensagem("O CNPJ digitado é inválido.");
			return false;
		}
		return true;
	}

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		return null;
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Alterar Empresas", "alterarEmpresas");
		return "alterarEmpresas";
	}
}
