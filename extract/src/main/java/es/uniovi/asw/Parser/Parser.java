package es.uniovi.asw.Parser;

import es.uniovi.asw.conf.Driver;

public class Parser {
	
	protected String[] lineas;
	String[] transformado;
	protected Document file;
	
	
	public Parser(){}
	/**
	 * 
	 * @param lineas
	 */
	public Parser(String[] lineas) {
		super();
		this.lineas = lineas;
	}

	/**
	 * Metodo que verifica que el formato de entrada sea correcto
	 */
	public boolean verificarFormato() {
		return file.getTipo().verify(lineas);
	}
	
	/**
	 * Metodo que transforma a formato JSON el archivo de entrada una vez verificado
	 */
	public void transform()	{
		transformado=file.getTipo().transform(lineas);
	}


	public String[] getLineas() {
		return lineas;
	}


	public String[] getTransformado() {
		return transformado;
	}


	public Document getFile() {
		return file;
	}
	
	/**
	 * Guarda en memoria el mapa JSON del fichero de entrada
	 */
	public void persist() {
		Driver D = new Driver();
		D.save(transformado);
	}

}
