
package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import es.uniovi.asw.model.Question;

@Entity
public class Category extends Model{

	@Required @Id
	public String name;
	
	@Required
	public ArrayList<Question> questions= new ArrayList<Question>();
	public ArrayList<Question> usedQuestions = new ArrayList<Question>();
	int a = "a";
	
	
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
}
