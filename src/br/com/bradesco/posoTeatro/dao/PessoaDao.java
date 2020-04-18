package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bradesco.posoTeatro.model.Pessoa;

public class PessoaDao extends Pessoa {

	public String cadastrar(Pessoa pessoa) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("insert into pessoa values (?, ?, ?, ?, ?)");
			
			smt.setString(1, pessoa.getNome());
			smt.setString(2, pessoa.getCpfFormatado());
			smt.setString(3, pessoa.getDataNascimentoString());
			smt.setString(4, pessoa.getTelefoneFormatado());
			smt.setString(5, pessoa.getEmail());
			
			smt.executeUpdate();	
			
			smt.close();
			conn.close();
			
			return "Pessoa cadastrada.";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}

	public Pessoa consultar(Pessoa pessoa) {
		Pessoa produto = new Pessoa();
		produto.setCpf(pessoa.getCpfFormatado());
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from pessoa where cpf_pessoa=?");
			
			smt.setString(1, produto.getCpf());
			
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				produto.setCodigo(rs.getInt("cod_pessoa"));
				System.out.println("nome: " + produto.getCodigo());
				
				produto.setNome(rs.getString("nome_pessoa"));
				System.out.println("nome: " + produto.getNome());
				
				produto.setEmail(rs.getString("email_pessoa"));
				System.out.println("Email: " + produto.getEmail());
				
				produto.setTelefone(rs.getString("tel_pessoa"));
				System.out.println("Tel: " + produto.getTelefone());
				
				
				produto.setDataNascimento(rs.getDate("dtnasc_pessoa"));	
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
			
			
			
//			if(rs.next()) {	
//				pessoaAltera.setNome(rs.getString("nome_pessoa"));
//				pessoaAltera.setCpf(rs.getString("cpf_pessoa"));
//				pessoaAltera.setDataNascimento(rs.getDate("dtnasc_pessoa"));	
//				pessoaAltera.setTelefone(rs.getString("tel_pessoa"));
//				pessoaAltera.setEmail(rs.getString("email_pessoa"));
//			}
			
			
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
			PreparedStatement stm = conn.prepareStatement("DELETE FROM pessoa WHERE cod_pessoa=?");
			stm.setInt(1, pessoa.getCodigo());
			
			rowAffected = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
		return rowAffected;
	}
	
}
