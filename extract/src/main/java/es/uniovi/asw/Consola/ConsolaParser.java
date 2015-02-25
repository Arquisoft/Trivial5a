package es.uniovi.asw.Consola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.uniovi.asw.Parser.Document;
import es.uniovi.asw.Parser.GiftType;
import es.uniovi.asw.Parser.Parser;

public class ConsolaParser extends Parser {

	
	
	public ConsolaParser(String[] lineas) {
		super(lineas);
		// TODO Auto-generated constructor stub
	}

	public ConsolaParser(Document document) throws Exception  {
		this.file=document;
		ConsolaUtils.setTipoDocumento(document);
	}
	
	/**
	 * Lee el archivo de entrada
	 * @throws IOException
	 */
	public void reader() throws IOException
	{	
		String leido= new String();
		 file= new Document("src/main/java/es/uniovi/asw/data/preguntas.txt");
		 file.setTipo(new GiftType());
		//Poner ruta de datos
		 
		BufferedReader bf = new BufferedReader(new FileReader(file));
		while(bf.ready())
		{
			leido+=bf.readLine()+"\n";
		}
		lineas = leido.split("[\r\n]");
		bf.close();	
	}
	
	/**
	 * Saca por consola para el proceso de depurabilidad el mapa JSON
	 * para que el usuario lo vea
	 */
	public void depurarJSON()
	{
		for (String mapaJSON : super.getTransformado())
			System.out.println(mapaJSON);
	}

}
