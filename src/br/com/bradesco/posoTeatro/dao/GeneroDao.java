package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bradesco.posoTeatro.model.Genero;

public class GeneroDao {
	public Genero consultar(Genero genero) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from genero where cod_genero = " + genero.getCodigo());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				genero.setNome(rs.getString("nome_genero"));			
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return genero;
	}
}
