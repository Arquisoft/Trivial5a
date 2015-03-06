package es.uniovi.asw.extractor.parser;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;

public class QTIXMLType extends AbstractDocument {

	@Override
	public boolean verify(String[] leido) {
		
		Question preguntaActual = new Question();
		int respuestasIncorrectas = 0;
		
			for (int i = 0; i < leido.length; ++i){
				String linea = leido[i];
				
				// Quitar lineas vacias
				if (linea.isEmpty())
					continue;
				
				if(linea.startsWith("<item title=")){
					respuestasIncorrectas = 0;
					if(!categorias.isEmpty())
						categorias.get(categorias.size()-1).addQuestions(preguntaActual);
					preguntaActual = new Question();
					String identificador = linea.split("ident=")[1];
					preguntaActual.setIdentifer("Q" + identificador.substring(1, identificador.length()-2));
					preguntaActual.setQuery(linea.split("\"")[1]);
				}
				
				if(linea.startsWith("<qmd_topic>")){
					String nCategoria = linea.split(">")[1].split("<")[0];
					Category c = new Category();
					c.setName(nCategoria);
					categorias.add(c);
				}
				
				if(linea.startsWith("<respcondition title=\"")){
					String respuesta = linea.split("\"")[1].substring(2);
					boolean correcta = false;
					if( (leido[i+1].contains("Set") && leido[i+1].contains("100")) ||
						(leido[i+2].contains("Set") && leido[i+2].contains("100")) ||
						(leido[i+3].contains("Set") && leido[i+3].contains("100")) ||
						(leido[i+4].contains("Set") && leido[i+4].contains("100")) ||
						(leido[i+5].contains("Set") && leido[i+5].contains("100")) ||
						(leido[i+6].contains("Set") && leido[i+6].contains("100")) ){
							correcta = true;
						}
					if(correcta){
						preguntaActual.setCorrectAnswer(respuesta);
					}else{
						preguntaActual.addWrongAnswer(respuesta, respuestasIncorrectas);
						respuestasIncorrectas++;
					}
				}
				
			}
			categorias.get(categorias.size()-1).addQuestions(preguntaActual);
			ordenarPreguntasPorCategoria();
				
			return true;
		}

	private void ordenarPreguntasPorCategoria() {
		
		List<Category> aux = new ArrayList<Category>();
		Category categoriaActual = null;
		
		for(int i = 0; i < categorias.size(); i++){
			if(categoriaActual == null){
				categoriaActual = categorias.get(i);
				aux.add(categoriaActual);
			}else{
				if(categorias.get(i).getName().equals(categoriaActual.getName())){
					aux.get(aux.indexOf(categoriaActual)).addQuestions(categorias.get(i).getQuestions().get(0));
				}else{
					categoriaActual = categorias.get(i);
					aux.add(categoriaActual); 
				}
			}
		}
		categorias.clear();
		categorias = aux;
		
	}
	
	
		
		

}
	

