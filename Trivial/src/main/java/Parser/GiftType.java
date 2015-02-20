package Parser;

import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Question;

public class GiftType implements TipoDocumento {

	List<Category> categorias = new ArrayList<Category>();
	@Override
	public String transform(String[] leido) {
		
			String JSON = "";
		for(int i =0;i<categorias.size();i++)
		{	
			JSON +=categorias.get(i).toJSON();
			if(i!=categorias.size()-1)
				JSON+=",";
		}
		System.out.println(JSON);
		return JSON;
	}

	@Override
	public boolean verify(String[] leido) {
		Category categoriaActual = null;
		Question preguntaActual = null;
		int opcionesIncorrectas =0; //Para comrpobar si esta bien constituidas las preguntas
		
		for (int i = 0; i < leido.length; ++i) 
		{

				String linea = leido[i];
				
				// Quitar lineas vacias
				if (linea.isEmpty())
					continue;

				// Si es linea de categoria

				else if (linea.toLowerCase().contains("$category")
						|| linea.toLowerCase().startsWith("$category:"))
				{
					/*if(categoriaActual!=null) //se añade la categoria a la lista para JSON
												//cuando empieza la siguiente categoria
						categorias.add(categoriaActual);*/
						
					if (preguntaActual != null) // Ya se esta dentro de la
													// categoria
					{
						System.err
								.println("No se puede poner categoría dentro de la pregunta (linea "
										+ (i+1) + ")");
						continue;
					}
					// Guardamos la categoria quitandole los ":" y el "$"
					categoriaActual = new Category();
					categoriaActual.setName(linea.split(":")[1]);
					
				}
				
				// Si es linea de identificador empieza pregnta
				else	if(linea.startsWith("::"))
				{
					opcionesIncorrectas=0; //se reinicia el contador
					if(preguntaActual!=null )
					{
						System.err
						.println("No se puede poner una pregunta dentro de otra (linea "
								+ (i+1) + ")");
				continue;
					}
					preguntaActual = new Question();
					preguntaActual.setIdentifer(linea.split("::")[1]);
					preguntaActual.setQuery(linea.split("::")[2].substring(0,
							linea.split("::")[2].length()-1));	//Quio el  { final
					
				}
				//Si es una linea de respuesta incorrecta
				else if(linea.startsWith("~"))
				{
					if(preguntaActual==null)
					{
						System.err
						.println("No se puede poner una respuesta sino esta en una pregunta(linea "
								+ (i+1) + ")");
						return false;
					}
					if(opcionesIncorrectas>=Question.MAX_ANSWER-1)
					{
						System.err
						.println("La pregunta tiene mas respuestas incorrectas de las que deberia"
								+ "(linea "+ (i+1) + ")");
						return false;
					}
					else
					{
						preguntaActual.addWrongAnswer(linea.substring(1), opcionesIncorrectas);
						opcionesIncorrectas++;
					}
				}
				//Si es una linea de respuesta correcta
				else if(linea.startsWith("="))
				{

					if(preguntaActual==null)
					{
						System.err
						.println("No se puede poner una respuesta sino esta en una pregunta(linea "
								+ (i+1) + ")");
						return false;	
					}
					if(preguntaActual.getCorrectAnswer()!=null)
					{
						System.err
						.println("No se puede poner mas de una respuesta correcta en una pregunta(linea "
								+ (i+1) + ")");
						return false;
					}
					else
						preguntaActual.setCorrectAnswer(linea.substring(1));
				}
				
				else if(linea.startsWith("}"))
				{
					if (preguntaActual.getCorrectAnswer() == null) {
						System.err.println("La pregunta no tiene respuesta correcta (linea "+i+")");
						return false;
					}
					if(opcionesIncorrectas<Question.MAX_ANSWER-1)
					{
						System.err.println("La pregunta no tiene suficientes respuestas "
								+ "incorrectas (linea "+(i+1)+")");
						return false;
					}
					if(categoriaActual.getQuestions().contains(preguntaActual))
						System.err
						.println("Pregunta repetida en la categoria(linea "
								+ (i+1) + ")");
					categoriaActual.addQuestions(preguntaActual);
					preguntaActual=null;
					int index=categorias.indexOf(categoriaActual);
					if(index==-1)
						categorias.add(categoriaActual);
					else
					categorias.set(index, categoriaActual);
				}
				else
				{
					System.err.println("La linea "+ (i+1)+" no esta correctamente  formateada para "
							+ "formato GIFT");
				}
			}
		return true;
		}
}
