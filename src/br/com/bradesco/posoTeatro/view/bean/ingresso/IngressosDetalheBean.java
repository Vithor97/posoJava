	package br.com.bradesco.posoTeatro.view.bean.ingresso;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.posoUtil.DataUtil;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "ingressosDetalheBean")
@SessionScoped

public class IngressosDetalheBean extends PosoBean implements BeanInterface{
	
	private Ingresso ingresso;
	
	private String hora;
	
	private String mensagem;

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Ingressos detalhe", "consultarIngressosDetalhe", titulosBread, urlsBread);
		getIngresso().setPessoa(new PessoaDao().consultar(getIngresso().getPessoa()));
		getIngresso().setSessao(new SessaoDao().consultaSessao(getIngresso().getSessao()));
		getIngresso().getSessao().setDataSessao(DataUtil.transformaData(getIngresso().getSessao().getDataSessao()));
		setHora(getIngresso().getSessao().getHorario().getHorarioCompleto());
		return "consultarIngressosDetalhe";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Ingressos detalhe", "consultarIngressosDetalhe");
		return "consultarIngressosDetalhe";
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
