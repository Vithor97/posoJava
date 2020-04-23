package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.bradesco.posoTeatro.model.Pessoa;

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
			PreparedStatement smt = conn.prepareStatement("select * from pessoa where cpf_pessoa=? OR cod_pessoa = ? and situacao_pessoa = 1");
			
			smt.setString(1, produto.getCpf());
			smt.setInt(2, produto.getCodigo());
			
			
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				produto.setCodigo(rs.getInt("cod_pessoa"));
				System.out.println("Codigo: " + produto.getCodigo());
				
				produto.setNome(rs.getString("nome_pessoa"));
				System.out.println("nome: " + produto.getNome());
				
				produto.setEmail(rs.getString("email_pessoa"));
				System.out.println("Email: " + produto.getEmail());
				
				produto.setTelefone(rs.getString("tel_pessoa"));
				System.out.println("Tel: " + produto.getTelefone());
				
				produto.setCpf(rs.getString("cpf_pessoa"));
				System.out.println("CPF: " + produto.getCpf());
				
				produto.setDataNascimento(rs.getDate("dtnasc_pessoa"));
				
				
				produto.setSituacaoPessoa(rs.getInt("situacao_pessoa"));
				System.out.println("Situção pessoa: " + produto.getSituacaoPessoa());
			}
			
			System.out.println("produto todo " + produto);
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

	public List<Pessoa> listarPessoas(String nome) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM pessoa WHERE upper(nome_pessoa) like upper('" + nome + "%')");
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
}
