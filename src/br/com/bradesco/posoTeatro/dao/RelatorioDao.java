package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.posoTeatro.model.relatorio.clientes.PrincipaisClientes;

public class RelatorioDao {

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
	

}
