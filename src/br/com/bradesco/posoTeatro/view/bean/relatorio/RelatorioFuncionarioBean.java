package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.dao.FuncionarioDao;
import br.com.bradesco.posoTeatro.dao.RelatorioDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.evento.DetalheEventoBean;

@ManagedBean(name = "relatorioFuncionarioBean")
@SessionScoped

public class RelatorioFuncionarioBean extends RelatorioBean implements BeanInterface {
	
	@ManagedProperty("#{detalheEventoBean}")
	private DetalheEventoBean detalheEventoBean;

	private String mensagem;
	
	//Funcionarios
	private HorizontalBarChartModel principaisFuncionariosGrafico;
	private List<Grafico<Funcionario>> principaisFuncionariosLista;
	private int principaisFuncionariosQuantidade;
	private Funcionario funcionarioDetalhe;
	private List<Evento> eventosFuncionario;
	
	private BarChartModel faixaEtariaFuncionariosGraficoColunas;
	private PieChartModel faixaEtariaFuncionariosGraficoPizza;

	public void setDetalheEventoBean(DetalheEventoBean detalheEventoBean) {
		this.detalheEventoBean = detalheEventoBean;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public HorizontalBarChartModel getPrincipaisFuncionariosGrafico() {
		return principaisFuncionariosGrafico;
	}

	public void setPrincipaisFuncionariosGrafico(HorizontalBarChartModel principaisFuncionariosGrafico) {
		this.principaisFuncionariosGrafico = principaisFuncionariosGrafico;
	}

	public List<Grafico<Funcionario>> getPrincipaisFuncionariosLista() {
		return principaisFuncionariosLista;
	}

	public void setPrincipaisFuncionariosLista(List<Grafico<Funcionario>> principaisFuncionariosLista) {
		this.principaisFuncionariosLista = principaisFuncionariosLista;
	}
	
	public int getPrincipaisFuncionariosQuantidade() {
		return principaisFuncionariosQuantidade;
	}

	public void setPrincipaisFuncionariosQuantidade(int principaisFuncionariosQuantidade) {
		if (principaisFuncionariosQuantidade > 0) {
			this.principaisFuncionariosQuantidade = principaisFuncionariosQuantidade;
		} else {
			this.principaisFuncionariosQuantidade = 1;
		}
	}

	public Funcionario getFuncionarioDetalhe() {
		return funcionarioDetalhe;
	}

	public void setFuncionarioDetalhe(Funcionario funcionarioDetalhe) {
		this.funcionarioDetalhe = funcionarioDetalhe;
	}
		
	public List<Evento> getEventosFuncionario() {
		return eventosFuncionario;
	}

	public void setEventosFuncionario(List<Evento> eventosFuncionario) {
		this.eventosFuncionario = eventosFuncionario;
	}

	public BarChartModel getFaixaEtariaFuncionariosGraficoColunas() {
		return faixaEtariaFuncionariosGraficoColunas;
	}

	public void setFaixaEtariaFuncionariosGraficoColunas(BarChartModel faixaEtariaFuncionariosGraficoColunas) {
		this.faixaEtariaFuncionariosGraficoColunas = faixaEtariaFuncionariosGraficoColunas;
	}

	public PieChartModel getFaixaEtariaFuncionariosGraficoPizza() {
		return faixaEtariaFuncionariosGraficoPizza;
	}

	public void setFaixaEtariaFuncionariosGraficoPizza(PieChartModel faixaEtariaFuncionariosGraficoPizza) {
		this.faixaEtariaFuncionariosGraficoPizza = faixaEtariaFuncionariosGraficoPizza;
	}
	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Relatórios de Funcionarios", "relatorioFuncionario", titulosBread, urlsBread);
		iniciarComponentes();
		return "relatorioFuncionario";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Relatórios de Funcionarios", "relatorioFuncionario");
		iniciarComponentes();
		return "relatorioFuncionario";
	}
	
	public void iniciarComponentes() {
		setPrincipaisFuncionariosQuantidade(5);
		carregarPrincipaisFuncionarios();
		carregarFaixaEtariaFuncionarios();	
	}

	public void carregarPrincipaisFuncionarios() {
		setPrincipaisFuncionariosLista(new RelatorioDao().principaisFuncionarios(getPrincipaisFuncionariosQuantidade()));
		
		if (getPrincipaisFuncionariosLista().size() < getPrincipaisFuncionariosQuantidade()) {
			setPrincipaisFuncionariosQuantidade(getPrincipaisFuncionariosLista().size());
		}
		
		setPrincipaisFuncionariosGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<Funcionario> dado : getPrincipaisFuncionariosLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem().getNome());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de ingressos comprados");
        hbarDataSet.setBackgroundColor(setCorBarras(getPrincipaisFuncionariosLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getPrincipaisFuncionariosLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getPrincipaisFuncionariosGrafico().setData(data);
        getPrincipaisFuncionariosGrafico().setOptions(getOptionsHorizontalBar(getPrincipaisFuncionariosQuantidade() + " funcionarios com mais compras de ingressos."));
        new Grafico<Funcionario>(null).ordenar(getPrincipaisFuncionariosLista());
	}

	public void carregarFaixaEtariaFuncionarios() {
		List<Grafico<String>> resultado = new RelatorioDao().faixaEtariaFuncionarios();
		setFaixaEtariaFuncionariosGraficoColunas(new BarChartModel());
		
		ChartData data = new ChartData();
        BarChartDataSet hbarDataSet = new BarChartDataSet();
  
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de funcionarios");
        hbarDataSet.setBackgroundColor(setCorBarras(5));
        hbarDataSet.setBorderColor(setBordaBarras(5));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getFaixaEtariaFuncionariosGraficoColunas().setData(data);
        getFaixaEtariaFuncionariosGraficoColunas().setOptions(getOptionsBar("Quantidade de funcionarios por faixa etária (Colunas)"));
        
        
        
        
        setFaixaEtariaFuncionariosGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getFaixaEtariaFuncionariosGraficoPizza().setData(data);
        getFaixaEtariaFuncionariosGraficoPizza().setOptions(getOptionsPie("Quantidade de funcionarios por faixa etária (Pizza)"));
      
        
        
	}

	public void detalharFuncionarios(Funcionario funcionario) {
		setFuncionarioDetalhe(new FuncionarioDao().consultarFuncionario(funcionario));
		setEventosFuncionario(new EventoDao().listar(funcionario));
	}
	
 	public String detalhar(Evento evento) {
		detalheEventoBean.setEvento(new EventoDao().consultar(evento));
		detalheEventoBean.setTelaAnterior(this);
		return detalheEventoBean.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
}
