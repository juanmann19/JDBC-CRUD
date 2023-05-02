package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection recuperaConexion() throws SQLException {
		EnvConfig config = new EnvConfig();
		return DriverManager.getConnection(
				config.url,
				config.user,
				config.pw);
	}

}
