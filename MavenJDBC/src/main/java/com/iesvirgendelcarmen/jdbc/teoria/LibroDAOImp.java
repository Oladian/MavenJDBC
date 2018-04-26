package com.iesvirgendelcarmen.jdbc.teoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		List<LibroDTO> listaLibrosDisponibles = new ArrayList<>();

		//crear el statement
		String sql= "select * from libro, categoria where\n" + 
				"libro.id not in (select idLibro from prestamos) group by categoria;";
		//String sql2="SELECT * FROM libro_no_prestado;";

		//crear  objeto resultSet
		try (Statement statement = conexion.createStatement();){
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				LibroDTO libro = new LibroDTO(
						resultSet.getString(2), resultSet.getString(3), 
						resultSet.getString(4), resultSet.getString(5));
				listaLibrosDisponibles.add(libro);
			}
		} catch (SQLException e) {
			System.out.println("Error SQL en listarLibros");
			e.printStackTrace();
		}
		return listaLibrosDisponibles;
	}

	@Override
	public boolean actualizarCategoriaLibro(LibroDTO libro, String nombreCategoria) {
		String sql="UPDATE libro SET categoria=? WHERE nombre=?";
		try(PreparedStatement preparedStatement = conexion.prepareStatement(sql);) {
			preparedStatement.setString(1,nombreCategoria);
			preparedStatement.setString(2,libro.getNombreLibro());
			return preparedStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean borrarLibro(String nombreLibro, String nombreAutor) {
		String sql = "DELETE FROM libro WHERE nombre=? and autor=?;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)){
			preparedStatement.setString(1, nombreLibro);
			preparedStatement.setString(2, nombreAutor);
			return !preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error SQL en insertarLibro");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertarLibro(LibroDTO libro) {
		String sql = "INSERT INTO libros (nombre, autor, editorial, categoria) VALUES (?,?,?,?);";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)){
			preparedStatement.setString(1, libro.getNombreLibro());
			preparedStatement.setString(2, libro.getNombreAutor());
			preparedStatement.setString(3, libro.getEditorial());
			preparedStatement.setString(4, libro.getNombreCategoria());
			return preparedStatement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error SQL en insertarLibro");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertarListaDeLibros(List<LibroDTO> listaLibros) {
		try {
			conexion.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "INSERT INTO libro (nombre, autor, editorial, categoria) VALUES (?,?,?,?);";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)){
			for (LibroDTO libroDTO : listaLibros) {
				preparedStatement.setString(1, libroDTO.getNombreLibro());
				preparedStatement.setString(2, libroDTO.getNombreAutor());
				preparedStatement.setString(3, libroDTO.getEditorial());
				preparedStatement.setString(4, libroDTO.getNombreCategoria());
				preparedStatement.execute();
			}
			return true;
		} catch (SQLException e) {
			System.out.println("Error SQL en insertarLibro");
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
}
