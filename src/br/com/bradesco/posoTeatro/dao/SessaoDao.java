package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Sessao;

public class SessaoDao {

	public String cadastrar(Sessao sessao) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("insert into sessao values (?, ?, ?, ?)");
			
			smt.setLong(1, sessao.getEvento().getCodigo());
			smt.setString(2, sessao.getDataFormatada());
			smt.setString(3, sessao.getHoraFormatada());
			smt.setLong(4, 1);
			
			smt.executeUpdate();	
			
			smt.close();
			conn.close();
			
			return "Sessão Cadastrada!";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}
	
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public Sessao consultaSessao(Sessao sessao) {
		Sessao sessaoRetorno = new Sessao();
		try {
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM sessao WHERE cod_sessao = ? AND situacao_sessao <> 0");
			smt.setInt(1,sessao.getCodigo());
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {
				sessaoRetorno.setCodigo(rs.getInt("cod_sessao"));
				sessaoRetorno.getEvento().setCodigo(rs.getInt("cod_evento"));
				sessaoRetorno.getHorario().setHora(Integer.parseInt((rs.getString("hora_sessao").substring(0, 2))));
				sessaoRetorno.getHorario().setMinutos(Integer.parseInt((rs.getString("hora_sessao").substring(3, 5))));
				sessaoRetorno.setDiaString(rs.getString("dia_sessao"));
				sessaoRetorno.setDataSessao(rs.getString("dia_sessao"));
				sessaoRetorno.setValorBase(rs.getDouble("valor_base_sessao"));
				rs.close();
				smt.close();
				return sessaoRetorno;
			}
		
		else {
			return null;
		}
	
	}catch(Exception e) {
	   e.printStackTrace();
	   return null;
	}
		
		finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Sessao> listarSessoes() {

		ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement(
					"select *, case when dia_sessao >= getdate() then 'Sessão futura' else 'Sessão já realizada' end as situacao from sessao");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Sessao sessao = new Sessao();
				sessao.setCodigo(rs.getInt("cod_sessao"));
				sessao.setDia(rs.getDate("dia_sessao"));
				sessao.getHorario().setHora(Integer.parseInt((rs.getString("hora_sessao").split(":")[0])));
				sessao.getHorario().setMinutos(Integer.parseInt((rs.getString("hora_sessao").split(":")[1])));
				sessao.setValorBase(rs.getDouble("valor_base_sessao"));
				sessao.setSituacao(rs.getString("situacao"));
				sessoes.add(sessao);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sessoes;
	}
	
	public ArrayList<Sessao> listarSessoesAtivas() {
		
		ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement(
					"select * from sessao where dia_sessao >= getdate() and situacao_sessao <> 0");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Sessao sessao = new Sessao();
				sessao.setCodigo(rs.getInt("cod_sessao"));
				sessao.setDia(rs.getDate("dia_sessao"));
				sessao.getHorario().setHora(Integer.parseInt((rs.getString("hora_sessao").split(":")[0])));
				sessao.getHorario().setMinutos(Integer.parseInt((rs.getString("hora_sessao").split(":")[1])));
				sessao.setValorBase(rs.getDouble("valor_base_sessao"));
				sessao.setSituacao("Sessão futura");
				sessoes.add(sessao);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessoes;
	}

	public ArrayList<Sessao> listarSessoes(Evento evento) {

		ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement(
					"select *, case when dia_sessao >= getdate() then 'Sessão futura' else 'Sessão já realizada' end as situacao from sessao where cod_evento = "
							+ evento.getCodigo());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Sessao sessao = new Sessao();
				sessao.setCodigo(rs.getInt("cod_sessao"));
				sessao.setDia(rs.getDate("dia_sessao"));
				sessao.getHorario().setHora(Integer.parseInt((rs.getString("hora_sessao").split(":")[0])));
				sessao.getHorario().setMinutos(Integer.parseInt((rs.getString("hora_sessao").split(":")[1])));
				sessao.setSituacao(rs.getString("situacao"));
				sessoes.add(sessao);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sessoes;
	}

	public ArrayList<Sessao> listarSessoes(String dia) {

		ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement(
					"select *, case when dia_sessao >= getdate() then 'Sessão futura' else 'Sessão já realizada' end as situacao from sessao where dia_sessao = ? and situacao_sessao = 1");
			smt.setString(1, dia);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Sessao sessao = new Sessao();
				sessao.setCodigo(rs.getInt("cod_sessao"));
				sessao.getEvento().setCodigo(rs.getInt("cod_evento"));
				sessao.setEvento(new EventoDao().consultar(sessao.getEvento()));
				sessao.setDia(rs.getDate("dia_sessao"));
				sessao.getHorario().setHora(Integer.parseInt((rs.getString("hora_sessao").split(":")[0])));
				sessao.getHorario().setMinutos(Integer.parseInt((rs.getString("hora_sessao").split(":")[0])));
				sessao.setValorBase(rs.getDouble("valor_base_sessao"));
				sessao.setSituacao(rs.getString("situacao"));
				sessoes.add(sessao);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sessoes;
	}

	public String alterarSessao(Sessao sessao) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement(
					"update sessao set cod_evento = ?, dia_sessao = ?, hora_sessao = ? where cod_sessao = ?");

			smt.setLong(1, sessao.getEvento().getCodigo());
			smt.setString(2, sessao.getDataFormatada());
			smt.setString(3, sessao.getHoraFormatada());
			smt.setLong(4, sessao.getCodigo());

			smt.executeUpdate();

			smt.close();
			conn.close();

			return "Sessão Alterada!";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na atualização.";
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean excluirSessao(Sessao sessao) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("update sessao set situacao_sessao = 0 where cod_sessao = ?");

			smt.setLong(1, sessao.getCodigo());

			smt.executeUpdate();

			smt.close();
			conn.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
