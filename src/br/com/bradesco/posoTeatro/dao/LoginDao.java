package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bradesco.posoTeatro.model.Funcionario;

public class LoginDao {
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public Funcionario validarLogin(Funcionario funcionario) {
		Funcionario funcionarioRetorno = new Funcionario();
		try {
			PreparedStatement smt = conn.prepareStatement("SELECT * FROM funcionario WHERE cod_func = ? AND senha_func = ?");
			smt.setInt(1,funcionario.getCodigo());
			smt.setString(2,funcionario.getSenha());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				funcionarioRetorno.setCodigo(rs.getInt("cod_func"));
				funcionarioRetorno.setCpf(rs.getString("cpf_func"));
				funcionarioRetorno.setDataNascimento(rs.getDate("dtnasc_func"));
				funcionarioRetorno.setEmail(rs.getString("email_func"));
				funcionarioRetorno.setNome(rs.getString("nome_func"));
				funcionarioRetorno.setTelefone(rs.getString("tel_func"));
				funcionarioRetorno.getCargo().setCodigo(rs.getInt("cod_cargo"));
				funcionarioRetorno.setSenha(rs.getString("senha_func"));
				rs.close();
				smt.close();
				return funcionarioRetorno;
			}
			else {
				return funcionario;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return funcionario;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
