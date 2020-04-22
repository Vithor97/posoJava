package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bradesco.posoTeatro.model.Estacionamento;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.posoUtil.Constantes;

public class EstacionamentoDao {
	
	public int limiteDisponivel(Sessao sessao) {
		int limite = 0;	
		try {
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement smt = conn.prepareStatement("select " + Constantes.LIMITE_ESTACIONAMENTO + " - count(cod_estacionamento) as limite from estacionamento where cod_sessao = " + sessao.getCodigo());
				ResultSet rs = smt.executeQuery();
				if (rs.next()) {
					limite = rs.getInt("limite");			
				}
				rs.close();
				smt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return limite;
	}

	public String cadastrar(Estacionamento estacionamento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();	
			PreparedStatement smt = conn.prepareStatement("insert into estacionamento values (?, ?)");
			smt.setInt(1, estacionamento.getPessoa().getCodigo());
			smt.setInt(2, estacionamento.getSessao().getCodigo());
			smt.executeUpdate();	
			smt.close();
			conn.close();
			return "Vaga reservada.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}
}
