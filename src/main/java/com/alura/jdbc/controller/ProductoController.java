package com.alura.jdbc.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;

import java.util.List;
import java.util.HashMap;


public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Map<String, String>> listar() throws SQLException {
		// TODO
		
		Connection con = ConnectionFactory.recuperaConexion(); 
		Statement statement = con.createStatement();
		statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		ResultSet resultSet = statement.getResultSet();
		List<Map<String, String>> resultado = new ArrayList<>(); 
		while(resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("ID")));
			fila.put("NOMBRE", resultSet.getString("NOMBRE"));
			fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
			fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
			
			resultado.add(fila);
			
		}
		con.close();
		
		
		return resultado;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
		// TODO
		Connection con = ConnectionFactory.recuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("INSERT INTO PRODUCTO (nombre, descripcion, cantidad)"
				+ "VALUES ('" + producto.get("NOMBRE") + "', " 
				+ "'" + producto.get("DESCRIPCION") + "', "
				+ "'" + producto.get("CANTIDAD") + "')", Statement.RETURN_GENERATED_KEYS);
		ResultSet result = statement.getGeneratedKeys();
		while(result.next()) {
			System.out.println(
					String.format("El ID del valor insertado fue %d", result.getInt(1))
					);
		}


	}

}
