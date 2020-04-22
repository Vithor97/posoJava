package br.com.bradesco.posoTeatro.view.bean.ingresso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.bradesco.posoTeatro.dao.EstacionamentoDao;
import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.dao.SessaoDao;
import br.com.bradesco.posoTeatro.model.Estacionamento;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.Poltrona;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.posoUtil.Constantes;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;
import br.com.bradesco.posoTeatro.view.bean.pessoa.CadastrarPessoasBean;

@ManagedBean(name = "cadastrarIngressosBean")
@SessionScoped

public class CadastrarIngressosBean extends PosoBean implements BeanInterface {

	@ManagedProperty("#{cadastrarPessoasBean}")
	private CadastrarPessoasBean cadastrarPessoasBean;
	
	//Cadastrar
	private Estacionamento estacionamento;
	private int limiteEstacionamento;
	private int vagasEstacionamento;
	private Ingresso ingresso;
	private String mensagem;
	private List<Poltrona> balcaoNobre;
	private List<Poltrona> camarotePrime;
	private List<Poltrona> plateia;
	private List<Poltrona> frisas1;
	private List<Poltrona> frisas2;
	private List<Sessao> sessoes;
	private List<Pessoa> pessoas;
	private Date currentDate = new Date();
	private String pesquisaPessoa;
	private Date pesquisaSessao;
		
	public void setCadastrarPessoasBean(CadastrarPessoasBean cadastrarPessoasBean) {
		this.cadastrarPessoasBean = cadastrarPessoasBean;
	}
	
	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public int getLimiteEstacionamento() {
		return limiteEstacionamento;
	}

	public void setLimiteEstacionamento(int limiteEstacionamento) {
		this.limiteEstacionamento = limiteEstacionamento;
	}

	public int getVagasEstacionamento() {
		return vagasEstacionamento;
	}

	public void setVagasEstacionamento(int vagasEstacionamento) {
		this.vagasEstacionamento = vagasEstacionamento;
	}

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
	
	public List<Poltrona> getBalcaoNobre() {
		return balcaoNobre;
	}

	public void setBalcaoNobre(List<Poltrona> balcaoNobre) {
		this.balcaoNobre = balcaoNobre;
	}

	public List<Poltrona> getCamarotePrime() {
		return camarotePrime;
	}

	public void setCamarotePrime(List<Poltrona> camarotePrime) {
		this.camarotePrime = camarotePrime;
	}

	public List<Poltrona> getPlateia() {
		return plateia;
	}

	public void setPlateia(List<Poltrona> plateia) {
		this.plateia = plateia;
	}

	public List<Poltrona> getFrisas1() {
		return frisas1;
	}

	public void setFrisas1(List<Poltrona> frisas1) {
		this.frisas1 = frisas1;
	}

	public List<Poltrona> getFrisas2() {
		return frisas2;
	}

	public void setFrisas2(List<Poltrona> frisas2) {
		this.frisas2 = frisas2;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public Date getCurrentDate() {
	    return currentDate;
	}

	public String getPesquisaPessoa() {
		return pesquisaPessoa;
	}

	public void setPesquisaPessoa(String pesquisaPessoa) {
		this.pesquisaPessoa = pesquisaPessoa;
	}

	public Date getPesquisaSessao() {
		return pesquisaSessao;
	}

	public void setPesquisaSessao(Date pesquisaSessao) {
		this.pesquisaSessao = pesquisaSessao;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Cadastrar Ingressos", "cadastrarIngressos", titulosBread, urlsBread);		
		inicializarComponentes();
		return "cadastrarIngressos";
	}

	public String iniciarPagina() {
		super.iniciarPagina("Cadastrar Ingressos", "cadastrarIngressos");
		inicializarComponentes();
		return "cadastrarIngressos";
	}
	
	private void inicializarComponentes() {
		setEstacionamento(new Estacionamento());
		setIngresso(new Ingresso());
		getIngresso().setSessao(new Sessao());
		getIngresso().setPessoa(new Pessoa());		
		preencherPoltronas();
		setPesquisaSessao(getCurrentDate());
		pesquisarSessoes();
		limiteEstacionamento = 0;
		vagasEstacionamento = 0;
	}

	private void preencherPoltronas() {
		setBalcaoNobre(new ArrayList<Poltrona>());
		setCamarotePrime(new ArrayList<Poltrona>());
		setFrisas1(new ArrayList<Poltrona>());
		setPlateia(new ArrayList<Poltrona>());
		setFrisas2(new ArrayList<Poltrona>());
		
		char letra = 'G';
		int numero = 1;
		
		while (letra >= 'A') {
			numero = 1;
			for (int j = 0; j < Constantes.BALCAO_NOBRE; j++) {
				Poltrona poltrona = new Poltrona("" + letra + String.format("%02d", numero), 0, 1);
				getBalcaoNobre().add(poltrona);
				numero++;
			}
			letra--;
		}
		
		letra = 'C';
		while (letra >= 'A') {
			numero = 1;
			for (int j = 0; j < Constantes.CAMAROTE_PRIME; j++) {
				Poltrona poltrona = new Poltrona("" + letra + String.format("%02d", numero), 0, 2);
				getCamarotePrime().add(poltrona);
				numero++;
			}
			letra--;
		}
		
		numero = 10;
		while (numero > 0) {
			letra = 'F';
			for (int j = 0; j < Constantes.FRISAS_1; j++) {
				Poltrona poltrona = new Poltrona("" + letra + String.format("%02d", numero), 0, 3);
				getFrisas1().add(poltrona);
				letra--;
			}
			numero--;
		}
		
		letra = 'J';
		while (letra >= 'A') {
			numero = 1;
			for (int j = 0; j < Constantes.PLATEIA; j++) {
				Poltrona poltrona = new Poltrona("" + letra + String.format("%02d", numero), 0, 4);
				getPlateia().add(poltrona);
				numero++;
			}
			letra--;
		}
		
		numero = 10;
		while (numero > 0) {
			letra = 'A';
			for (int j = 0; j < Constantes.FRISAS_2; j++) {
				Poltrona poltrona = new Poltrona("" + letra + String.format("%02d", numero), 0, 3);
				getFrisas2().add(poltrona);
				letra++;
			}
			numero--;
		}
		
		
	}

	public void reservar(Poltrona poltrona, int setor) {
		
		List<Poltrona> lista = null;
		
		switch (setor) {
		case 1: // Balcão Nobre
			lista = getBalcaoNobre();
			break;
		case 2: // Camarote Prime
			lista = getCamarotePrime();
			break;
		case 3: // Frisas 1
			lista = getFrisas1();
			break;
		case 4: // 	Platéia
			lista = getPlateia();
			break;
		case 5: // Frisas 2
			lista = getFrisas2();
			break;
		}
		
		Poltrona poltronaDaLista = lista.get(lista.indexOf(poltrona));
		if (poltronaDaLista.getSelecionada() != 1) {		
			if (poltronaDaLista.getSelecionada() == 0) {	
				poltronaDaLista.setSelecionada(2);				
			}else {											
				poltronaDaLista.setSelecionada(0);				
			}
		}
		
		
	}

	public void pesquisarSessoes() {
		Format f = new SimpleDateFormat("dd/MM/yyyy");
		String diaString = f.format(getPesquisaSessao());
		setSessoes(new SessaoDao().listarSessoes(diaString));
	}
	
	public void pesquisarPessoas() {
		setPessoas(new PessoaDao().listarPessoas(getPesquisaPessoa()));
	}
	
	public void selecionarSessao(Sessao sessao) {
		getIngresso().getSessao().setCodigo(sessao.getCodigo());
		getIngresso().getSessao().setDia(sessao.getDia());
		getIngresso().getSessao().setHorario(sessao.getHorario());
		getIngresso().getSessao().setEvento(sessao.getEvento());
		
		getEstacionamento().getSessao().setCodigo(sessao.getCodigo());
		getEstacionamento().getSessao().setDia(sessao.getDia());
		getEstacionamento().getSessao().setHorario(sessao.getHorario());
		getEstacionamento().getSessao().setEvento(sessao.getEvento());
		
		setLimiteEstacionamento(new EstacionamentoDao().limiteDisponivel(sessao));
		
		marcarPoltronasVendidas();
		setPesquisaSessao(getCurrentDate());
		setSessoes(new ArrayList<Sessao>());
	}

	public void selecionarPessoa(Pessoa pessoa) {
		getIngresso().getPessoa().setCodigo(pessoa.getCodigo());
		getIngresso().getPessoa().setNome(pessoa.getNome());
		getIngresso().getPessoa().setCpf(pessoa.getCpf());
		setPesquisaPessoa("");
		setPessoas(new ArrayList<Pessoa>());
	}
	
	public String cadastrarCliente() {
		cadastrarPessoasBean.setPessoa(getIngresso().getPessoa());
		cadastrarPessoasBean.setTelaAnterior(this);
		return cadastrarPessoasBean.iniciarPagina(getTitulosBread(), getUrlsBread());
	}

	private void marcarPoltronasVendidas() {
		preencherPoltronas();

		List<Poltrona> vendidas = new IngressoDao().verificarPoltronas(getIngresso().getSessao());
			
		for (Poltrona poltrona : vendidas) {
			List<Poltrona> lista = null;
			
			switch (poltrona.getTipoPoltrona()) {
			case 1: // Balcão Nobre
				lista = getBalcaoNobre();
				break;
			case 2: // Camarote Prime
				lista = getCamarotePrime();
				break;
			case 3: // Frisas 1
				lista = getFrisas1();
				break;
			case 4: // 	Platéia
				lista = getPlateia();
				break;
			case 5: // Frisas 2
				lista = getFrisas2();
				break;
			}
			
			Poltrona poltronaDaLista = lista.get(lista.indexOf(poltrona));
			poltronaDaLista.setSelecionada(1);
		}
		
	}
	
	public void cadastrar() {
		List<Poltrona> poltronasSelecionadas = new ArrayList<Poltrona>();
		
		for (Poltrona poltrona : getBalcaoNobre()) {
			if (poltrona.getSelecionada() == 2) {
				poltronasSelecionadas.add(poltrona);
			}
		}
		
		for (Poltrona poltrona : getCamarotePrime()) {
			if (poltrona.getSelecionada() == 2) {
				poltronasSelecionadas.add(poltrona);
			}
		}
		
		for (Poltrona poltrona : getFrisas1()) {
			if (poltrona.getSelecionada() == 2) {
				poltronasSelecionadas.add(poltrona);
			}
		}
		
		for (Poltrona poltrona : getPlateia()) {
			if (poltrona.getSelecionada() == 2) {
				poltronasSelecionadas.add(poltrona);
			}
		}
		
		for (Poltrona poltrona : getFrisas2()) {
			if (poltrona.getSelecionada() == 2) {
				poltronasSelecionadas.add(poltrona);
			}
		}

		if (getIngresso().getSessao().getCodigo() == 0) {
			setMensagem(getMessageProperties("msg_cadastrarIngressos_sessao"));
			showDialog();
		} else if (getIngresso().getPessoa().getCodigo() == 0) {
			setMensagem(getMessageProperties("msg_cadastrarIngressos_pessoa"));
			showDialog();
		} else if (poltronasSelecionadas.size() == 0) {
			setMensagem(getMessageProperties("msg_cadastrarIngressos_nenhumaPoltrona"));
			showDialog();
		} else {
			for (Poltrona poltrona : poltronasSelecionadas) {
				getIngresso().setPoltrona(poltrona);
				setMensagem(new IngressoDao().cadastrar(getIngresso()));			
			}
			
			for (int i = 0; i < vagasEstacionamento; i++) {
				new EstacionamentoDao().cadastrar(getEstacionamento());
			}
			
			showDialog();
			iniciarPagina();
		}
	}
}
