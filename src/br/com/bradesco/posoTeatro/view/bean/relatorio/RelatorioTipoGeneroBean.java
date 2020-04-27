package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import br.com.bradesco.posoTeatro.dao.RelatorioDao;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;

@ManagedBean(name = "relatorioTipoGeneroBean")
@SessionScoped

public class RelatorioTipoGeneroBean extends RelatorioBean implements BeanInterface {
	
	private String mensagem;
	
	//Tipo
	
	private BarChartModel tiposGraficoColunas;
	private PieChartModel tiposGraficoPizza;
	
	private BarChartModel tiposGraficoVendas;
	private PieChartModel tiposGraficoVendasPizza;
	
	private BarChartModel tiposGraficoArrecadacao;
	private List<Grafico<String>> tiposListaArrecadacao;
	
	//Genero
	
	private BarChartModel generosGraficoColunas;
	private PieChartModel generosGraficoPizza;
	
	private BarChartModel generosGraficoVendas;
	private PieChartModel generosGraficoVendasPizza;
	
	private BarChartModel generosGraficoArrecadacao;
	private List<Grafico<String>> generosListaArrecadacao;

 	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public BarChartModel getTiposGraficoColunas() {
		return tiposGraficoColunas;
	}

	public void setTiposGraficoColunas(BarChartModel tiposGraficoColunas) {
		this.tiposGraficoColunas = tiposGraficoColunas;
	}

	public PieChartModel getTiposGraficoPizza() {
		return tiposGraficoPizza;
	}

	public void setTiposGraficoPizza(PieChartModel tiposGraficoPizza) {
		this.tiposGraficoPizza = tiposGraficoPizza;
	}
	
	public BarChartModel getTiposGraficoVendas() {
		return tiposGraficoVendas;
	}

	public void setTiposGraficoVendas(BarChartModel tiposGraficoVendas) {
		this.tiposGraficoVendas = tiposGraficoVendas;
	}

	public PieChartModel getTiposGraficoVendasPizza() {
		return tiposGraficoVendasPizza;
	}

	public void setTiposGraficoVendasPizza(PieChartModel tiposGraficoVendasPizza) {
		this.tiposGraficoVendasPizza = tiposGraficoVendasPizza;
	}

	public BarChartModel getTiposGraficoArrecadacao() {
		return tiposGraficoArrecadacao;
	}

	public void setTiposGraficoArrecadacao(BarChartModel tiposGraficoArrecadacao) {
		this.tiposGraficoArrecadacao = tiposGraficoArrecadacao;
	}
	
	public List<Grafico<String>> getTiposListaArrecadacao() {
		return tiposListaArrecadacao;
	}

	public void setTiposListaArrecadacao(List<Grafico<String>> tiposListaArrecadacao) {
		this.tiposListaArrecadacao = tiposListaArrecadacao;
	}

	public BarChartModel getGenerosGraficoColunas() {
		return generosGraficoColunas;
	}

	public void setGenerosGraficoColunas(BarChartModel generosGraficoColunas) {
		this.generosGraficoColunas = generosGraficoColunas;
	}

	public PieChartModel getGenerosGraficoPizza() {
		return generosGraficoPizza;
	}

	public void setGenerosGraficoPizza(PieChartModel generosGraficoPizza) {
		this.generosGraficoPizza = generosGraficoPizza;
	}
	
	public BarChartModel getGenerosGraficoVendas() {
		return generosGraficoVendas;
	}

	public void setGenerosGraficoVendas(BarChartModel generosGraficoVendas) {
		this.generosGraficoVendas = generosGraficoVendas;
	}

	public PieChartModel getGenerosGraficoVendasPizza() {
		return generosGraficoVendasPizza;
	}

	public void setGenerosGraficoVendasPizza(PieChartModel generosGraficoVendasPizza) {
		this.generosGraficoVendasPizza = generosGraficoVendasPizza;
	}

	public BarChartModel getGenerosGraficoArrecadacao() {
		return generosGraficoArrecadacao;
	}

	public void setGenerosGraficoArrecadacao(BarChartModel generosGraficoArrecadacao) {
		this.generosGraficoArrecadacao = generosGraficoArrecadacao;
	}
	
	public List<Grafico<String>> getGenerosListaArrecadacao() {
		return generosListaArrecadacao;
	}

	public void setGenerosListaArrecadacao(List<Grafico<String>> generosListaArrecadacao) {
		this.generosListaArrecadacao = generosListaArrecadacao;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Relatórios de Tipos e Gêneros", "relatorioTipoGenero", titulosBread, urlsBread);
		iniciarComponentes();
		return "relatorioTipoGenero";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Relatórios de Tipos e Gêneros", "relatorioTipoGenero");
		iniciarComponentes();
		return "relatorioTipoGenero";
	}
	
	public void iniciarComponentes() {
		carregarTipos();	
		carregarTiposVendas();
		carregarTiposArrecadacao();
		carregarGeneros();	
		carregarGenerosVendas();
		carregarGenerosArrecadacao();
	}
	
	public void carregarTipos() {
		List<Grafico<String>> resultado = new RelatorioDao().tipos();
		setTiposGraficoColunas(new BarChartModel());
		
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
        hbarDataSet.setBackgroundColor(setCorBarras(resultado.size()));
        hbarDataSet.setBorderColor(setBordaBarras(resultado.size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getTiposGraficoColunas().setData(data);
        getTiposGraficoColunas().setOptions(getOptionsBar("Quantidade de clientes por faixa etária (Colunas)"));
        
        
        
        
        setTiposGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         
        			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getTiposGraficoPizza().setData(data);
        getTiposGraficoPizza().setOptions(getOptionsPie("Quantidade de clientes por faixa etária (Pizza)"));
      
        
        
	}

	public void carregarTiposVendas() {
		List<Grafico<String>> resultado = new RelatorioDao().tiposVendas();
		setTiposGraficoVendas(new BarChartModel());
		
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
        hbarDataSet.setBackgroundColor(setCorBarras(resultado.size()));
        hbarDataSet.setBorderColor(setBordaBarras(resultado.size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getTiposGraficoVendas().setData(data);
        getTiposGraficoVendas().setOptions(getOptionsBar("Quantidade de compras por faixa etária dos clientes (Colunas)")); 
        
        
        setTiposGraficoVendasPizza(new PieChartModel());
        data = new ChartData();
                 
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         		
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getTiposGraficoVendasPizza().setData(data);
        getTiposGraficoVendasPizza().setOptions(getOptionsPie("Quantidade de compras por faixa etária dos clientes (Pizza)"));
      
	}

	public void carregarTiposArrecadacao() {
		setTiposListaArrecadacao(new RelatorioDao().tiposArrecadacao());
		setTiposGraficoArrecadacao(new BarChartModel());
		
		ChartData data = new ChartData();
        BarChartDataSet hbarDataSet = new BarChartDataSet();
  
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getTiposListaArrecadacao()) {
        	quantidades.add(dado.getValor());
        	nomes.add(dado.getItem());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de ingressos comprados");
        hbarDataSet.setBackgroundColor(setCorBarras(getTiposListaArrecadacao().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getTiposListaArrecadacao().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getTiposGraficoArrecadacao().setData(data);
        getTiposGraficoArrecadacao().setOptions(getOptionsBar("Valor arrecadado em reais por faixa etária dos clientes")); 
        new Grafico<String>(null).ordenar(getTiposListaArrecadacao());
	}

	public void carregarGeneros() {
		List<Grafico<String>> resultado = new RelatorioDao().generos();
		setGenerosGraficoColunas(new BarChartModel());
		
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
        hbarDataSet.setBackgroundColor(setCorBarras(resultado.size()));
        hbarDataSet.setBorderColor(setBordaBarras(resultado.size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getGenerosGraficoColunas().setData(data);
        getGenerosGraficoColunas().setOptions(getOptionsBar("Quantidade de clientes por faixa etária (Colunas)"));
        
        
        
        
        setGenerosGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         		
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getGenerosGraficoPizza().setData(data);
        getGenerosGraficoPizza().setOptions(getOptionsPie("Quantidade de clientes por faixa etária (Pizza)"));
      
        
        
	}

	public void carregarGenerosVendas() {
		List<Grafico<String>> resultado = new RelatorioDao().generosVendas();
		setGenerosGraficoVendas(new BarChartModel());
		
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
        hbarDataSet.setBackgroundColor(setCorBarras(resultado.size()));
        hbarDataSet.setBorderColor(setBordaBarras(resultado.size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getGenerosGraficoVendas().setData(data);
        getGenerosGraficoVendas().setOptions(getOptionsBar("Quantidade de compras por faixa etária dos clientes (Colunas)")); 
        
        
        setGenerosGraficoVendasPizza(new PieChartModel());
        data = new ChartData();
                 
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getGenerosGraficoVendasPizza().setData(data);
        getGenerosGraficoVendasPizza().setOptions(getOptionsPie("Quantidade de compras por faixa etária dos clientes (Pizza)"));
      
	}

	public void carregarGenerosArrecadacao() {
		setGenerosListaArrecadacao(new RelatorioDao().generosArrecadacao());
		setGenerosGraficoArrecadacao(new BarChartModel());
		
		ChartData data = new ChartData();
        BarChartDataSet hbarDataSet = new BarChartDataSet();
  
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getGenerosListaArrecadacao()) {
        	quantidades.add(dado.getValor());
        	nomes.add(dado.getItem());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de ingressos comprados");
        hbarDataSet.setBackgroundColor(setCorBarras(getGenerosListaArrecadacao().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getGenerosListaArrecadacao().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getGenerosGraficoArrecadacao().setData(data);
        getGenerosGraficoArrecadacao().setOptions(getOptionsBar("Valor arrecadado em reais por faixa etária dos clientes")); 
        new Grafico<String>(null).ordenar(getGenerosListaArrecadacao());
	}
	

}
