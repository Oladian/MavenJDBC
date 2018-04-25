package com.iesvirgendelcarmen.jdbc.teoria;

import java.util.List;

public interface LibroDAO {
	List<LibroDTO> listarLibros();
	List<LibroDTO> listarLibrosDisponibles();
	boolean borrarLibro(String nombreLibro, String nombreAutor);
	boolean insertarLibro(LibroDTO libro);
	boolean insertarListaDeLibros(List<LibroDTO> listaLibros);
	/* ************************************************** */
}

