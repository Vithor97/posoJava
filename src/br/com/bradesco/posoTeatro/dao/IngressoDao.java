package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Poltrona;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.posoUtil.enums.TipoPoltrona;

public class IngressoDao {
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public String cadastrar(Ingresso ingresso) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("insert into ingresso values (?, ?, ?, ?)");
			
			smt.setInt(1, ingresso.getSessao().getCodigo());
			smt.setInt(2, ingresso.getPessoa().getCodigo());
			smt.setString(3, ingresso.getPoltrona().getCodigo());
			smt.setInt(4, ingresso.getPoltrona().getTipoPoltrona().getCodigo());
	
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
				ingressoRetorno.getPessoa().setCodigo(rs.getInt("cod_pessoa"));
				ingressoRetorno.getPoltrona().setCodigo(rs.getString("cod_poltrona"));
				ingressoRetorno.getPoltrona().setTipoPoltrona(TipoPoltrona.codigo(rs.getInt("tipo_poltrona")));
				ingressoRetorno.getSessao().setCodigo(rs.getInt("cod_sessao"));
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
	
	
	
	
	
	
	
	
	
	
	public List<Poltrona> verificarPoltronas(Sessao sessao) {
		List<Poltrona> poltronas = new ArrayList<Poltrona>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM ingresso WHERE cod_sessao = ?");
			smt.setInt(1, sessao.getCodigo());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Poltrona poltrona = new Poltrona();
				poltrona.setCodigo(rs.getString("cod_poltrona"));
				poltrona.setTipoPoltrona(TipoPoltrona.codigo(rs.getInt("tipo_poltrona")));
				poltrona.setSelecionada(1);
				poltronas.add(poltrona);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return poltronas;

	}
	
	public int reembolsoIngresso(Ingresso ingresso) {
		int rs = 0;
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("delete from ingresso where cod_ingresso = ?");

			smt.setLong(1, ingresso.getCodigoIngresso());

			rs = smt.executeUpdate();
			System.out.println("Linhas afetadas: " + rs);

			smt.close();
			conn.close();

			

		} catch (Exception e) {
			System.out.println("Dentro do catch: " + e.getMessage());
			
		}
		return rs;
	  }
	}
