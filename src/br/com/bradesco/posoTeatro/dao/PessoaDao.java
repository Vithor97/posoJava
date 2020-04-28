package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.Evento;

public class PessoaDao extends Pessoa {

	public String cadastrar(Pessoa pessoa) {
		try {
			Connection conn = new ConnectionFactory().getConnection();			
			PreparedStatement smt = conn.prepareStatement("insert into pessoa values (?, ?, ?, ?, ?, default)");		
			smt.setString(1, pessoa.getNome());
			smt.setString(2, pessoa.getCpfFormatado());
			smt.setString(3, pessoa.getDataNascimentoString());
			smt.setString(4, pessoa.getTelefoneFormatado());
			smt.setString(5, pessoa.getEmail());			
			smt.executeUpdate();	
			return "Pessoa cadastrada.";	
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}

	public Pessoa consultar(Pessoa pessoa) {
		Pessoa produto = new Pessoa();
		produto.setCpf(pessoa.getCpfFormatado());
		produto.setCodigo(pessoa.getCodigo());
		try {
			Connection conn = new ConnectionFactory().getConnection();
			
			 FacesContext context = FacesContext.getCurrentInstance();
	         Funcionario funcionario = (Funcionario) context.getExternalContext().getSessionMap().get("funcionarioLogado");
	         
			PreparedStatement smt = conn.prepareStatement("select * from pessoa where cpf_pessoa=? OR cod_pessoa = ? and situacao_pessoa = 1");
			
			smt.setString(1, produto.getCpf());
			smt.setInt(2, produto.getCodigo());
			
			
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				produto.setCodigo(rs.getInt("cod_pessoa"));
				
				produto.setNome(rs.getString("nome_pessoa"));
				
				produto.setEmail(rs.getString("email_pessoa"));
				
				produto.setTelefone(rs.getString("tel_pessoa"));
				
				produto.setCpf(rs.getString("cpf_pessoa"));
				
				produto.setDataNascimento(rs.getDate("dtnasc_pessoa"));
				
				produto.setSituacaoPessoa(rs.getInt("situacao_pessoa"));
			}
			
			rs.close();
			smt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produto;
	}
	
	
	public int alterar(Pessoa pessoaAltera) {
		
		int rs = 0;
		try {
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement stm = conn.prepareStatement("update pessoa set nome_pessoa = ?, cpf_pessoa = ?, dtnasc_pessoa = ?, tel_pessoa = ?, email_pessoa = ? where cod_pessoa = ?");
			
			stm.setString(1, pessoaAltera.getNome());
			stm.setString(2, pessoaAltera.getCpfFormatado());
			stm.setString(3, pessoaAltera.getDataNascimentoString());
			stm.setString(4, pessoaAltera.getTelefoneFormatado());
			stm.setString(5, pessoaAltera.getEmail());
			stm.setInt(6, pessoaAltera.getCodigo());
			
			rs = stm.executeUpdate();
	
			System.out.println("rows affected " + rs);
			
		} catch (SQLException e) {
			System.out.println("dentro do catch: " + e.getMessage());
		}
		
		return rs;				
	}
	
	public int deletaPessoa(Pessoa pessoa) {
		int rowAffected = 0;
//		stm = conn.prepareStatement("delete from Pessoa_Evento where cod_pessoa=?");
//		stm.setInt(1, pessoa.getCodigo());
//		rowAffected = stm.executeUpdate();

		
		try {
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement stm = conn.prepareStatement("update pessoa set situacao_pessoa = ? where cod_pessoa= ?");
			//Desativa a pessoa dando set na situacao_pessoa para 0;
			stm.setInt(1, 0);
			stm.setInt(2, pessoa.getCodigo());
			
			rowAffected = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
		return rowAffected;
	}

	public ArrayList<Pessoa> listarPessoas(String nome) {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		String query = "";
		try {
			
			if(nome == "") {
				query = "Select * from pessoa where situacao_pessoa = 1";
			}
			else {
				query = "SELECT * FROM pessoa WHERE upper(nome_pessoa) like upper('" + nome + "%') and situacao_pessoa = 1";
			}
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement(query);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCodigo(rs.getInt("cod_pessoa"));
				pessoa.setNome(rs.getString("nome_pessoa"));
				pessoa.setCpf(rs.getString("cpf_pessoa"));
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
	
	public ArrayList<Evento> listarEventosPessoa(Pessoa pessoa) {

		ArrayList<Evento> eventos = new ArrayList<Evento>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select b.* from pessoa_evento a inner join evento b on a.cod_evento = b.cod_evento where a.cod_pessoa = " + pessoa.getCodigo());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setCodigo(rs.getInt("cod_evento"));
				evento.setDescricao(rs.getString("desc_evento"));
				evento.setCodigo(rs.getInt("cod_tipo"));
				evento.setCodigo(rs.getInt("cod_genero"));
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
	
}
