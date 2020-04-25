package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import br.com.bradesco.posoTeatro.dao.IngressoDao;
import br.com.bradesco.posoTeatro.dao.PessoaDao;
import br.com.bradesco.posoTeatro.dao.RelatorioDao;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.relatorio.clientes.PrincipaisClientes;
import br.com.bradesco.posoTeatro.view.bean.BeanInterface;
import br.com.bradesco.posoTeatro.view.bean.PosoBean;

@ManagedBean(name = "relatorioClienteBean")
@SessionScoped

public class RelatorioClienteBean extends PosoBean implements BeanInterface {
	
	private String mensagem;
	
	//Clientes
	private HorizontalBarChartModel principaisClientesGrafico;
	private List<PrincipaisClientes> principaisClientesLista;
	private int principaisClientesQuantidade;
	private Pessoa clienteDetalhe;
	private List<Ingresso> comprasCliente;
	
	private BarChartModel faixaEtariaClientesGraficoColunas;
	private PieChartModel faixaEtariaClientesGraficoPizza;

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

	public List<PrincipaisClientes> getPrincipaisClientesLista() {
		return principaisClientesLista;
	}

	public void setPrincipaisClientesLista(List<PrincipaisClientes> principaisClientesLista) {
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

	public String iniciarPagina(List<String> titulosBread, List<String> urlsBread) {
		super.iniciarPagina("Relatórios", "relatorio", titulosBread, urlsBread);
		iniciarComponentes();
		return "relatorioCliente";
	}

	@Override
	public String iniciarPagina() {
		super.iniciarPagina("Relatórios", "relatorio");
		iniciarComponentes();
		return "relatorioCliente";
	}
	
	public void iniciarComponentes() {
		setPrincipaisClientesQuantidade(5);
		carregarPrincipaisClientes();
		carregarFaixaEtariaClientes();	
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
        
        for (PrincipaisClientes dado : getPrincipaisClientesLista()) {
        	quantidades.add(dado.getQuantidadeCompras());
        	nomes.add(dado.getPessoa().getNome());
		}

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("");
        hbarDataSet.setBackgroundColor(setCorBarras(getPrincipaisClientesLista().size()));
        hbarDataSet.setBorderColor(setBordaBarras(getPrincipaisClientesLista().size()));
        hbarDataSet.setBorderWidth(1);      
        data.addChartDataSet(hbarDataSet);        
        getPrincipaisClientesGrafico().setData(data);
        getPrincipaisClientesGrafico().setOptions(getOptionsBar(getPrincipaisClientesQuantidade() + " clientes com mais compras de ingressos."));
	}

	public void carregarFaixaEtariaClientes() {
		List<Integer> resultado = new RelatorioDao().faixaEtariaClientes();
		setFaixaEtariaClientesGraficoColunas(new BarChartModel());
		
		ChartData data = new ChartData();
        BarChartDataSet hbarDataSet = new BarChartDataSet();
  
        List<Number> quantidades = new ArrayList<Number>();      
        for (int dado : resultado) {
        	quantidades.add(dado);
		}
        
        List<String> nomes = new ArrayList<String>();
        nomes.add("18 - 25");
        nomes.add("25 - 35");
        nomes.add("35 - 50");
        nomes.add("50 - 60");
        nomes.add("60+");

        data.setLabels(nomes);       
        hbarDataSet.setData(quantidades);
        hbarDataSet.setLabel("");
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
         
        List<String> cores = new ArrayList<String>();
		cores.add("rgba(0, 122, 217, 0.45)");			
		cores.add("rgba(217, 0, 0, 1)");			
		cores.add("rgba(0, 122, 217, 1)");			
		cores.add("rgba(0, 217, 122, 1)");			
		cores.add("rgba(255, 255, 0, 0.4)");			
        dataSet.setBackgroundColor(cores);
         
        data.addChartDataSet(dataSet);
        data.setLabels(nomes);
         
        getFaixaEtariaClientesGraficoPizza().setData(data);
        getFaixaEtariaClientesGraficoPizza().setOptions(getOptionsPie("Quantidade de clientes por faixa etária (Pizza)"));
      
        
        
	}

	public void detalharPessoas(Pessoa pessoa) {
		setClienteDetalhe(new PessoaDao().consultar(pessoa));
		setComprasCliente(new IngressoDao().listar(pessoa));
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
}
