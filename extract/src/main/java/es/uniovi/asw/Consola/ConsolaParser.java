package es.uniovi.asw.Consola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.uniovi.asw.Parser.Document;
//import es.uniovi.asw.Parser.GiftType;
import es.uniovi.asw.Parser.Parser;

public class ConsolaParser extends Parser {

	/**
	 * Constructor usado cuando el nombre del documento ya contiente su extensión
	 */
	public ConsolaParser(Document document) throws Exception {
		this.file = document;
		ConsolaUtils.setTipoDocumento(document);
	}

	/**
	 * Constructor usado cuando el nombre del documento no contiente su extensión
	 */
	public ConsolaParser(Document document, String tipoEntrada)
			throws Exception {
		this.file = document;
		ConsolaUtils.setTipoDocumento(document, tipoEntrada);
	}

	/**
	 * Lee el archivo de entrada
	 * 
	 * @throws IOException
	 */
	public void reader() throws IOException {
		String leido = new String();
		// Poner ruta de datos

		BufferedReader bf = new BufferedReader(new FileReader(file));
		while (bf.ready()) {
			leido += bf.readLine() + "\n";
		}
		lineas = leido.split("[\r\n]");
		bf.close();
	}

	/**
	 * Saca por consola para el proceso de depurabilidad para que el usuario lo
	 * vea
	 */
	public void depurar() {
		for (String linea : super.getTransformado())
			System.out.println(linea);
	}

}
