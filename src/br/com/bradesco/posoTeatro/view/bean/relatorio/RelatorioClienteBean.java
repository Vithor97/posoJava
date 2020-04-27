package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.dao.RelatorioDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;

@ManagedBean(name = "relatorioClienteBean")
@SessionScoped

public class RelatorioClienteBean extends RelatorioBean implements BeanInterface {
	
	private String mensagem;
	
	//Clientes
	private HorizontalBarChartModel principaisClientesGrafico;
	private List<Grafico<Pessoa>> principaisClientesLista;
	private int principaisClientesQuantidade;
	private Pessoa clienteDetalhe;
	private List<Ingresso> comprasCliente;
	
	private BarChartModel faixaEtariaClientesGraficoColunas;
	private PieChartModel faixaEtariaClientesGraficoPizza;
	
	private BarChartModel faixaEtariaClientesGraficoVendas;
	private PieChartModel faixaEtariaClientesGraficoVendasPizza;
	private BarChartModel faixaEtariaClientesGraficoArrecadacao;
	private List<Grafico<String>> faixaEtariaClientesListaArrecadacao;

 	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public HorizontalBarChartModel getPrincipaisClientesGrafico() {
		return principaisClientesGrafico;
	}

	public void setPrincipaisClientesGrafico(HorizontalBarChartModel principaisClientesGrafico) {
		this.principaisClientesGrafico = principaisClientesGrafico;
	}

	public List<Grafico<Pessoa>> getPrincipaisClientesLista() {
		return principaisClientesLista;
	}

	public void setPrincipaisClientesLista(List<Grafico<Pessoa>> principaisClientesLista) {
		this.principaisClientesLista = principaisClientesLista;
	}
	
	public int getPrincipaisClientesQuantidade() {
		return principaisClientesQuantidade;
	}

	public void setPrincipaisClientesQuantidade(int principaisClientesQuantidade) {
		if (principaisClientesQuantidade > 0) {
			this.principaisClientesQuantidade = principaisClientesQuantidade;
		} else {
			this.principaisClientesQuantidade = 1;
		}
	}

	public Pessoa getClienteDetalhe() {
		return clienteDetalhe;
	}

	public void setClienteDetalhe(Pessoa clienteDetalhe) {
		this.clienteDetalhe = clienteDetalhe;
	}
		
	public List<Ingresso> getComprasCliente() {
		return comprasCliente;
	}

	public void setComprasCliente(List<Ingresso> comprasCliente) {
		this.comprasCliente = comprasCliente;
	}

	public BarChartModel getFaixaEtariaClientesGraficoColunas() {
		return faixaEtariaClientesGraficoColunas;
	}

	public void setFaixaEtariaClientesGraficoColunas(BarChartModel faixaEtariaClientesGraficoColunas) {
		this.faixaEtariaClientesGraficoColunas = faixaEtariaClientesGraficoColunas;
	}

	public PieChartModel getFaixaEtariaClientesGraficoPizza() {
		return faixaEtariaClientesGraficoPizza;
	}

	public void setFaixaEtariaClientesGraficoPizza(PieChartModel faixaEtariaClientesGraficoPizza) {
		this.faixaEtariaClientesGraficoPizza = faixaEtariaClientesGraficoPizza;
	}
	
	public BarChartModel getFaixaEtariaClientesGraficoVendas() {
		return faixaEtariaClientesGraficoVendas;
	}

	public void setFaixaEtariaClientesGraficoVendas(BarChartModel faixaEtariaClientesGraficoVendas) {
		this.faixaEtariaClientesGraficoVendas = faixaEtariaClientesGraficoVendas;
	}

	public PieChartModel getFaixaEtariaClientesGraficoVendasPizza() {
		return faixaEtariaClientesGraficoVendasPizza;
	}

	public void setFaixaEtariaClientesGraficoVendasPizza(PieChartModel faixaEtariaClientesGraficoVendasPizza) {
		this.faixaEtariaClientesGraficoVendasPizza = faixaEtariaClientesGraficoVendasPizza;
	}

	public BarChartModel getFaixaEtariaClientesGraficoArrecadacao() {
		return faixaEtariaClientesGraficoArrecadacao;
	}

	public void setFaixaEtariaClientesGraficoArrecadacao(BarChartModel faixaEtariaClientesGraficoArrecadacao) {
		this.faixaEtariaClientesGraficoArrecadacao = faixaEtariaClientesGraficoArrecadacao;
	}
	
	public List<Grafico<String>> getFaixaEtariaClientesListaArrecadacao() {
		return faixaEtariaClientesListaArrecadacao;
	}

	public void setFaixaEtariaClientesListaArrecadacao(List<Grafico<String>> faixaEtariaClientesListaArrecadacao) {
		this.faixaEtariaClientesListaArrecadacao = faixaEtariaClientesListaArrecadacao;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Relatórios de Clientes", "relatorioCliente", titulosBread, urlsBread);
		iniciarComponentes();
		return "relatorioCliente";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Relatórios de Clientes", "relatorioCliente");
		iniciarComponentes();
		return "relatorioCliente";
	}
	
	public void iniciarComponentes() {
		setPrincipaisClientesQuantidade(5);
		carregarPrincipaisClientes();
		carregarFaixaEtariaClientes();	
		carregarFaixaEtariaClientesVendas();
		carregarFaixaEtariaClientesArrecadacao();
	}

	public void carregarPrincipaisClientes() {
		setPrincipaisClientesLista(new RelatorioDao().principaisClientes(getPrincipaisClientesQuantidade()));
		
		if (getPrincipaisClientesLista().size() < getPrincipaisClientesQuantidade()) {
			setPrincipaisClientesQuantidade(getPrincipaisClientesLista().size());
		}
		
		setPrincipaisClientesGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<Pessoa> dado : getPrincipaisClientesLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem().getNome());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de ingressos comprados");
        hbarDataSet.setBackgroundColor(setCorBarras(getPrincipaisClientesLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getPrincipaisClientesLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getPrincipaisClientesGrafico().setData(data);
        getPrincipaisClientesGrafico().setOptions(getOptionsHorizontalBar(getPrincipaisClientesQuantidade() + " clientes com mais compras de ingressos."));
        new Grafico<Pessoa>(null).ordenar(getPrincipaisClientesLista());
	}

	public void carregarFaixaEtariaClientes() {
		List<Grafico<String>> resultado = new RelatorioDao().faixaEtariaClientes();
		setFaixaEtariaClientesGraficoColunas(new BarChartModel());
		
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
        hbarDataSet.setLabel("Quantidade de clientes");
        hbarDataSet.setBackgroundColor(setCorBarras(5));
        hbarDataSet.setBorderColor(setBordaBarras(5));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getFaixaEtariaClientesGraficoColunas().setData(data);
        getFaixaEtariaClientesGraficoColunas().setOptions(getOptionsBar("Quantidade de clientes por faixa etária (Colunas)"));
        
        
        
        
        setFaixaEtariaClientesGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getFaixaEtariaClientesGraficoPizza().setData(data);
        getFaixaEtariaClientesGraficoPizza().setOptions(getOptionsPie("Quantidade de clientes por faixa etária (Pizza)"));
      
        
        
	}

	public void carregarFaixaEtariaClientesVendas() {
		List<Grafico<String>> resultado = new RelatorioDao().faixaEtariaVendas();
		setFaixaEtariaClientesGraficoVendas(new BarChartModel());
		
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
        hbarDataSet.setLabel("Quantidade de ingressos comprados");
        hbarDataSet.setBackgroundColor(setCorBarras(5));
        hbarDataSet.setBorderColor(setBordaBarras(5));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getFaixaEtariaClientesGraficoVendas().setData(data);
        getFaixaEtariaClientesGraficoVendas().setOptions(getOptionsBar("Quantidade de compras por faixa etária dos clientes (Colunas)")); 
        
        
        setFaixaEtariaClientesGraficoVendasPizza(new PieChartModel());
        data = new ChartData();
                 
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getFaixaEtariaClientesGraficoVendasPizza().setData(data);
        getFaixaEtariaClientesGraficoVendasPizza().setOptions(getOptionsPie("Quantidade de compras por faixa etária dos clientes (Pizza)"));
      
	}

	public void carregarFaixaEtariaClientesArrecadacao() {
		setFaixaEtariaClientesListaArrecadacao(new RelatorioDao().faixaEtariaArrecadacao());
		setFaixaEtariaClientesGraficoArrecadacao(new BarChartModel());
		
		ChartData data = new ChartData();
        BarChartDataSet hbarDataSet = new BarChartDataSet();
  
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getFaixaEtariaClientesListaArrecadacao()) {
        	quantidades.add(dado.getValor());
        	nomes.add(dado.getItem());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de ingressos comprados");
        hbarDataSet.setBackgroundColor(setCorBarras(5));
        hbarDataSet.setBorderColor(setBordaBarras(5));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getFaixaEtariaClientesGraficoArrecadacao().setData(data);
        getFaixaEtariaClientesGraficoArrecadacao().setOptions(getOptionsBar("Valor arrecadado em reais por faixa etária dos clientes")); 
        new Grafico<String>(null).ordenar(getFaixaEtariaClientesListaArrecadacao());
	}
	
	public void detalharPessoas(Pessoa pessoa) {
		setClienteDetalhe(new PessoaDao().consultar(pessoa));
		setComprasCliente(new IngressoDao().listar(pessoa));
	}
	
}
