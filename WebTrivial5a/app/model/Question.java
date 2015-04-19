package model;

import com.google.gson.Gson;


public class Question {

	public String identifier; // Identifica inequivocamente a la pregunta
	
	public static final int MAX_ANSWER = 4;
	
	
	public String category; // Una pregunta solo puede tener una categoria
								// asociada
	
	public String query;
	
	
	
	public String correctAnswer; // Solo puede tener una respuesta correcta. Se
								// podria modificar llegado el caso
	
	public String[] wrongAnswers; // Array de preguntas incorrectas
	public int vecesFallada;
	public int vecesAcertada;
	
	
	
	/**
	 * Devuelve la representacion en formato JSON de la pregunta. Cabe añadir
	 * que es independiente del formato de entrada
	 * 
	 * @return String JSON
	 */
	public String toJSON() {
		Gson g = new Gson();
		return g.toJson(this);
	}
}
