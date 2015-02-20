package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	
	String[] lineas;
	String transformado;
	Document file;
	
	public void reader() throws IOException
	{	String leido= new String();
		 file= new Document("data/preguntas.txt");
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
	 * Metodo que verifica que el formato de entrada sea correcto
	 */
	public boolean verificarFormato()
	{
		return file.getTipo().verify(lineas);
		
	}
	
	/**
	 * Metodo que transforma a formato JSON el archivo de entrada una vez verificado
	 */
	public void transform()
	{
		transformado=file.getTipo().transform(lineas);
		
	}

}
