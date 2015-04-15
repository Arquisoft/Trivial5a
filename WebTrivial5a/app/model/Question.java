package model;
import javax.persistence.Entity;

import play.data.validation.Constraints.Required;

@Entity
public class Question {
	
	
	
	public String identifier; // Identifica inequivocamente a la pregunta
	
	public static final int MAX_ANSWER = 4;
	
	@Required
	public String category; // Una pregunta solo puede tener una categoria
								// asociada
	@Required
	public String query;
	
	
	@Required
	public String correctAnswer; // Solo puede tener una respuesta correcta. Se
								// podria modificar llegado el caso
	
	@Required
	public String[] wrongAnswers; // Array de preguntas incorrectas
	public int vecesFallada;
	public int vecesAcertada;
	
	
	
	/**
	 * Metodo que sirve para cambiar el ID a otro id legible para el modelo
	 * @param identifier
	 * @return
	 */
	public Long getID()
	{
		return Long.valueOf(this.identifier.substring(1));
		
	}

}
