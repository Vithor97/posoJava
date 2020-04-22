package br.com.bradesco.posoTeatro.view.bean.empresaResponsavel;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EmpresaDao;
import br.com.bradesco.posoTeatro.model.EmpresaResponsavel;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "cadastrarEmpresaBean")
@SessionScoped

public class CadastrarEmpresaBean extends PosoBean {

	private EmpresaResponsavel empresaResponsavel;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public EmpresaResponsavel getEmpresaResponsavel() {
		return empresaResponsavel;
	}

	public void setEmpresaResp(EmpresaResponsavel empresaResponsavel) {
		this.empresaResponsavel = empresaResponsavel;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Empresas", "cadastrarEmpresas", titulosBread, urlsBread);
		setEmpresaResp(new EmpresaResponsavel());
		return "cadastrarEmpresas";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Empresas", "cadastrarEmpresas");
		setEmpresaResp(new EmpresaResponsavel());
		return "cadastrarEmpresas";
	}

	public void cadastrar() {
		if (validarDados())
			setMensagem(new EmpresaDao().cadastrar(getEmpresaResponsavel()));
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
}
