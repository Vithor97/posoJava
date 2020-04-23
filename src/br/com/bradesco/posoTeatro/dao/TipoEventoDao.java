package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bradesco.posoTeatro.model.Genero;
import br.com.bradesco.posoTeatro.model.TipoEvento;

public class TipoEventoDao {
	public TipoEvento consultar(TipoEvento tipoEvento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from tipo_evento where cod_tipo = " + tipoEvento.getCodigo());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				tipoEvento.setNome(rs.getString("nome_tipo"));			
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tipoEvento;
	}
	
	public String cadastrar(TipoEvento tipoEvento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("insert into tipo_evento values (?, default)");
			smt.setString(1,  tipoEvento.getNome());
			smt.executeUpdate();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}

		return "Tipo de Evento cadastrado.";
	}
}
