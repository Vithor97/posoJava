package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;

public class IngressoDao extends Ingresso {
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public String cadastrar(Ingresso ingresso) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("insert into ingresso values (?, ?, ?)");
			
			smt.setInt(1, ingresso.getCodigoSessao() );
			smt.setInt(2, ingresso.getCodigoPessoa());
			smt.setString(3, ingresso.getCodigoPoltrona());
	
			smt.executeUpdate();	
			
			smt.close();
			conn.close();
			
			return "Ingresso cadastrado.";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}
	

	public Ingresso cosultarIngressos(Ingresso ingresso) {
		Ingresso ingressoRetorno = new Ingresso();
		try {
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM ingresso WHERE cod_ingresso = ?");
			smt.setInt(1,ingresso.getCodigoIngresso());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				ingressoRetorno.setCodigoIngresso(rs.getInt("cod_ingresso"));
				ingressoRetorno.setCodigoPessoa(rs.getInt("cod_pessoa"));
				ingressoRetorno.setCodigoPoltrona(rs.getString("cod_poltrona"));
				ingressoRetorno.setCodigoSessao(rs.getInt("cod_sessao"));
				rs.close();
				smt.close();
				return ingressoRetorno;
			}
			else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
