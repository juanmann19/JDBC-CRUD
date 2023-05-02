package com.alura.jdbc.pruebas;
import com.alura.jdbc.factory.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
    	Connection con = ConnectionFactory.recuperaConexion(); 

        System.out.println("Cerrando la conexión");

        con.close();
    }

}
