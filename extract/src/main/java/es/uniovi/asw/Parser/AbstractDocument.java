package es.uniovi.asw.Parser;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Category;

public class AbstractDocument implements TipoDocumento {
	
	List<Category> categorias = new ArrayList<Category>();


	/**
	 * Devuelve el mapaJSON de las categorias de las preguntas
	 */
	@Override
	public String[] transform(String[] leido) {
		
		String [] mapaJSON =new String[categorias.size()];
		
		for(int i =0;i<mapaJSON.length;i++)
		{	
			mapaJSON[i]=categorias.get(i).toJSON();
		}
		return mapaJSON;
	}

	@Override
	public boolean verify(String[] leido) {
		// TODO Auto-generated method stub
		return false;
	}

}
