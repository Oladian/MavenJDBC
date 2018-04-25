package com.iesvirgendelcarmen.jdbc.teoria;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import org.sqlite.SQLiteConfig;
public class InsertarDatos {
public static void main(String[] args) {
		
		Properties p = new Properties();
		try {
			p.load(new FileReader("PropiedadesSQLite/BD2.properties"));
			final String DB_URL = p.getProperty("DB_URL");
			final String DRIVER = p.getProperty("DRIVER");
			final String BD = p.getProperty("BD");
			
			try {
				Class.forName(DRIVER);
				try {
					SQLiteConfig config = new SQLiteConfig();
					config.enforceForeignKeys(true);
					Connection connection = DriverManager.getConnection(DB_URL+BD,config.toProperties());
					final Statement st;
					
					String sql = "INSERT INTO usuario VALUES (null,'Pacac',33)";
					st = connection.createStatement();
					st.executeUpdate(sql);
					System.out.println("Datos insertados");
					sql = "select * from usuario";
					st.executeQuery(sql);
					ResultSet result = st.executeQuery(sql);
					System.out.println(result.getString("id")+" "+result.getString("nombre")+" "+result.getString("edad"));
					connection.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}finally {
		
		}
	}
}

