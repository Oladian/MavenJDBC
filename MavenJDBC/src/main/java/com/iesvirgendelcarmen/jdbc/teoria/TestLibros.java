package com.iesvirgendelcarmen.jdbc.teoria;

import java.util.List;

public class TestLibros {

	public static void main(String[] args) {
		LibroDAO manipLibros = new LibroDAOImp();
		List<LibroDTO> listaTotal = manipLibros.listarLibros();

		System.out.println(listaTotal);
	}

}
