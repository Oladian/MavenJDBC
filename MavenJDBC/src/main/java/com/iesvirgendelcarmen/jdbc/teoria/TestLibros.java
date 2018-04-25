package com.iesvirgendelcarmen.jdbc.teoria;

import java.util.ArrayList;
import java.util.List;

public class TestLibros {

	public static void main(String[] args) {
		LibroDAO manipLibros = new LibroDAOImp();
		
		List<LibroDTO> listaTotal = manipLibros.listarLibros();
		List<LibroDTO> listaLibrosDisponibles = manipLibros.listarLibrosDisponibles();
		List<LibroDTO> listaDeObjetosLibro = new ArrayList<>();
		
		LibroDTO libro1 = new LibroDTO("Las maravillas de la Informatica", "Esteban", "Editorial 4", "Hardware");
		LibroDTO libro2 = new LibroDTO("Electronica Avanzada", "Claudia", "Editorial 2", "Hardware");
		LibroDTO libro3 = new LibroDTO("MicroChips", "Ana", "Editorial 3", "Hardware");
		LibroDTO libro4 = new LibroDTO("Como aprobar DAM", "Los Pecos", "Editorial 3", "Programacion");
		LibroDTO libro5 = new LibroDTO("Briconsejos para desarrolladores", "Groot", "Editorial 1", "Programacion");
		
		listaDeObjetosLibro.add(libro1);
		listaDeObjetosLibro.add(libro2);
		listaDeObjetosLibro.add(libro3);
		listaDeObjetosLibro.add(libro4);
		listaDeObjetosLibro.add(libro5);
		
		// inserta los 5 libros
		
		System.out.println("Insertar libros: "+manipLibros.insertarListaDeLibros(listaDeObjetosLibro));
/*		
		System.out.println("Borrar libros: \n"+
	 	manipLibros.borrarLibro(libro1.getNombreLibro(), libro1.getNombreAutor())+"\n"+
		manipLibros.borrarLibro(libro2.getNombreLibro(), libro2.getNombreAutor())+"\n"+
		manipLibros.borrarLibro(libro3.getNombreLibro(), libro3.getNombreAutor())+"\n"+
		manipLibros.borrarLibro(libro4.getNombreLibro(), libro4.getNombreAutor())+"\n"+
		manipLibros.borrarLibro(libro5.getNombreLibro(), libro5.getNombreAutor())+"\n");
*/		
		System.out.println(listaTotal);
		//System.out.println(listaLibrosDisponibles);
	}

}
