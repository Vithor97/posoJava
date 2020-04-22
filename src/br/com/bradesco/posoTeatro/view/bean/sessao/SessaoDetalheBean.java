package br.com.bradesco.posoTeatro.view.bean.sessao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "consultaSessaoDetalheBean")
@SessionScoped
public class SessaoDetalheBean extends PosoBean implements BeanInterface {

	private Sessao sessao;
	private boolean desabilitado = true;
	private String mensagem;

	@ManagedProperty(value = "#{alterarSessaoBean}")
	private AlterarSessaoBean alterar;
	
	@ManagedProperty(value = "#{excluirSessaoBean}")
	private ExcluirSessaoBean excluir;

	public ExcluirSessaoBean getExcluir() {
		return excluir;
	}

	public void setExcluir(ExcluirSessaoBean excluir) {
		this.excluir = excluir;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public AlterarSessaoBean getAlterar() {
		return alterar;
	}

	public void setAlterar(AlterarSessaoBean alterar) {
		this.alterar = alterar;
	}

	public Date getDataAtual() {
		return new Date(); 
	}
	
	public List<Date> getDataAtualList(){
		List<Date> resp = new ArrayList<Date>();
		resp.add(getDataAtual());
		return resp;
	}

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Sessão detalhe", "consultaSessaoDetalhe", titulosBread, urlsBread);
		return "consultaSessaoDetalhe";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Sessão detalhe", "consultaSessaoDetalhe");
		return "consultaSessaoDetalhe";
	}

	public void altera() {
		this.desbloqueiaCampos();
	}

	public void salvar() {
		alterar.setSessao(this.sessao);
		alterar.alterar();
		this.bloqueiaCampos();
	}

	private void desbloqueiaCampos() {
		this.setDesabilitado(false);
	}
	
	private void bloqueiaCampos() {
		this.setDesabilitado(true);
	}

	public void cancelar() {
		bloqueiaCampos();
	}

	public String exclui() {
		excluir.setSessao(this.sessao);
		excluir.excluir();
		return this.voltar();
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

}
