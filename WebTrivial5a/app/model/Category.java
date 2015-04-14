package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import play.data.validation.Constraints.Required;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;



@Entity
public class Category extends Model{

	@Required @Id
	public String name;
	
	@Required
	public ArrayList<Question> questions= new ArrayList<Question>();
	public ArrayList<Question> usedQuestions = new ArrayList<Question>();
	
	
	public static List<Category> all() {
		return finder.all();
	}
	
	
	/**
	 * Baraja aleatoriamente las preguntas de la categoría
	 */
	private void shuffleQuestions(long seed)
	{
		Collections.shuffle(questions, new Random(seed));
	}
	
	public static Finder<String, Category> finder = new Finder(String.class, Category.class);
}
