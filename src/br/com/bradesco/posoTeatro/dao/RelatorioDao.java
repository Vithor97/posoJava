package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.posoUtil.enums.TipoPoltrona;

public class RelatorioDao {

	//CLIENTES
	
	public List<Grafico<Pessoa>> principaisClientes(int quantidade){
		List<Grafico<Pessoa>> resultado = new ArrayList<Grafico<Pessoa>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select count(cod_ingresso) as quantidade, b.nome_pessoa, b.cod_pessoa from Ingresso a inner join Pessoa b on a.cod_pessoa = b.cod_pessoa group by b.nome_pessoa, b.cod_pessoa order by 1  desc offset 0 rows fetch next " + quantidade + " rows only");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<Pessoa> principaisClientes = new Grafico<Pessoa>(new Pessoa());
            	principaisClientes.getItem().setCodigo(rs.getInt("cod_pessoa"));
            	principaisClientes.getItem().setNome(rs.getString("nome_pessoa"));
            	principaisClientes.setValor(rs.getDouble("quantidade"));
            	resultado.add(principaisClientes);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}
	
	public List<Grafico<String>> faixaEtariaClientes(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select count(cod_pessoa) as quantidade, dbo.FaixaEtaria(dtnasc_pessoa) from Pessoa group by dbo.FaixaEtaria(dtnasc_pessoa)");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	if (rs.getString(2).equals("60")) {
                	retorno.setItem("Acima de " + rs.getString(2));
				}else {
	            	retorno.setItem("" + rs.getString(2));
				}
            	retorno.setValor(rs.getDouble("quantidade"));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> faixaEtariaVendas(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("Select dbo.faixaEtaria(b.dtnasc_pessoa), count(a.cod_ingresso) from ingresso a inner join pessoa b on a.cod_pessoa = b.cod_pessoa group by dbo.faixaEtaria(b.dtnasc_pessoa)");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	if (rs.getString(1).equals("60")) {
                	retorno.setItem("Acima de " + rs.getString(1));
				}else {
	            	retorno.setItem("" + rs.getString(1));
				}
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> faixaEtariaArrecadacao(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("Select dbo.faixaEtaria(b.dtnasc_pessoa), sum(c.valor_base_sessao) from ingresso a inner join pessoa b on a.cod_pessoa = b.cod_pessoa inner join Sessao c on a.cod_sessao = c.cod_sessao group by dbo.faixaEtaria(b.dtnasc_pessoa)");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	if (rs.getString(1).equals("60")) {
                	retorno.setItem("Acima de " + rs.getString(1));
				}else {
	            	retorno.setItem("" + rs.getString(1));
				}            	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}
		
	//EVENTOS
	
	public List<Grafico<Evento>> publicoEventos(int quantidade){
		List<Grafico<Evento>> resultado = new ArrayList<Grafico<Evento>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select c.cod_evento, c.desc_evento, count(b.cod_ingresso) as quantidade from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.desc_evento, c.cod_evento order by 2 offset 0 rows fetch next " + quantidade + " rows only");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<Evento> grafico = new Grafico<Evento>(new Evento());
            	grafico.getItem().setCodigo(rs.getInt("cod_evento"));
            	grafico.getItem().setDescricao(rs.getString("desc_evento"));
            	grafico.setValor(rs.getDouble("quantidade"));
            	resultado.add(grafico);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}
	
	public List<Grafico<Evento>> publicoMediaEventos(int quantidade){
		List<Grafico<Evento>> resultado = new ArrayList<Grafico<Evento>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select c.cod_evento, c.desc_evento, cast(count(b.cod_ingresso) as float)  / cast(count(distinct a.cod_sessao) as float) as quantidade from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.desc_evento, c.cod_evento order by 1 offset 0 rows fetch next " + quantidade + " rows only");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<Evento> grafico = new Grafico<Evento>(new Evento());
            	grafico.getItem().setCodigo(rs.getInt("cod_evento"));
            	grafico.getItem().setDescricao(rs.getString("desc_evento"));
            	grafico.setValor(rs.getDouble("quantidade"));
            	resultado.add(grafico);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}
	
	public List<Grafico<Evento>> arrecadacao(int quantidade){
		List<Grafico<Evento>> resultado = new ArrayList<Grafico<Evento>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select c.cod_evento, c.desc_evento, sum(a.valor_base_sessao * case when b.tipo_poltrona = 1 then 2.1 when b.tipo_poltrona = 2 then 2.05 when b.tipo_poltrona = 3 then 0.9 when b.tipo_poltrona = 4 then 1 when b.tipo_poltrona = 5 then 0.9 end) as valor from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.desc_evento, c.cod_evento order by 1 offset 0 rows fetch next " + quantidade + " rows only");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<Evento> grafico = new Grafico<Evento>(new Evento());
            	grafico.getItem().setCodigo(rs.getInt("cod_evento"));
            	grafico.getItem().setDescricao(rs.getString("desc_evento"));
            	grafico.setValor(rs.getDouble("valor"));
            	resultado.add(grafico);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}
	
	public List<Grafico<Evento>> arrecadacaoMedia(int quantidade){
		List<Grafico<Evento>> resultado = new ArrayList<Grafico<Evento>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select c.cod_evento, c.desc_evento, sum(a.valor_base_sessao * case when b.tipo_poltrona = 1 then 2.1 when b.tipo_poltrona = 2 then 2.05 when b.tipo_poltrona = 3 then 0.9 when b.tipo_poltrona = 4 then 1 when b.tipo_poltrona = 5 then 0.9 end) / count(distinct a.cod_sessao) as valor from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.desc_evento, c.cod_evento order by 1 offset 0 rows fetch next " + quantidade + " rows only");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<Evento> grafico = new Grafico<Evento>(new Evento());
            	grafico.getItem().setCodigo(rs.getInt("cod_evento"));
            	grafico.getItem().setDescricao(rs.getString("desc_evento"));
            	grafico.setValor(rs.getDouble("valor"));
            	resultado.add(grafico);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	//TIPOS
	
	public List<Grafico<String>> tipos(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("Select b.nome_tipo, count(a.cod_evento) as quantidade from Evento a full outer join Tipo_Evento b on a.cod_tipo = b.cod_tipo group by b.nome_tipo");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	retorno.setItem(rs.getString(1));       	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> tiposVendas(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("Select a.nome_tipo, coalesce(sum(b.quantidade), 0) as quantidade from Tipo_evento a left join ( select c.cod_tipo as tipo, count(b.cod_ingresso) as quantidade from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.cod_tipo, c.cod_evento ) b on a.cod_tipo = b.tipo group by a.nome_tipo, a.cod_tipo");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	retorno.setItem(rs.getString(1));       	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> tiposArrecadacao(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select a.nome_tipo, coalesce(sum(b.quantidade), 0) as quantidade from Tipo_Evento a left join ( select c.cod_tipo as tipo, sum(dbo.ValorIngresso( b.tipo_poltrona, a.valor_base_sessao)) as quantidade from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.cod_tipo, c.cod_evento ) b on a.cod_tipo = b.tipo group by a.nome_tipo, a.cod_tipo");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	retorno.setItem(rs.getString(1));       	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> generos(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("Select b.nome_genero, count(a.cod_evento) as quantidade from Evento a full outer join Genero b on a.cod_genero = b.cod_genero group by b.nome_genero");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	retorno.setItem(rs.getString(1));       	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> generosVendas(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select a.nome_genero, coalesce(sum(b.quantidade), 0) as quantidade from Genero a left join ( select c.cod_genero as genero, count(b.cod_ingresso) as quantidade from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.cod_genero, c.cod_evento ) b on a.cod_genero = b.genero group by a.nome_genero, a.cod_genero");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	retorno.setItem(rs.getString(1));       	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	public List<Grafico<String>> generosArrecadacao(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select a.nome_genero, coalesce(sum(b.quantidade), 0) as quantidade from Genero a left join ( select c.cod_genero as genero, sum(dbo.ValorIngresso( b.tipo_poltrona, a.valor_base_sessao)) as quantidade from Sessao a inner join ingresso b on a.cod_sessao = b.cod_sessao inner join Evento c on a.cod_evento = c.cod_evento group by c.cod_genero, c.cod_evento ) b on a.cod_genero = b.genero group by a.nome_genero, a.cod_genero");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Grafico<String> retorno = new Grafico<String>("");
            	retorno.setItem(rs.getString(1));       	
            	retorno.setValor(rs.getDouble(2));
            	resultado.add(retorno);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}

	
	public List<Grafico<Pessoa>> principaisClientes(){
		List<Grafico<Pessoa>> resultado = new ArrayList<Grafico<Pessoa>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("select count(cod_ingresso) as quantidade, b.nome_pessoa, b.cod_pessoa from Ingresso a inner join Pessoa b on a.cod_pessoa = b.cod_pessoa group by b.nome_pessoa, b.cod_pessoa order by 1  desc offset 0 rows fetch next 20 rows only");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<Pessoa> item = new Grafico<Pessoa>(new Pessoa());
	        	item.getItem().setCodigo(rs.getInt("cod_pessoa"));
	        	item.getItem().setNome(rs.getString("nome_pessoa"));
	        	item.setValor(rs.getDouble("quantidade"));
	        	resultado.add(item);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	
	public List<Grafico<Ingresso>> comprasCliente(Pessoa pessoa){
		List<Grafico<Ingresso>> resultado = new ArrayList<Grafico<Ingresso>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("select * from Ingresso where cod_pessoa = ?");
	        smt.setInt(1, pessoa.getCodigo());        
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<Ingresso> ingresso = new Grafico<Ingresso>(new Ingresso());
	        	ingresso.getItem().getSessao().setCodigo(rs.getInt("cod_sessao"));
	        	ingresso.getItem().setSessao(new SessaoDao().consultaSessao(ingresso.getItem().getSessao()));
	        	ingresso.getItem().getPoltrona().setTipoPoltrona(TipoPoltrona.codigo(rs.getInt("tipo_poltrona")));
	        	resultado.add(ingresso);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public int quantidadeClientes(){
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("select count(cod_pessoa) as quantidade from Pessoa");
	        ResultSet rs = smt.executeQuery();
	        if (rs.next()) {
	        	return rs.getInt("quantidade");
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return 0;
	}

	public List<Grafico<String>> faixaEtariaFuncionarios() {
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("select count(cod_func) as quantidade, b.nome from Funcionario a right join FaixasEtarias b on dbo.FaixaEtaria(a.dtnasc_func) = b.nome group by b.nome");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	if (rs.getString(2).equals("60")) {
	            	retorno.setItem("Acima de " + rs.getString(2));
				}else {
	            	retorno.setItem("" + rs.getString(2));
				}
	        	retorno.setValor(rs.getDouble("quantidade"));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<Funcionario>> principaisFuncionarios(int quantidade) {
		List<Grafico<Funcionario>> resultado = new ArrayList<Grafico<Funcionario>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("select count(cod_evento) as quantidade, b.nome_func, b.cod_func from Evento a inner join Funcionario b on a.cod_func = b.cod_func where b.cod_cargo in (1, 2) group by b.nome_func, b.cod_func order by 1  desc offset 0 rows fetch next " + quantidade + " rows only");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<Funcionario> principaisClientes = new Grafico<Funcionario>(new Funcionario());
	        	principaisClientes.getItem().setCodigo(rs.getInt("cod_func"));
	        	principaisClientes.getItem().setNome(rs.getString("nome_func"));
	        	principaisClientes.setValor(rs.getDouble("quantidade"));
	        	resultado.add(principaisClientes);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoHoraValorMedio(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select coalesce(coalesce(sum(a.valor_base_sessao), 0) / NULLIF(count(a.cod_sessao), 0), 0), b.nome, b.numero from Sessao a right join PeriodosDoDia b on dbo.PeriodoDoDia(a.hora_sessao) = b.nome left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoHoraArrecadacao(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select coalesce(sum(dbo.valorIngresso(c.tipo_poltrona, a.valor_base_sessao)), 0), b.nome, b.numero from Sessao a right join PeriodosDoDia b on dbo.PeriodoDoDia(a.hora_sessao) = b.nome left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoHoraVendas(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select count(c.cod_ingresso), b.nome, b.numero from Sessao a right join PeriodosDoDia b on dbo.PeriodoDoDia(a.hora_sessao) = b.nome left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoHoraQuantidade(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select count(a.cod_sessao), b.nome, b.numero from Sessao a right join PeriodosDoDia b on dbo.PeriodoDoDia(a.hora_sessao) = b.nome group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoDiaValorMedio(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select coalesce(coalesce(sum(a.valor_base_sessao), 0) / NULLIF(count(a.cod_sessao), 0), 0), b.nome, b.numero from Sessao a right join DiasSemana b on datepart(dw, (a.dia_sessao)) = b.numero left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoDiaArrecadacao(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select coalesce(sum(dbo.valorIngresso(c.tipo_poltrona, a.valor_base_sessao)), 0), b.nome, b.numero from Sessao a right join DiasSemana b on datepart(dw, (a.dia_sessao)) = b.numero left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoDiaVendas(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select count(c.cod_ingresso), b.nome, b.numero from Sessao a right join DiasSemana b on datepart(dw, (a.dia_sessao)) = b.numero left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoDiaQuantidade(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select count(a.cod_sessao), b.nome, b.numero from Sessao a right join DiasSemana b on datepart(dw, (a.dia_sessao)) = b.numero group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoMesValorMedio(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select coalesce(coalesce(sum(a.valor_base_sessao), 0) / NULLIF(count(a.cod_sessao), 0), 0), b.nome, b.numero from Sessao a right join Meses b on month(a.dia_sessao) = b.numero left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoMesArrecadacao(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select coalesce(sum(dbo.valorIngresso(c.tipo_poltrona, a.valor_base_sessao)), 0), b.nome, b.numero from Sessao a right join Meses b on month(a.dia_sessao) = b.numero left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoMesVendas(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select count(c.cod_ingresso), b.nome, b.numero from Sessao a right join Meses b on month(a.dia_sessao) = b.numero left join Ingresso c on a.cod_sessao = c.cod_sessao group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

	public List<Grafico<String>> sessaoMesQuantidade(){
		List<Grafico<String>> resultado = new ArrayList<Grafico<String>>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("Select count(a.cod_sessao), b.nome, b.numero from Sessao a right join Meses b on month(a.dia_sessao) = b.numero group by b.nome, b.numero order by 3");
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	        	Grafico<String> retorno = new Grafico<String>("");
	        	retorno.setItem(rs.getString(2));       	
	        	retorno.setValor(rs.getDouble(1));
	        	resultado.add(retorno);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}

}
