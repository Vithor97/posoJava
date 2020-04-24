package br.com.bradesco.posoTeatro.posoUtil.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoPoltrona {

    BalcaoNobre ("Balcao Nobre", 1),
    CamarotePrime ("Camarote Prime", 2),
    Frisas1 ("Frisas 1", 3),
    Plateia ("Plateia", 4),
    Frisas2 ("Frisas 2", 5);

    private final int codigo;       
    private final String descricao;       

    private TipoPoltrona(String descricao, int codigo) {
    	this.descricao = descricao;
    	this.codigo = codigo;
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
        case 1:
            return BalcaoNobre.getDescricao();
        case 2:
            return CamarotePrime.getDescricao();
         case 3:
            return Frisas1.getDescricao();
        case 4:
            return Plateia.getDescricao();
         case 5:
            return Frisas2.getDescricao();
        }
        return null;
     }
       
    public int getCodigo(String descricao) {
    	switch(codigo) {
        case 1:
            return BalcaoNobre.getCodigo();
        case 2:
            return CamarotePrime.getCodigo();
         case 3:
            return Frisas1.getCodigo();
        case 4:
            return Plateia.getCodigo();
         case 5:
            return Frisas2.getCodigo();
        }
        return 0;
    }
    
	public static TipoPoltrona codigo(int valor) {
        switch(valor) {
        case 1:
            return BalcaoNobre;
        case 2:
            return CamarotePrime;
         case 3:
            return Frisas1;
        case 4:
            return Plateia;
         case 5:
            return Frisas2;
        }
        return null;
    }

	public static List<String> getItems(){
		List<String> itens = new ArrayList<String>();
		TipoPoltrona[] tipos = values();
		for (TipoPoltrona tipoPoltrona : tipos) {
			itens.add(tipoPoltrona.getDescricao());
		}
		return itens;
	}
}
