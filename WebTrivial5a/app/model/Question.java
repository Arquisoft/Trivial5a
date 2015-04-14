package model;

import javax.persistence.Entity;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;


@Entity
public class Question extends Model {
	
	
	@Required 
	public String identifer; // Identifica inequivocamente a la pregunta
	
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

}
