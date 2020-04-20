package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Pessoa;
//TESTEEEE

public class EventoDao extends Evento {

	public String cadastrar(Evento evento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("insert into evento values (?, ?, ?, ?, ?)");

			smt.setString(1, evento.getCnpjEmpresa());
			smt.setInt(2, evento.getCodFuncResp());
			smt.setString(3, evento.getDescEvento());
			smt.setString(4, evento.getTipoEvento());
			smt.setString(5, evento.getGeneroEvento());

			smt.executeUpdate();

			smt.close();
			conn.close();

			return "Evento cadastrado.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}

	public ArrayList<Evento> listar() {

		ArrayList<Evento> eventos = new ArrayList<Evento>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select cod_evento, desc_evento from evento");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setCodigo(rs.getInt("cod_evento"));
				evento.setDescEvento(rs.getString("desc_evento"));
				eventos.add(evento);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventos;
	}
	
	public ArrayList<Pessoa> listarPessoas(Evento evento) {

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select b.* from pessoa_evento a inner join pessoa b on a.cod_pessoa = b.cod_pessoa where a.cod_evento = " + evento.getCodigo());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCodigo(rs.getInt("cod_pessoa"));
				pessoa.setNome(rs.getString("nome_pessoa"));
				String cpf = rs.getString("cpf_pessoa");
				pessoa.setCpf(cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9,11));
				pessoas.add(pessoa);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pessoas;
	}
	
	public Evento consultar(Evento evento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from evento where cod_evento = " + evento.getCodigo());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				evento.setDescEvento(rs.getString("desc_evento"));
				evento.setGeneroEvento(rs.getString("genr_evento"));
				evento.setTipoEvento(rs.getString("tipo_evento"));
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return evento;
	}
	
	
	public int alterar(Evento eventoAltera) {
		int rs = 0;
		
		try {
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("Update evento set desc_evento = ?,  tipo_evento = ?, genr_evento = ?, cnpj_empresa = ?, cod_func_resp = ?");
			
			smt.setString(1, eventoAltera.getDescEvento());
			smt.setString(2, eventoAltera.getTipoEvento());
			smt.setString(3, eventoAltera.getGeneroEvento());
			smt.setString(4, eventoAltera.getCnpjEmpresa());
			smt.setInt(5, eventoAltera.getCodFuncResp());

			rs = smt.executeUpdate();
			
			
			System.out.println("rows affected " + rs);
			
		} catch (Exception e) {
			System.out.println("dentro do catch: " + e.getMessage());
		}
		
		return rs;				
	}
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public boolean excluir(Evento evento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("delete from evento where cod_evento = ?");

			smt.setInt(1, evento.getCodigo());

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