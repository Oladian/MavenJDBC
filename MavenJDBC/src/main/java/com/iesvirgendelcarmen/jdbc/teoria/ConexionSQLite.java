package com.iesvirgendelcarmen.jdbc.teoria;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import org.sqlite.SQLiteConfig;
public class ConexionSQLite {
	public static void main(String[] args) {
		
		Properties p = new Properties();
		try {
			p.load(new FileReader("PropiedadesSQLite/BD.properties"));
			
						
			final String DB_URL = p.getProperty("DB_URL");
			final String DRIVER = p.getProperty("DRIVER");
			final String BD = p.getProperty("BD");
			try {
				Class.forName(DRIVER);
				try {
					SQLiteConfig config = new SQLiteConfig();
					config.enforceForeignKeys(true);
					Connection connection = DriverManager.getConnection(DB_URL+BD,config.toProperties());
					Statement statement = connection.createStatement();
					String sql = "select * from libro";
					ResultSet result = statement.executeQuery(sql);
					System.out.printf("%-30s %-30s %-30s%n%s%n","Título","Categoría","Autor",
							"-----------------------------------------"
							+ "------------------------------");
					while(result.next()) {
						String nombre = result.getString("nombre");
						String autor = result.getString("autor");
						String categoria = result.getString("categoria");
						System.out.printf("%-30s %-30s %-30s%n",nombre, categoria, autor);
					}
					connection.close();
				} catch (SQLException e) {
					System.out.println("Sin acceso a la base de datos");
				}
			} catch (ClassNotFoundException e1) {
				System.out.println("Clase no encontrada");
			}
			
			
		} catch (FileNotFoundException e2) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e2) {
			System.out.println("IO Exception");
		}
		
		
		
/*		
		final String DB_URL = "jdbc:sqlite:";
		final String DRIVER = "org.sqlite.JDBC";
		final String BD = "BD/libreria";
		try {
			Class.forName(DRIVER);
			try {
				SQLiteConfig config = new SQLiteConfig();
				config.enforceForeignKeys(true);
				Connection connection = DriverManager.getConnection(DB_URL+BD,config.toProperties());
				Statement statement = connection.createStatement();
				String sql = "select * from libro";
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					String nombre = result.getString("nombre");
					String autor = result.getString("autor");
					String categoria = result.getString("categoria");
					System.out.println(nombre+"...."+categoria+"...."+autor);
				}
				connection.close();
			} catch (SQLException e) {
				System.out.println("Sin acceso a la base de datos");
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/		
		
/*
		try(Connection connection = DriverManager.getConnection("jdbc:sqlite:"
		+BD);){
			Statement statement = connection.createStatement();
			String sql = "select * from libro";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String nombre = result.getString("nombre");
				String autor = result.getString("autor");
				System.out.println(nombre+"...."+autor);
			}
			
		}
		catch (SQLException e) {
			System.out.println("Sin acceso a la base de datos");
		}
*/
		}

}
