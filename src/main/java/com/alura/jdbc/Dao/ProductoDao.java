package com.alura.jdbc.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoDao {
	final private Connection con;
	public  ProductoDao(Connection con){
		this.con = con;
	}
	public void guardar(Producto producto) throws SQLException {
		// TODO
		
		con.setAutoCommit(false);

		
		PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
				+ "(nombre, descripcion, cantidad) "
				+ "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		try {
			ejecutaRegistro(producto, statement);
			con.commit();
		}catch(Exception e) {
			con.rollback();
		}
		con.close();
		statement.close();
	}
	private void ejecutaRegistro(Producto producto, PreparedStatement statement)
			throws SQLException {
		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());
		statement.execute();
		ResultSet result = statement.getGeneratedKeys();
		while(result.next()) {
			producto.setId(result.getInt(1));
			System.out.println(
					producto.toString()
					);
		}
	}
}
