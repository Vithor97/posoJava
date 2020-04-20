package br.com.bradesco.posoTeatro.model;

import br.com.bradesco.posoTeatro.posoUtil.Validacoes;

public class Evento {
	
	//eduardo funkeiro
	private int codigo;
	private String cnpjEmpresa;
	private int codFuncResp;
	private String descEvento;
	private String tipoEvento;
	private String generoEvento;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public int getCodFuncResp() {
		return codFuncResp;
	}

	public void setCodFuncResp(int codFuncResp) {
		this.codFuncResp = codFuncResp;
	}

	public String getDescEvento() {
		return descEvento;
	}

	public void setDescEvento(String descEvento) {
		this.descEvento = descEvento;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getGeneroEvento() {
		return generoEvento;
	}

	public void setGeneroEvento(String generoEvento) {
		this.generoEvento = generoEvento;
	}

}