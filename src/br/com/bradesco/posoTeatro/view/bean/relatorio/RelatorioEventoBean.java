package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartOptions;

import br.com.bradesco.posoTeatro.dao.EventoDao;
import br.com.bradesco.posoTeatro.dao.RelatorioDao;
import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;
import br.com.bradesco.posoTeatro.view.bean.evento.AlterarEventoBean;
import br.com.bradesco.posoTeatro.view.bean.evento.DetalheEventoBean;

@ManagedBean(name = "relatorioEventoBean")
@SessionScoped

public class RelatorioEventoBean extends PosoBean implements BeanInterface {
	
	@ManagedProperty("#{detalheEventoBean}")
	private DetalheEventoBean detalheEventoBean;

	private String mensagem;
	
	//Eventos
	private HorizontalBarChartModel publicoEventosGrafico;
	private List<Grafico<Evento>> publicoEventosLista;
	private int publicoEventosQuantidade;
	
	private HorizontalBarChartModel publicoMediaEventosGrafico;
	private List<Grafico<Evento>> publicoMediaEventosLista;
	private int publicoMediaEventosQuantidade;
	
	private HorizontalBarChartModel arrecadacaoGrafico;
	private List<Grafico<Evento>> arrecadacaoLista;
	private int arrecadacaoQuantidade;
	
	private HorizontalBarChartModel arrecadacaoMediaGrafico;
	private List<Grafico<Evento>> arrecadacaoMediaLista;
	private int arrecadacaoMediaQuantidade;

	public void setDetalheEventoBean(DetalheEventoBean detalheEventoBean) {
		this.detalheEventoBean = detalheEventoBean;
	}

 	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public HorizontalBarChartModel getPublicoEventosGrafico() {
		return publicoEventosGrafico;
	}

	public void setPublicoEventosGrafico(HorizontalBarChartModel publicoEventosGrafico) {
		this.publicoEventosGrafico = publicoEventosGrafico;
	}

	public List<Grafico<Evento>> getPublicoEventosLista() {
		return publicoEventosLista;
	}

	public void setPublicoEventosLista(List<Grafico<Evento>> publicoEventosLista) {
		this.publicoEventosLista = publicoEventosLista;
	}

	public int getPublicoEventosQuantidade() {
		return publicoEventosQuantidade;
	}

	public void setPublicoEventosQuantidade(int publicoEventosQuantidade) {
		this.publicoEventosQuantidade = publicoEventosQuantidade;
	}

	
	
	
	
	
	public HorizontalBarChartModel getPublicoMediaEventosGrafico() {
		return publicoMediaEventosGrafico;
	}

	public void setPublicoMediaEventosGrafico(HorizontalBarChartModel publicoMediaEventosGrafico) {
		this.publicoMediaEventosGrafico = publicoMediaEventosGrafico;
	}

	public List<Grafico<Evento>> getPublicoMediaEventosLista() {
		return publicoMediaEventosLista;
	}

	public void setPublicoMediaEventosLista(List<Grafico<Evento>> publicoMediaEventosLista) {
		this.publicoMediaEventosLista = publicoMediaEventosLista;
	}

	public int getPublicoMediaEventosQuantidade() {
		return publicoMediaEventosQuantidade;
	}

	public void setPublicoMediaEventosQuantidade(int publicoMediaEventosQuantidade) {
		this.publicoMediaEventosQuantidade = publicoMediaEventosQuantidade;
	}

	
	public HorizontalBarChartModel getArrecadacaoGrafico() {
		return arrecadacaoGrafico;
	}

	public void setArrecadacaoGrafico(HorizontalBarChartModel arrecadacaoGrafico) {
		this.arrecadacaoGrafico = arrecadacaoGrafico;
	}

	public List<Grafico<Evento>> getArrecadacaoLista() {
		return arrecadacaoLista;
	}

	public void setArrecadacaoLista(List<Grafico<Evento>> arrecadacaoLista) {
		this.arrecadacaoLista = arrecadacaoLista;
	}

	public int getArrecadacaoQuantidade() {
		return arrecadacaoQuantidade;
	}

	public void setArrecadacaoQuantidade(int arrecadacaoQuantidade) {
		this.arrecadacaoQuantidade = arrecadacaoQuantidade;
	}
	
	public HorizontalBarChartModel getArrecadacaoMediaGrafico() {
		return arrecadacaoMediaGrafico;
	}

	public void setArrecadacaoMediaGrafico(HorizontalBarChartModel arrecadacaoMediaGrafico) {
		this.arrecadacaoMediaGrafico = arrecadacaoMediaGrafico;
	}

	public List<Grafico<Evento>> getArrecadacaoMediaLista() {
		return arrecadacaoMediaLista;
	}

	public void setArrecadacaoMediaLista(List<Grafico<Evento>> arrecadacaoMediaLista) {
		this.arrecadacaoMediaLista = arrecadacaoMediaLista;
	}

	public int getArrecadacaoMediaQuantidade() {
		return arrecadacaoMediaQuantidade;
	}

	public void setArrecadacaoMediaQuantidade(int arrecadacaoMediaQuantidade) {
		this.arrecadacaoMediaQuantidade = arrecadacaoMediaQuantidade;
	}

	
	
	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Relatórios de Eventos", "relatorioEvento", titulosBread, urlsBread);
		iniciarComponentes();
		return "relatorioEvento";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Relatórios de Eventos", "relatorioEvento");
		iniciarComponentes();
		return "relatorioEvento";
	}
	
	public void iniciarComponentes() {
		setPublicoEventosQuantidade(5);
		carregarPublicoEventos();
		
		setPublicoMediaEventosQuantidade(5);
		carregarPublicoMediaEventos();
		
		setArrecadacaoQuantidade(5);
		carregarArrecadacao();
		
		setArrecadacaoMediaQuantidade(5);
		carregarArrecadacaoMedia();
	}

	public void carregarPublicoEventos() {
		setPublicoEventosLista(new RelatorioDao().publicoEventos(getPublicoEventosQuantidade()));
		
		if (getPublicoEventosLista().size() < getPublicoEventosQuantidade()) {
			setPublicoEventosQuantidade(getPublicoEventosLista().size());
		}
		
		setPublicoEventosGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<Evento> dado : getPublicoEventosLista()) {
        	quantidades.add(dado.getValor().intValue());
        	nomes.add(dado.getItem().getDescricao());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Quantidade de espectadores");
        hbarDataSet.setBackgroundColor(setCorBarras(getPublicoEventosLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getPublicoEventosLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getPublicoEventosGrafico().setData(data);
        getPublicoEventosGrafico().setOptions(getOptionsBar(getPublicoEventosQuantidade() + " eventos com mais público."));
        ordenar(getPublicoEventosLista());
	}

	public void carregarPublicoMediaEventos() {
		setPublicoMediaEventosLista(new RelatorioDao().publicoMediaEventos(getPublicoMediaEventosQuantidade()));
		
		if (getPublicoMediaEventosLista().size() < getPublicoMediaEventosQuantidade()) {
			setPublicoMediaEventosQuantidade(getPublicoMediaEventosLista().size());
		}
		
		setPublicoMediaEventosGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<Evento> dado : getPublicoMediaEventosLista()) {
        	quantidades.add(dado.getValor());
        	nomes.add(dado.getItem().getDescricao());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Média de espectadores por sessão");
        hbarDataSet.setBackgroundColor(setCorBarras(getPublicoMediaEventosLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getPublicoMediaEventosLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getPublicoMediaEventosGrafico().setData(data);
        getPublicoMediaEventosGrafico().setOptions(getOptionsBar(getPublicoMediaEventosQuantidade() + " eventos com maior média de público."));
        ordenar(getPublicoMediaEventosLista());

	}

	public void carregarArrecadacao() {
		setArrecadacaoLista(new RelatorioDao().arrecadacao(getArrecadacaoQuantidade()));
		
		if (getArrecadacaoLista().size() < getArrecadacaoQuantidade()) {
			setArrecadacaoQuantidade(getArrecadacaoLista().size());
		}
		
		setArrecadacaoGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<Evento> dado : getArrecadacaoLista()) {
        	quantidades.add(dado.getValor());
        	nomes.add(dado.getItem().getDescricao());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Valor arrecadado");
        hbarDataSet.setBackgroundColor(setCorBarras(getArrecadacaoLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getArrecadacaoLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getArrecadacaoGrafico().setData(data);
        getArrecadacaoGrafico().setOptions(getOptionsBar(getArrecadacaoQuantidade() + " eventos com maior arrecadação."));
        ordenar(getArrecadacaoLista());

	}
	
	public void carregarArrecadacaoMedia() {
		setArrecadacaoMediaLista(new RelatorioDao().arrecadacaoMedia(getArrecadacaoQuantidade()));
		
		if (getArrecadacaoMediaLista().size() < getArrecadacaoMediaQuantidade()) {
			setArrecadacaoMediaQuantidade(getArrecadacaoMediaLista().size());
		}
		
		setArrecadacaoMediaGrafico(new HorizontalBarChartModel());
		
		ChartData data = new ChartData();
        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
         
        List<Number> quantidades = new ArrayList<Number>();
        List<String> nomes = new ArrayList<String>();
        
        for (Grafico<Evento> dado : getArrecadacaoMediaLista()) {
        	quantidades.add(dado.getValor());
        	nomes.add(dado.getItem().getDescricao());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("Média de arrecadação por sessão");
        hbarDataSet.setBackgroundColor(setCorBarras(getArrecadacaoMediaLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getArrecadacaoMediaLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getArrecadacaoMediaGrafico().setData(data);
        getArrecadacaoMediaGrafico().setOptions(getOptionsBar(getArrecadacaoMediaQuantidade() + " eventos com maior média dearrecadação."));
        ordenar(getArrecadacaoMediaLista());
	}

	public List<Grafico<Evento>> ordenar(List<Grafico<Evento>> lista){
        Collections.sort(lista, new Comparator<Grafico<Evento>>() {
            @Override
        	public int compare(Grafico<Evento> o1, Grafico<Evento> o2) {
                return o2.getValor().compareTo(o1.getValor());
            }
        });
        return lista;
	}
	
	public List<String> setCorBarras(int dados) {
		List<String> cores = new ArrayList<String>();
		for (int i = 0; i < dados; i++) {
			cores.add("rgba(0, 122, 217, 0.4)");			
		}
        return cores;
	}
	
	public List<String> setBordaBarras(int dados) {
		List<String> cores = new ArrayList<String>();
		for (int i = 0; i < dados; i++) {
			cores.add("rgba(0, 71, 126, 1)");			
		}
        return cores;
	}

	public BarChartOptions getOptionsBar(String titulo) {
		BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);
        Title title = new Title();
        title.setText(titulo);
        title.setDisplay(true);
        options.setTitle(title);
        return options;
	}
	
	public PieChartOptions getOptionsPie(String titulo) {
		PieChartOptions options = new PieChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        Title title = new Title();
        title.setText(titulo);
        title.setDisplay(true);
        options.setTitle(title);
        return options;
	}

 	public String detalhar(Evento evento) {
		detalheEventoBean.setEvento(new EventoDao().consultar(evento));
		detalheEventoBean.setTelaAnterior(this);
		return detalheEventoBean.iniciarPagina(getTitulosBread(), getUrlsBread());
	}
}
