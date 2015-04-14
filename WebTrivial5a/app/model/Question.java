package model;
import javax.persistence.Entity;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

import java.util.*;

import javax.persistence.*;

@Entity
public class Question extends Model {
	
	
	@Required @Id
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
	
	public static Question findById(Long id) {
		return finder.byId(id);
	}
 
	public static List<Question> all() {
		return finder.all();
	}

	public static void create(Question question) {
		question.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	/**
	 * Metodo que sirve para cambiar el ID a otro id legible para el modelo
	 * @param identifier
	 * @return
	 */
	public Long getID()
	{
		return Long.valueOf(this.identifier.substring(1));
		
	}
	public static Finder<Long, Question> finder = new Finder(Long.class, Question.class);

}
