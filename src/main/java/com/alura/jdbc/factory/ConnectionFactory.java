package com.alura.jdbc.factory;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static EnvConfig config = new EnvConfig();
	private static DataSource datasource;

	
	public static Connection recuperaConexion(){
		
		try {
			var pooledDataSource = new ComboPooledDataSource();
			pooledDataSource.setJdbcUrl(config.url);
			pooledDataSource.setUser(config.user);
			pooledDataSource.setPassword (config.pw);
			datasource = pooledDataSource;
			return datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
//		EnvConfig config = new EnvConfig();
//		return DriverManager.getConnection(
//				config.url,
//				config.user,
//				config.pw);
	}

}
