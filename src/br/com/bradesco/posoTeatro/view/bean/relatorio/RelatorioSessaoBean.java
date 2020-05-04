package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import br.com.bradesco.posoTeatro.dao.RelatorioDao;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;

@ManagedBean(name = "relatorioSessaoBean")
@SessionScoped

public class RelatorioSessaoBean extends RelatorioBean implements BeanInterface {
	
	private String mensagem;
	

	
	private LineChartModel mesQuantidadeGraficoLinhas;
	private PieChartModel mesQuantidadeGraficoPizza;
	
	private LineChartModel mesVendasGraficoLinhas;
	private PieChartModel mesVendasGraficoPizza;
	
	private LineChartModel mesArrecadacaoGrafico;
	private List<Grafico<String>> mesArrecadacaoLista;
	private LineChartModel mesMediaPrecoGrafico;
	private List<Grafico<String>> mesMediaPrecoLista;
	
	
	
	
	
	private HorizontalBarChartModel diaQuantidadeGraficoLinhas;
	private PieChartModel diaQuantidadeGraficoPizza;
	
	private HorizontalBarChartModel diaVendasGraficoLinhas;
	private PieChartModel diaVendasGraficoPizza;
	
	private HorizontalBarChartModel diaArrecadacaoGrafico;
	private List<Grafico<String>> diaArrecadacaoLista;
	private HorizontalBarChartModel diaMediaPrecoGrafico;
	private List<Grafico<String>> diaMediaPrecoLista;
	
	
	
	
	
	private HorizontalBarChartModel horaQuantidadeGraficoLinhas;
	private PieChartModel horaQuantidadeGraficoPizza;
	
	private HorizontalBarChartModel horaVendasGraficoLinhas;
	private PieChartModel horaVendasGraficoPizza;
	
	private HorizontalBarChartModel horaArrecadacaoGrafico;
	private List<Grafico<String>> horaArrecadacaoLista;
	private HorizontalBarChartModel horaMediaPrecoGrafico;
	private List<Grafico<String>> horaMediaPrecoLista;
	
	
	
	
	

 	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public LineChartModel getMesQuantidadeGraficoLinhas() {
		return mesQuantidadeGraficoLinhas;
	}

	public void setMesQuantidadeGraficoLinhas(LineChartModel mesQuantidadeGraficoLinhas) {
		this.mesQuantidadeGraficoLinhas = mesQuantidadeGraficoLinhas;
	}

	public PieChartModel getMesQuantidadeGraficoPizza() {
		return mesQuantidadeGraficoPizza;
	}

	public void setMesQuantidadeGraficoPizza(PieChartModel mesQuantidadeGraficoPizza) {
		this.mesQuantidadeGraficoPizza = mesQuantidadeGraficoPizza;
	}

	public LineChartModel getMesVendasGraficoLinhas() {
		return mesVendasGraficoLinhas;
	}

	public void setMesVendasGraficoLinhas(LineChartModel mesVendasGraficoLinhas) {
		this.mesVendasGraficoLinhas = mesVendasGraficoLinhas;
	}

	public PieChartModel getMesVendasGraficoPizza() {
		return mesVendasGraficoPizza;
	}

	public void setMesVendasGraficoPizza(PieChartModel mesVendasGraficoPizza) {
		this.mesVendasGraficoPizza = mesVendasGraficoPizza;
	}

	public LineChartModel getMesArrecadacaoGrafico() {
		return mesArrecadacaoGrafico;
	}

	public void setMesArrecadacaoGrafico(LineChartModel mesArrecadacaoGrafico) {
		this.mesArrecadacaoGrafico = mesArrecadacaoGrafico;
	}

	public List<Grafico<String>> getMesArrecadacaoLista() {
		return mesArrecadacaoLista;
	}

	public void setMesArrecadacaoLista(List<Grafico<String>> mesArrecadacaoLista) {
		this.mesArrecadacaoLista = mesArrecadacaoLista;
	}

	public LineChartModel getMesMediaPrecoGrafico() {
		return mesMediaPrecoGrafico;
	}

	public void setMesMediaPrecoGrafico(LineChartModel mesMediaPrecoGrafico) {
		this.mesMediaPrecoGrafico = mesMediaPrecoGrafico;
	}

	public List<Grafico<String>> getMesMediaPrecoLista() {
		return mesMediaPrecoLista;
	}

	public void setMesMediaPrecoLista(List<Grafico<String>> mesMediaPrecoLista) {
		this.mesMediaPrecoLista = mesMediaPrecoLista;
	}

	
	
	
	
	
	public HorizontalBarChartModel getDiaQuantidadeGraficoLinhas() {
		return diaQuantidadeGraficoLinhas;
	}

	public void setDiaQuantidadeGraficoLinhas(HorizontalBarChartModel diaQuantidadeGraficoLinhas) {
		this.diaQuantidadeGraficoLinhas = diaQuantidadeGraficoLinhas;
	}

	public PieChartModel getDiaQuantidadeGraficoPizza() {
		return diaQuantidadeGraficoPizza;
	}

	public void setDiaQuantidadeGraficoPizza(PieChartModel diaQuantidadeGraficoPizza) {
		this.diaQuantidadeGraficoPizza = diaQuantidadeGraficoPizza;
	}

	public HorizontalBarChartModel getDiaVendasGraficoLinhas() {
		return diaVendasGraficoLinhas;
	}

	public void setDiaVendasGraficoLinhas(HorizontalBarChartModel diaVendasGraficoLinhas) {
		this.diaVendasGraficoLinhas = diaVendasGraficoLinhas;
	}

	public PieChartModel getDiaVendasGraficoPizza() {
		return diaVendasGraficoPizza;
	}

	public void setDiaVendasGraficoPizza(PieChartModel diaVendasGraficoPizza) {
		this.diaVendasGraficoPizza = diaVendasGraficoPizza;
	}

	public HorizontalBarChartModel getDiaArrecadacaoGrafico() {
		return diaArrecadacaoGrafico;
	}

	public void setDiaArrecadacaoGrafico(HorizontalBarChartModel diaArrecadacaoGrafico) {
		this.diaArrecadacaoGrafico = diaArrecadacaoGrafico;
	}

	public List<Grafico<String>> getDiaArrecadacaoLista() {
		return diaArrecadacaoLista;
	}

	public void setDiaArrecadacaoLista(List<Grafico<String>> diaArrecadacaoLista) {
		this.diaArrecadacaoLista = diaArrecadacaoLista;
	}

	public HorizontalBarChartModel getDiaMediaPrecoGrafico() {
		return diaMediaPrecoGrafico;
	}

	public void setDiaMediaPrecoGrafico(HorizontalBarChartModel diaMediaPrecoGrafico) {
		this.diaMediaPrecoGrafico = diaMediaPrecoGrafico;
	}

	public List<Grafico<String>> getDiaMediaPrecoLista() {
		return diaMediaPrecoLista;
	}

	public void setDiaMediaPrecoLista(List<Grafico<String>> diaMediaPrecoLista) {
		this.diaMediaPrecoLista = diaMediaPrecoLista;
	}

	
	
	
	
	
	
	
	
	
	
	public HorizontalBarChartModel getHoraQuantidadeGraficoLinhas() {
		return horaQuantidadeGraficoLinhas;
	}

	public void setHoraQuantidadeGraficoLinhas(HorizontalBarChartModel horaQuantidadeGraficoLinhas) {
		this.horaQuantidadeGraficoLinhas = horaQuantidadeGraficoLinhas;
	}

	public PieChartModel getHoraQuantidadeGraficoPizza() {
		return horaQuantidadeGraficoPizza;
	}

	public void setHoraQuantidadeGraficoPizza(PieChartModel horaQuantidadeGraficoPizza) {
		this.horaQuantidadeGraficoPizza = horaQuantidadeGraficoPizza;
	}

	public HorizontalBarChartModel getHoraVendasGraficoLinhas() {
		return horaVendasGraficoLinhas;
	}

	public void setHoraVendasGraficoLinhas(HorizontalBarChartModel horaVendasGraficoLinhas) {
		this.horaVendasGraficoLinhas = horaVendasGraficoLinhas;
	}

	public PieChartModel getHoraVendasGraficoPizza() {
		return horaVendasGraficoPizza;
	}

	public void setHoraVendasGraficoPizza(PieChartModel horaVendasGraficoPizza) {
		this.horaVendasGraficoPizza = horaVendasGraficoPizza;
	}

	public HorizontalBarChartModel getHoraArrecadacaoGrafico() {
		return horaArrecadacaoGrafico;
	}

	public void setHoraArrecadacaoGrafico(HorizontalBarChartModel horaArrecadacaoGrafico) {
		this.horaArrecadacaoGrafico = horaArrecadacaoGrafico;
	}

	public List<Grafico<String>> getHoraArrecadacaoLista() {
		return horaArrecadacaoLista;
	}

	public void setHoraArrecadacaoLista(List<Grafico<String>> horaArrecadacaoLista) {
		this.horaArrecadacaoLista = horaArrecadacaoLista;
	}

	public HorizontalBarChartModel getHoraMediaPrecoGrafico() {
		return horaMediaPrecoGrafico;
	}

	public void setHoraMediaPrecoGrafico(HorizontalBarChartModel horaMediaPrecoGrafico) {
		this.horaMediaPrecoGrafico = horaMediaPrecoGrafico;
	}

	public List<Grafico<String>> getHoraMediaPrecoLista() {
		return horaMediaPrecoLista;
	}

	public void setHoraMediaPrecoLista(List<Grafico<String>> horaMediaPrecoLista) {
		this.horaMediaPrecoLista = horaMediaPrecoLista;
	}

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Relatórios de Sessões", "relatorioSessao", titulosBread, urlsBread);
		iniciarComponentes();
		return "relatorioSessao";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Relatórios de Sessões", "relatorioSessao");
		iniciarComponentes();
		return "relatorioSessao";
	}
	
	public void iniciarComponentes() {
		carregarMesQuantidade();
		carregarMesVendas();
		carregarMesArrecadacao();
		carregarMesMediaPreco();

		carregarDiaQuantidade();
		carregarDiaVendas();
		carregarDiaArrecadacao();
		carregarDiaMediaPreco();
		
		carregarHoraQuantidade();
		carregarHoraVendas();
		carregarHoraArrecadacao();
		carregarHoraMediaPreco();
	}

	public void carregarMesQuantidade() {
		List<Grafico<String>> resultado = new RelatorioDao().sessaoMesQuantidade();
		
		setMesQuantidadeGraficoLinhas(new LineChartModel());
		
		ChartData data = new ChartData();
		LineChartDataSet hbarDataSet = new LineChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setFill(false);
        hbarDataSet.setBorderColor(setCorBarras(1).get(0));
        hbarDataSet.setBackgroundColor(setCorBarras(1).get(0));
        hbarDataSet.setLineTension(0.3); 
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Quantidade de sessões marcadas");
        hbarDataSet.setBorderColor(setBordaBarras(12).get(0));
        
        data.addChartDataSet(hbarDataSet);        
        getMesQuantidadeGraficoLinhas().setData(data);
        getMesQuantidadeGraficoLinhas().setOptions(getOptionsLine("Comparação mensal de sessões agendadas (Colunas)"));
        
        
        
        
        setMesQuantidadeGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getMesQuantidadeGraficoPizza().setData(data);
        getMesQuantidadeGraficoPizza().setOptions(getOptionsPie("Comparação mensal de sessões agendadas (Pizza)"));
      
        
	}

	public void carregarMesVendas() {
		List<Grafico<String>> resultado = new RelatorioDao().sessaoMesVendas();
		
		setMesVendasGraficoLinhas(new LineChartModel());
		
		ChartData data = new ChartData();
		LineChartDataSet hbarDataSet = new LineChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setFill(false);
        hbarDataSet.setBorderColor(setCorBarras(1).get(0));
        hbarDataSet.setBackgroundColor(setCorBarras(1).get(0));
        hbarDataSet.setLineTension(0.3); 
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Quantidade de ingressos vendidos");
        hbarDataSet.setBorderColor(setBordaBarras(12).get(0));
        
        data.addChartDataSet(hbarDataSet);        
        getMesVendasGraficoLinhas().setData(data);
        getMesVendasGraficoLinhas().setOptions(getOptionsLine("Comparação mensal de vendas de ingressos. (Colunas)"));
        
        
        
        
        setMesVendasGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getMesVendasGraficoPizza().setData(data);
        getMesVendasGraficoPizza().setOptions(getOptionsPie("Comparação mensal de vendas de ingressos. (Pizza)"));
      
        
	}

	public void carregarMesArrecadacao() {
		setMesArrecadacaoLista(new RelatorioDao().sessaoMesArrecadacao());
		setMesArrecadacaoGrafico(new LineChartModel());
		
		ChartData data = new ChartData();
		LineChartDataSet hbarDataSet = new LineChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getMesArrecadacaoLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setFill(false);
        hbarDataSet.setBorderColor(setCorBarras(1).get(0));
        hbarDataSet.setBackgroundColor(setCorBarras(1).get(0));
        hbarDataSet.setLineTension(0.3); 
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Valor arrecadado");
        hbarDataSet.setBorderColor(setBordaBarras(12).get(0));
        
        data.addChartDataSet(hbarDataSet);        
        getMesArrecadacaoGrafico().setData(data);
        getMesArrecadacaoGrafico().setOptions(getOptionsLine("Comparação mensal de arrecadação."));
        
        new Grafico<String>(null).ordenar(getMesArrecadacaoLista());

  
      
        
	}

	public void carregarMesMediaPreco() {
		setMesMediaPrecoLista(new RelatorioDao().sessaoMesValorMedio());
		setMesMediaPrecoGrafico(new LineChartModel());
		
		ChartData data = new ChartData();
		LineChartDataSet hbarDataSet = new LineChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getMesMediaPrecoLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setFill(false);
        hbarDataSet.setBorderColor(setCorBarras(1).get(0));
        hbarDataSet.setBackgroundColor(setCorBarras(1).get(0));
        hbarDataSet.setLineTension(0.3); 
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Valor médio");
        hbarDataSet.setBorderColor(setBordaBarras(12).get(0));
        
        data.addChartDataSet(hbarDataSet);        
        getMesMediaPrecoGrafico().setData(data);
        getMesMediaPrecoGrafico().setOptions(getOptionsLine("Comparação mensal da média de valores por sessão."));
   
        new Grafico<String>(null).ordenar(getMesMediaPrecoLista());

  
      
        
	}

	
	
	public void carregarDiaQuantidade() {
		List<Grafico<String>> resultado = new RelatorioDao().sessaoDiaQuantidade();
		
		setDiaQuantidadeGraficoLinhas(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Quantidade de sessões marcadas");
        
        
        data.addChartDataSet(hbarDataSet);        
        getDiaQuantidadeGraficoLinhas().setData(data);
        getDiaQuantidadeGraficoLinhas().setOptions(getOptionsHorizontalBar("Comparação semanal de sessões agendadas (Colunas)"));
        
        
        
        
        setDiaQuantidadeGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getDiaQuantidadeGraficoPizza().setData(data);
        getDiaQuantidadeGraficoPizza().setOptions(getOptionsPie("Comparação semanal de sessões agendadas (Pizza)"));
      
        
	}

	public void carregarDiaVendas() {
		List<Grafico<String>> resultado = new RelatorioDao().sessaoDiaVendas();
		
		setDiaVendasGraficoLinhas(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Quantidade de ingressos vendidos");
        
        
        data.addChartDataSet(hbarDataSet);        
        getDiaVendasGraficoLinhas().setData(data);
        getDiaVendasGraficoLinhas().setOptions(getOptionsHorizontalBar("Comparação semanal de vendas de ingressos. (Colunas)"));
        
        
        
        
        setDiaVendasGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getDiaVendasGraficoPizza().setData(data);
        getDiaVendasGraficoPizza().setOptions(getOptionsPie("Comparação semanal de vendas de ingressos. (Pizza)"));
      
        
	}

	public void carregarDiaArrecadacao() {
		setDiaArrecadacaoLista(new RelatorioDao().sessaoDiaArrecadacao());
		setDiaArrecadacaoGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getDiaArrecadacaoLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Valor arrecadado");
        
        data.addChartDataSet(hbarDataSet);        
        getDiaArrecadacaoGrafico().setData(data);
        getDiaArrecadacaoGrafico().setOptions(getOptionsHorizontalBar("Comparação semanal de arrecadação."));
        
        new Grafico<String>(null).ordenar(getDiaArrecadacaoLista());

  
      
        
	}

	public void carregarDiaMediaPreco() {
		setDiaMediaPrecoLista(new RelatorioDao().sessaoDiaValorMedio());
		setDiaMediaPrecoGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getDiaMediaPrecoLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Valor médio");
        
        
        data.addChartDataSet(hbarDataSet);        
        getDiaMediaPrecoGrafico().setData(data);
        getDiaMediaPrecoGrafico().setOptions(getOptionsHorizontalBar("Comparação semanal da média de valores por sessão."));
        
        new Grafico<String>(null).ordenar(getDiaMediaPrecoLista());

  
      
        
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void carregarHoraQuantidade() {
		List<Grafico<String>> resultado = new RelatorioDao().sessaoHoraQuantidade();
		
		setHoraQuantidadeGraficoLinhas(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Quantidade de sessões marcadas");
        
        
        data.addChartDataSet(hbarDataSet);        
        getHoraQuantidadeGraficoLinhas().setData(data);
        getHoraQuantidadeGraficoLinhas().setOptions(getOptionsHorizontalBar("Comparação diária de sessões agendadas (Colunas)"));
        
        
        
        
        setHoraQuantidadeGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getHoraQuantidadeGraficoPizza().setData(data);
        getHoraQuantidadeGraficoPizza().setOptions(getOptionsPie("Comparação diária de sessões agendadas (Pizza)"));
      
        
	}

	public void carregarHoraVendas() {
		List<Grafico<String>> resultado = new RelatorioDao().sessaoHoraVendas();
		
		setHoraVendasGraficoLinhas(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : resultado) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Quantidade de ingressos vendidos");
        
        
        data.addChartDataSet(hbarDataSet);        
        getHoraVendasGraficoLinhas().setData(data);
        getHoraVendasGraficoLinhas().setOptions(getOptionsHorizontalBar("Comparação diária de vendas de ingressos. (Colunas)"));
        
        
        
        
        setHoraVendasGraficoPizza(new PieChartModel());
        data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(quantidades);
         			
        dataSet.setBackgroundColor(getCores());
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getHoraVendasGraficoPizza().setData(data);
        getHoraVendasGraficoPizza().setOptions(getOptionsPie("Comparação diária de vendas de ingressos. (Pizza)"));
      
        
	}

	public void carregarHoraArrecadacao() {
		setHoraArrecadacaoLista(new RelatorioDao().sessaoHoraArrecadacao());
		setHoraArrecadacaoGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getHoraArrecadacaoLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Valor arrecadado");
        
        data.addChartDataSet(hbarDataSet);        
        getHoraArrecadacaoGrafico().setData(data);
        getHoraArrecadacaoGrafico().setOptions(getOptionsHorizontalBar("Comparação diária de arrecadação."));
        
        new Grafico<String>(null).ordenar(getHoraArrecadacaoLista());

  
      
        
	}

	public void carregarHoraMediaPreco() {
		setHoraMediaPrecoLista(new RelatorioDao().sessaoHoraValorMedio());
		setHoraMediaPrecoGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
		HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<String> dado : getHoraMediaPrecoLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem());
		}

        
        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setBorderColor(setCorBarras(7));
        hbarDataSet.setBackgroundColor(setCorBarras(7));
        hbarDataSet.setBorderWidth(1);
        hbarDataSet.setLabel("Valor médio");
        
        
        data.addChartDataSet(hbarDataSet);        
        getHoraMediaPrecoGrafico().setData(data);
        getHoraMediaPrecoGrafico().setOptions(getOptionsHorizontalBar("Comparação diária da média de valores por sessão."));
        
        new Grafico<String>(null).ordenar(getHoraMediaPrecoLista());

  
      
        
	}

}
