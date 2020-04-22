package br.com.bradesco.posoTeatro.posoUtil.enums;

public enum Cargos {
    
	ORGANIZADOR("organizador"), 
	BILHETERIA("tarde");
	
	private String descricao;
	
	Cargos (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
