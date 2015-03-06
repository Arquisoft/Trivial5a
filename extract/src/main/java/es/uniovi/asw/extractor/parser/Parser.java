package es.uniovi.asw.extractor.parser;

import es.uniovi.asw.persistence.Driver;

public class Parser {
	
	private String[] lineas;
	String[] transformado;
	protected Document file;
	
	
	public Parser(){}
	/**
	 * 
	 * @param lineas
	 */
	public Parser(String[] lineas) {
		super();
		this.setLineas(lineas);
	}

	/**
	 * Metodo que verifica que el formato de entrada sea correcto
	 */
	public boolean verificarFormato() {
		return file.getTipo().verify(getLineas());
	}
	
	/**
	 * Metodo que transforma a formato JSON el archivo de entrada una vez verificado
	 */
	public void transform()	{
		transformado=file.getTipo().transform(getLineas());
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
	 * @throws Exception 
	 */
	public void persist() throws Exception {
		Driver D = new Driver();
		D.saveQuestionJSON(transformado);
	}
	public void setLineas(String[] lineas) {
		this.lineas = lineas;
	}

}
