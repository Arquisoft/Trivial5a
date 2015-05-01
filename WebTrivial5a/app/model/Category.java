package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import com.google.gson.Gson;
import controllers.MongoConnection;

public class Category {

	@Id
	@ObjectId
	public String id;

	public String name;

	public List<Question> questions = new ArrayList<Question>();

	public Category(String name, List<Question> preguntas) {
		super();
		this.name = name;
		this.questions = preguntas;
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	/**
	 * Añadir una pregunta
	 * 
	 * @param question
	 */
	public void addQuestions(Question question) {
		questions.add(question);
	}

	/**
	 * Eliminar un pregunta
	 * 
	 * @param question
	 */
	public void removeQuestions(Question question) {
		questions.remove(question);
	}

	/**
	 * Baraja aleatoriamente las preguntas de la categoría
	 */
	public void shuffleQuestions(long seed) {
		Collections.shuffle(questions, new Random(seed));
	}

	/**
	 * Crea categoría
	 * 
	 * @param category
	 */
	public static void create(Category category) throws Exception {

	}

	/**
	 * Csrea preguntas
	 * 
	 * @param login
	 * @param password
	 */
	public static void create(String name, List<Question> preguntas)
			throws Exception {

	}

	/**
	 * Borra un usuario dado un login
	 * 
	 * @param login
	 */
	public static void delete(String name) {

	}

	/**
	 * Busca un usuario
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public static Category findOne(String name) throws Exception {
		return MongoConnection.findCategory(name);
	}
	
	/**
	 * Busca todas las preguntas
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static List<Question> findAllQuestions(String name) throws Exception {
		return MongoConnection.findAllQuestionsByCategory(name);
	}

	/**
	 * Retorna todas las categorias con todas sus preguntas cargadas.
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public static List<Category> all() throws Exception {
		return MongoConnection.findAllQuestion();
	}

	/**
	 * Retorna todas las preguntas faciles de cada categoria.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> easyQuestions() throws Exception {
		List<Category> c = MongoConnection.findAllQuestion();
		Question[] easyQuestions = new Question[c.size()];
		for (int i = 0; i < c.size(); i++) {
			for (Question q : c.get(i).questions) {
				if (easyQuestions[i] == null)
					easyQuestions[i] = q;
				if (easyQuestions[i].vecesAcertada < q.vecesAcertada)
					easyQuestions[i] = q;
			}
		}
		List<String> r = new ArrayList<String>();
		for (Question q : easyQuestions)
			r.add(q.query);
		return r;

	}

	/**
	 * Retorna todas las preguntas dificiles de cada categoria.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> hardQuestions() throws Exception {
		List<Category> c = MongoConnection.findAllQuestion();
		Question[] hardQuestions = new Question[c.size()];
		for (int i = 0; i < c.size(); i++) {
			for (Question q : c.get(i).questions) {
				if (hardQuestions[i] == null)
					hardQuestions[i] = q;
				if (hardQuestions[i].vecesFallada < q.vecesFallada)
					hardQuestions[i] = q;
			}
		}
		List<String> r = new ArrayList<String>();
		for (Question q : hardQuestions)
			r.add(q.query);
		return r;

	}

	/**
	 * Busca una categoria, una preguntas y lo actualiza
	 * 
	 * @param login
	 * @return
	 */
	public static void Update(Question q, Category category) {
		MongoConnection.updateQuestion(q, category);
	}

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
