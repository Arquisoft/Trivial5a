package main.java.Consola;

import java.io.File;
import java.io.FileWriter;

import main.java.Parser.Document;
import main.java.Parser.GiftType;

public class ConsolaUtils {

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
	
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf+1);
	}
	
	
	public static void setTipoDocumento(Document documento) throws Exception
	{
		switch(getFileExtension(documento))
		{
			case "gift":  documento.setTipo(new GiftType()); break;
			
			
			default : throw new Exception("Tipo de archivo no valido");
		}
		
	}
	

	  public static void saveFile(String[] lines,String savedName) throws Exception {
	    FileWriter fw = new FileWriter(savedName+".JSON");

	    for (int i = 0; i < lines.length; i++) {
	      fw.write(lines[i]+"\n");
	    }
	    fw.close();
	  }
	
}
