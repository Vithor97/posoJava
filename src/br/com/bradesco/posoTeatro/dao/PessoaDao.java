package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	public Pessoa consultar(String cpf) {
		Pessoa produto = new Pessoa();
		produto.setCpf(cpf);
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from pessoa where cpf_pessoa=?");
			
			smt.setString(1, produto.getCpf());
			
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
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
	
	
	public Pessoa alterar(Pessoa pessoaAltera) {
		
		
		try {
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement stm = conn.prepareStatement("Update pessoa set ");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return pessoaAltera;				
	}
	
}
