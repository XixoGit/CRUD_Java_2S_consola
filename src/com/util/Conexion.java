package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase que tiene los metodos getConexion() y cierraConexion()
public class Conexion {
	// CONSTANTES PARA LA CONEXION
	private static final String USUARIO = "root";
	private static final String PASSWORD = "5363";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String HOST = "jdbc:mysql://localhost:3306/crudprimero?zeroDateTimeBehavior=convertToNull";
	// Utilizamos singleton para la conexion
	private static Connection conexion = null;

	public static Connection getConexion() {

		if (conexion == null) {    //si la conexion no esta hecha
			try {
				// Levanto el driver
				Class.forName(DRIVER);
				// Obtengo la conexion
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/crudprimero?zeroDateTimeBehavior=convertToNull", "root", "");
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Error intentando establecer la conexión (" + e + ")");
			}
		}
		return conexion;   //retorna la conexion ya establecida
	}
       //Constructor privado no nos interesa que nadie instancie la conexion
	public Conexion() {
	}
	public static Connection cierraConexion() throws SQLException{
		if (conexion != null) {
			conexion.close();
	}
		return conexion;    //retorna la conexion cerrada
	}
}


