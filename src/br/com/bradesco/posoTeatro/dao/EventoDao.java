package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Pessoa;

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
}