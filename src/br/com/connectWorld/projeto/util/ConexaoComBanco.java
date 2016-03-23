package br.com.connectWorld.projeto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoComBanco {
	
	public Connection getConnection() throws SQLException {
		
		String banco ="projeto_final";
		String usuario= "root";
		String password = "ifpe";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
												banco,usuario, password );
										
		
	}

}
