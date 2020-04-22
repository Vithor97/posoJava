package br.com.bradesco.posoTeatro.view.bean.sessao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@SessionScoped
@ManagedBean(name = "alterarSessaoBean")
public class AlterarSessaoBean extends PosoBean {

	private Sessao sessao;
	private String mensagem;
	
	public void alterar() {
		if(validarDados()) {
			String resultado = new SessaoDao().alterarSessao(this.getSessao());
			setMensagem(resultado);
			this.showDialog("dlg2");
		}
	}
	
	//Melhorar, pois tem cópia de código do cadastrar
	private boolean validarDados() {

		if (!sessao.validaCodEvento()) {
			setMensagem("Código do evento deve ser positivo (maior que zero)!");
			this.showDialog("dlg2");
			return false;
		}
		if (sessao.getDia() == null) {
			setMensagem("Data e hora da nova sessão devem ser válidas!");
			this.showDialog("dlg2");
			return false;
		}
		if (!sessao.validaDiaSessao()) {
			setMensagem("Data da nova sessão devem ser após a data atual!");
			this.showDialog("dlg2");
			return false;
		}
		return true;
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

}
