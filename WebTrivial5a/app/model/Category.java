package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import model.Question;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;


 
@Entity
public class Category extends Model{

	@Required @Id
	public String name;
	
	@Required
	public ArrayList<Question> questions= new ArrayList<Question>();
	
	
	public static List<Category> all() {
		return finder.all();
	}
	
	public static Category findById(Long id) {
		return finder.byId(id);
	}
 

	public static void create(Category category) {
		category.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	/**
	 * Añadir una pregunta
	 * @param question
	 */
	public void addQuestions(Question question){
		questions.add(question);
	}
	
	/**
	 * Eliminar un pregunta
	 * @param question
	 */
	public void removeQuestions (Question question){
		questions.remove(question);
	}
	
	
	
	/**
	 * Baraja aleatoriamente las preguntas de la categoría
	 */
	private void shuffleQuestions(long seed)
	{
		Collections.shuffle(questions, new Random(seed));
	}
	
	public static Finder<Long, Category> finder = new Finder(Long.class, Category.class);
}
