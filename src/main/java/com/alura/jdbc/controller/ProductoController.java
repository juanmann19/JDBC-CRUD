package com.alura.jdbc.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;

import java.util.List;
import java.util.HashMap;


public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
		// TODO
		Connection con = ConnectionFactory.recuperaConexion();
		PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
	            + " NOMBRE = ?" 
	            + ", DESCRIPCION = ?" 
	            + ", CANTIDAD = ?" 
	            + " WHERE ID = ?");
		statement.setString(1, nombre);
		statement.setString(2, descripcion);
		statement.setInt(3, Integer.valueOf(cantidad));
		statement.setInt(4, Integer.valueOf(id));
		statement.execute();
		int updatecount = statement.getUpdateCount();
		con.close();
		return updatecount;
	}
		
	public int eliminar(Integer id) throws SQLException {
		// TODO
		Connection con = ConnectionFactory.recuperaConexion(); 
		PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
		statement.setInt(1, id);
		statement.execute();
		int updatecount = statement.getUpdateCount();
		con.close();
		return updatecount;
		
	}

	public List<Map<String, String>> listar() throws SQLException {
		// TODO
		
		Connection con = ConnectionFactory.recuperaConexion(); 
		PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		statement.execute();
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
		con.setAutoCommit(false);
		String nombre = producto.get("NOMBRE"); 
		String descripcion = producto.get("DESCRIPCION");
		Integer cantidad = Integer.valueOf(producto.get("CANTIDAD"));
		Integer maximoCantidad = 50;
		
		PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
				+ "(nombre, descripcion, cantidad) "
				+ "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		try {
			do {
				int cantidadParaGuardar = Math.min(cantidad, maximoCantidad);
				ejecutaRegistro(nombre, descripcion, cantidadParaGuardar, statement);
				cantidad-=maximoCantidad;
				
			}while(cantidad > 0);
			con.commit();
		}catch(Exception e) {
			con.rollback();
		}
		con.close();
		statement.close();
	}

	private void ejecutaRegistro(String nombre, String descripcion, Integer cantidad, PreparedStatement statement)
			throws SQLException {
		statement.setString(1, nombre);
		statement.setString(2, descripcion);
		statement.setInt(3, cantidad);
		statement.execute();
		ResultSet result = statement.getGeneratedKeys();
		while(result.next()) {
			System.out.println(
					String.format("El ID del valor insertado fue %d", result.getInt(1))
					);
		}
	}

}
