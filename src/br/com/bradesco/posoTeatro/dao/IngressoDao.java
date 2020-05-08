package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.posoTeatro.model.Ingresso;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.Poltrona;
import br.com.bradesco.posoTeatro.model.Sessao;
import br.com.bradesco.posoTeatro.posoUtil.enums.Situacao;
import br.com.bradesco.posoTeatro.posoUtil.enums.TipoPoltrona;

public class IngressoDao {
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public String cadastrar(Ingresso ingresso) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("insert into ingresso values (?, ?, ?, ?, default)");
			
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
	
	public List<Ingresso> listar(Pessoa pessoa){
		List<Ingresso> ingressos = new ArrayList<Ingresso>();
        try {
            Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement smt = conn.prepareStatement("select * from Ingresso where cod_pessoa = ?");
            smt.setInt(1, pessoa.getCodigo());       
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
            	Ingresso ingresso = new Ingresso();
            	ingresso.getSessao().setCodigo(rs.getInt("cod_sessao"));
            	ingresso.setSessao(new SessaoDao().consultaSessao(ingresso.getSessao()));
            	ingresso.getPoltrona().setTipoPoltrona(TipoPoltrona.codigo(rs.getInt("tipo_poltrona")));
            	ingressos.add(ingresso);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return ingressos;
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
	
	public boolean reembolsoIngresso(Ingresso ingresso) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("UPDATE ingresso SET situacao_ingresso = 0 WHERE cod_ingresso = ?");

			smt.setLong(1, ingresso.getCodigoIngresso());

			smt.executeUpdate();

			smt.close();
			conn.close();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
			
		}
	  }
	
	public ArrayList<Ingresso> listarIngressosAtivos(){
		ArrayList<Ingresso> ingressosRetorno = new ArrayList<Ingresso>();
		try {
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM ingresso WHERE situacao_ingresso = 1");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Ingresso ingresso = new Ingresso();
				ingresso.setCodigoIngresso(rs.getInt("cod_ingresso"));
				ingresso.getPessoa().setCodigo(rs.getInt("cod_pessoa"));
				ingresso.getPoltrona().setCodigo(rs.getString("cod_poltrona"));
				ingresso.getPoltrona().setTipoPoltrona(TipoPoltrona.codigo(rs.getInt("tipo_poltrona")));
				ingresso.getSessao().setCodigo(rs.getInt("cod_sessao"));
				ingresso.setSituacaoIngresso(Situacao.codigo(rs.getInt("situacao_ingresso")));
				ingresso.setSessao(new SessaoDao().consultaSessao(ingresso.getSessao()));
				ingresso.setPessoa(new PessoaDao().consultar(ingresso.getPessoa()));
				ingressosRetorno.add(ingresso);
			}
			rs.close();
			smt.close();
			return ingressosRetorno;
			
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
	
	public boolean alterarIngresso(Ingresso ingresso) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("UPDATE ingresso SET cod_sessao = ?,"
																			+ "cod_pessoa = ?,"
																			+ "cod_poltrona = ?,"
																			+ "tipo_poltrona = ?"
																			+ " WHERE cod_ingresso = ?");

			smt.setInt(1, ingresso.getSessao().getCodigo());
			smt.setInt(2, ingresso.getPessoa().getCodigo());
			smt.setString(3, ingresso.getPoltrona().getCodigo());
			smt.setInt(4, ingresso.getPoltrona().getTipoPoltrona().getCodigo());
			smt.setInt(5, ingresso.getCodigoIngresso());
			smt.executeUpdate();

			smt.close();
			conn.close();

			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
			
		}
	}
}
