package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bradesco.posoTeatro.model.Cargo;
import br.com.bradesco.posoTeatro.model.Funcionario;

public class FuncionarioDao extends Funcionario {

	public String cadastrar(Funcionario funcionario) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("insert into funcionario values (?, ?, ?, ?, ?, ?, default, default)");
			
			smt.setInt(3, funcionario.getCargo().getCodigo());
			smt.setString(1, funcionario.getNome());
			smt.setString(2, funcionario.getCpfFormatado());
			smt.setString(4, funcionario.getDataNascimentoString());
			smt.setString(5, funcionario.getTelefoneFormatado());
			smt.setString(6, funcionario.getEmail());
			
			smt.executeUpdate();	
			
			smt.close();
			conn.close();
			
			return "Funcionario cadastrado.";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}

	public Funcionario consultarFuncionario(Funcionario funcionario) {
		Connection conn = new ConnectionFactory().getConnection();
		Funcionario funcionarioRetorno = new Funcionario();
		try {
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM funcionario WHERE cod_func = ?");
			smt.setInt(1,funcionario.getCodigo());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				funcionarioRetorno.setCodigo(Integer.parseInt(rs.getString("cod_func")));
				funcionarioRetorno.setNome(rs.getString("nome_func"));
				funcionarioRetorno.setCpf(rs.getString("cpf_func"));
				funcionarioRetorno.setCargo(new Cargo());
				funcionarioRetorno.getCargo().setCodigo(rs.getInt("cod_func"));
				//funcionarioRetorno.setCargo(getCargo().setNome(rs.getString("cargo_func")));
				funcionarioRetorno.setDataNascimento(rs.getDate("dtnasc_func"));
				funcionarioRetorno.setTelefone(rs.getString("tel_func"));
				funcionarioRetorno.setEmail(rs.getString("email_func"));
				rs.close();
				smt.close();
				return funcionarioRetorno;
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
	
	public boolean alterarSenha(Funcionario funcionario) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
				
			PreparedStatement smt = conn.prepareStatement("UPDATE funcionario SET senha_func = ? WHERE cod_func = ?");
			
			smt.setString(1, funcionario.getSenha());
			smt.setInt(2, funcionario.getCodigo());
			
			smt.executeUpdate();	
			
			smt.close();
			conn.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
