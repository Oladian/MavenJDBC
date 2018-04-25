package com.iesvirgendelcarmen.jdbc.teoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImp implements LibroDAO {

	private static Connection conexion = Conexion.getConexion();
	
	@Override
	public List<LibroDTO> listarLibros() {
		List<LibroDTO> listaLibros = new ArrayList<>();
		
		//crear el statement
		String sql= "SELECT * from Libro;";
		
		//crear  objeto resultSet
		try (Statement statement = conexion.createStatement();){
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				LibroDTO libro = new LibroDTO(
						resultSet.getString(2), resultSet.getString(3), 
						resultSet.getString(4), resultSet.getString(5));
				listaLibros.add(libro);
			}
		} catch (SQLException e) {
			System.out.println("Error SQL en listarLibros");
			e.printStackTrace();
		}
		return listaLibros;
	}

	@Override
	public List<LibroDTO> listarLibrosDisponibles() {
		return null;
	}

	@Override
	public boolean borrarLibro(String nombreLibro, String nombreAutor) {
		return false;
	}

	@Override
	public boolean insertarLibro(LibroDTO libro) {
		return false;
	}

	@Override
	public boolean insertarListaDeLibros(List<LibroDTO> listaLibros) {
		return false;
	}

}
