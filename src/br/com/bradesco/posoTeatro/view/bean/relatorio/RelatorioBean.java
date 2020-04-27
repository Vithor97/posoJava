package br.com.bradesco.posoTeatro.view.bean.relatorio;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartOptions;

import br.com.bradesco.posoTeatro.view.bean.PosoBean;

public class RelatorioBean extends PosoBean{

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
	
	public BarChartOptions getOptionsHorizontalBar(String titulo) {
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

	public BarChartOptions getOptionsBar(String titulo) {
		BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);        
        cScales.addYAxesData(linearAxes);
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

	
	public List<String> getCores(){
		List<String> cores = new ArrayList<String>();
		cores.add("rgba(0, 122, 217, 0.45)");			
		cores.add("rgba(0, 217, 122, 1)");			
		cores.add("rgba(217, 0, 0, 0.45)");			
		cores.add("rgba(0, 217, 122, 0.45)");			
		cores.add("rgba(0, 122, 217, 1)");			
		cores.add("rgba(122, 0, 217, 0.45)");			
		cores.add("rgba(0, 0, 217, 0.45)");			
		cores.add("rgba(217, 0, 122, 0.45)");			
		cores.add("rgba(255, 255, 0, 0.4)");
		cores.add("rgba(217, 122, 0, 0.45)");			
		cores.add("rgba(217, 0, 0, 1)");			
		cores.add("rgba(122, 217, 0, 0.45)");	
		cores.add("rgba(0, 217, 0, 0.45)");			
		return cores;
	}
}
