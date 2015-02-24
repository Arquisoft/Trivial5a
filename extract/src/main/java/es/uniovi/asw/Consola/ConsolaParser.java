package es.uniovi.asw.Consola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.uniovi.asw.Parser.*;

public class ConsolaParser extends Parser{

	public ConsolaParser(Document document) throws Exception {
		file = document;
		ConsolaUtils.setTipoDocumento(file);
	}
	
	@Override
	public void reader() throws IOException
	{
		String leido= new String();
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

}
