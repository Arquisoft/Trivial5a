package es.uniovi.asw.extractor.consola;

import java.io.File;
import java.io.FileWriter;

import es.uniovi.asw.extractor.parser.Document;
import es.uniovi.asw.extractor.parser.GiftType;
import es.uniovi.asw.extractor.parser.QTIXMLType;


public class ConsolaUtils {

	public static String[] formatosSalidaValidos = {"JSON"};	
	
	/**
	 * Dado un nombre de archivo completo, devuelve el nombre sin la extension
	 */
	public static String getFileName(String path){

		 String fileName = null;
		 String separator = File.separator;

		 int pos = path.lastIndexOf(separator);
		 int pos2 = path.lastIndexOf(".");

		 if(pos2>-1)
		  fileName =path.substring(pos+1, pos2);
		 else
		  fileName =path.substring(pos+1);

		 return fileName;
		}
	
	/**
	 * Dado archivo, devuelve su extension
	 */
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf+1);
	}
	
	/**
	 * Dado un documento con extensión, modifica su tipo.
	 */
	public static void setTipoDocumento(Document documento) throws Exception
	{
		switch(getFileExtension(documento))
		{
			case "gift":  documento.setTipo(new GiftType()); break;
			
			case "xml":	documento.setTipo(new QTIXMLType());break;
			
			default : throw new Exception("Tipo de archivo no valido");
		}
		 
	}
	
	/**
	 * Dado un documento y un nombre de extensión, el documento modifica su tipo a la de la extension
	 */
	public static void setTipoDocumento(Document documento, String tipo) throws Exception
	{
		switch(tipo)
		{
			case "gift":  documento.setTipo(new GiftType()); break;
			
			case "xml":	documento.setTipo(new QTIXMLType());break;
			
			default : throw new Exception("Tipo de archivo no valido");
		}	 
	}	

	/**
	 * Guarda un conjunto de lineas en un fichero con un nombre dado y la extensión JSON
	 */
	  public static void saveFile(String[] lines,String savedName) throws Exception {
	    FileWriter fw = new FileWriter("src/main/java/es/uniovi/asw/data/"+savedName+".JSON");

	    for (int i = 0; i < lines.length; i++) {
	      fw.write(lines[i]+"\n");
	    }
	    fw.close();
	  }
	  
	 /**
	 * Guarda un conjunto de lineas en un fichero con un nombre dado y la extensión dada
	 */
	  public static void saveFile(String[] lines,String savedName, String extension) throws Exception {
		    FileWriter fw = new FileWriter("src/main/java/es/uniovi/asw/data/"+savedName+"."+extension);

		    for (int i = 0; i < lines.length; i++) {
		      fw.write(lines[i]+"\n");
		    }
		    fw.close();
		  }
	  
	/**
	 * Ejecuta el método de transformación correspondiente al formato de salida (actualmente, solo JSON)
	 */
	
	public static void transform(ConsolaParser parser, String formatoSalida) throws Exception
	{
		switch(formatoSalida)
		{
			case "JSON":  parser.transform(); break;
			
			
			default : throw new Exception("Formato de salida no valido");
		}	 
	}
}