package br.com.bradesco.posoTeatro.model;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sessao {
 
	private int codigo;
	private Evento evento = new Evento();
	private Horario horario = new Horario();
	private String dataSessao;
	private Date dia;	
	private double valorBase;
	private String situacao;
	
	public Sessao() {
		setHorario(new Horario());
		setEvento(new Evento());
		getEvento().setCodigo(1);		
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public double getValorBase() {
		return valorBase;
	}

	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getDiaString() {
		if (getDia() != null) {
			Format f = new SimpleDateFormat("dd/MM/yyyy");
			return f.format(getDia());
		}
		return "";
	}

	public void setDiaString(String data) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			setDia(((DateFormat) f).parse(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		
	public boolean validaCodEvento() {
		if(this.getEvento().getCodigo() <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean validaDiaSessao() {
		return this.getDia().after(new Date());
	}
	
	public String getDataFormatada() {
		if(this.getDia() != null) {
			Format f = new SimpleDateFormat("dd/MM/yyyy");
			return f.format(this.getDia());
		}
		return "";
	}
	
	public String getHoraFormatada() {
		if(this.getDia() != null) {
			return this.getHorario().getHorarioCompleto();
		}
		return "";
	}

	public String getDataSessao() {
		return dataSessao;
	}

	public void setDataSessao(String dataSessao) {
		this.dataSessao = dataSessao;
	}
	
	public String getValorBaseString() {
		return String.format("%.2f", getValorBase());
	}
	
	public String getValorPoltronaString(double porcentagem) {
		return String.format("%.2f", getValorBase() * porcentagem);
	}
	
	public double getValorPoltrona(double porcentagem) {
		return getValorBase() * porcentagem;
	}
	
	public boolean filtraHorario(Object valor, Object filtroDigitado, Locale locale) {
		String textoFiltro = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();
		if (textoFiltro == null || textoFiltro.equals("")) {
            return true;
        }
 
        if (valor == null) {
            return false;
        }

        try {
        	if(Integer.getInteger(valor.toString().split(":")[0])
        			.compareTo(Integer.getInteger(textoFiltro.split(":")[0])) >= 0) {
        		return true;
        	} 
        	if(Integer.getInteger(valor.toString().split(":")[1])
        			.compareTo(Integer.getInteger(textoFiltro.split(":")[1])) >= 0) {
        		return true;
        	}
        	return false;
        
        } catch (Exception e) {
			return true;
		}
        
	}

}
