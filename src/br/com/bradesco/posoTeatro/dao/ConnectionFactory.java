package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); /* Aqui registra */
	        return DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=posoTeatro;user=sa;password=123456");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
	}
	}
}
