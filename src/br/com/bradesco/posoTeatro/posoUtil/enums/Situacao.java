package br.com.bradesco.posoTeatro.posoUtil.enums;

public enum Situacao {

	DESATIVADO(0,"Desativado"), 
	ATIVADO(1,"Ativado");

    private final int codigo;       
    private final String descricao;      

	private Situacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
    }
    public boolean equals(String descricao) {
        return this.descricao.equals(descricao);
    }

    public String getDescricao() {
       return this.descricao;
    }
    
    public int getCodigo() {
        return this.codigo;
     }
    
    public String getDescricao(int codigo) {
    	switch(codigo) {
        case 0:
            return DESATIVADO.getDescricao();
        case 1:
            return ATIVADO.getDescricao();
        }
        return null;
     }
    
	public static Situacao codigo(int valor) {
        switch(valor) {
        case 0:
            return DESATIVADO;
        case 1:
            return ATIVADO;
        }
        return null;
    }
}
