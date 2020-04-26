package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.relatorio.Grafico;
import br.com.bradesco.posoTeatro.model.relatorio.clientes.PrincipaisClientes;

public class RelatorioDao {

	//CLIENTES
	
	public List<PrincipaisClientes> principaisClientes(int quantidade){
		List<PrincipaisClientes> resultado = new ArrayList<PrincipaisClientes>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select count(cod_ingresso) as quantidade, b.nome_pessoa, b.cod_pessoa from Ingresso a inner join Pessoa b on a.cod_pessoa = b.cod_pessoa group by b.nome_pessoa, b.cod_pessoa order by 1  desc offset 0 rows fetch next " + quantidade + " rows only");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	PrincipaisClientes principaisClientes = new PrincipaisClientes();
            	principaisClientes.getPessoa().setCodigo(rs.getInt("cod_pessoa"));
            	principaisClientes.getPessoa().setNome(rs.getString("nome_pessoa"));
            	principaisClientes.setQuantidadeCompras(rs.getInt("quantidade"));
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
	
	public List<Integer> faixaEtariaClientes(){
		List<Integer> resultado = new ArrayList<Integer>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select count(cod_pessoa) as quantidade, '18 - 25' from Pessoa where dbo.FaixaEtaria(dtnasc_pessoa) = '18 - 25' union select count(cod_pessoa), '25 - 35' from Pessoa where dbo.FaixaEtaria(dtnasc_pessoa) = '25 - 35' union select count(cod_pessoa), '35 - 50' from Pessoa where dbo.FaixaEtaria(dtnasc_pessoa) = '35 - 50' union select count(cod_pessoa), '50 - 60' from Pessoa where dbo.FaixaEtaria(dtnasc_pessoa) = '50 - 60' union select count(cod_pessoa), '60+' from Pessoa where dbo.FaixaEtaria(dtnasc_pessoa) = '60+' order by 2");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	resultado.add(rs.getInt("quantidade"));
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
}
