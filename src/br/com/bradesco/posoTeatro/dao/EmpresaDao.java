package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bradesco.posoTeatro.model.EmpresaResponsavel;

public class EmpresaDao extends EmpresaResponsavel {

	public String cadastrar(EmpresaResponsavel empresaResponsavel) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("INSERT INTO Empresa_Responsavel VALUES (?, ?)");

			smt.setString(1, empresaResponsavel.getCnpj().replace(".", "").replace("-", "").replace("/", ""));
			smt.setString(2, empresaResponsavel.getNome());

			smt.executeUpdate();

			smt.close();
			conn.close();

			return "Empresa cadastrada.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}

	public String alterar(EmpresaResponsavel empresaResponsavel) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("UPDATE Empresa_Responsavel SET CNPJ_EMPRESA=?, NOMEF_EMPRESA=? WHERE CNPJ_EMPRESA=?");
			smt.setString(1, empresaResponsavel.getCnpj().replace(".", "").replace("-", "").replace("/", ""));
			smt.setString(2, empresaResponsavel.getNome());

			smt.executeUpdate();

			smt.close();
			conn.close();
			return "Empresa alterada!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na alteração!";
		}
	}
	
	public String excluir(EmpresaResponsavel empresaResponsavel) {
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("DELETE FROM Empresa_Responsavel WHERE COD_EMPRESA=?");

			smt.executeUpdate();

			smt.close();
			conn.close();
			return "Empresa excluida!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na exclusão!";
		}
	}

	public EmpresaResponsavel consultar(EmpresaResponsavel empresaResponsavel) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from empresa_responsavel where cod_empresa = " + empresaResponsavel.getCodigo());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				empresaResponsavel.setNome(rs.getString("nomef_empresa"));
				String cnpj = rs.getString("cnpj_empresa");
				empresaResponsavel.setCnpj(cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "."
						+ cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14));
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empresaResponsavel;
	}
}
