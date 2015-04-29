package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange.Map;

import controllers.MongoConnection;

public class Category {

	@Id
	@ObjectId
	public String id;

	public static String name;

	public static ArrayList<Question> questions = new ArrayList<Question>();

	public Category(String name, ArrayList<Question> preguntas) {
		super();
		this.name = name;
		this.questions = preguntas;
	}

	public Category(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Devuelve el valor de name
	 * @return name
	 */
	public static String getName() {
		return name;
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
	 * Metodo que es llamado por el anterior y guarda en la BBDD
	 * 
	 * @param category
	 */
	public static void create(Category category) throws Exception {

	}

	/**
	 * Metodo que crea un usuarios, si es admin o no
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
		return MongoConnection.findCategory(" " + name);
	}

	/**
	 * Devuelve la pregunta mas facil y mas dificil de la categoria junto con su nombre
	 * @return
	 */
	public static List<Category> showEstadisticsCategory(String name) throws Exception {
		Question preguntaMasDificil = questions.get(0);
		Question preguntaMasFacil = questions.get(0);

		HashMap<String, Object> preguntaFacilDificil = new HashMap<String, Object>();
		for (Question q : questions) {
			if (q.getVecesAcertada() > preguntaMasFacil.getVecesAcertada())
				preguntaMasFacil = q;

			if (q.getVecesFallada() > preguntaMasDificil.getVecesFallada())
				preguntaMasDificil = q;
		}
		preguntaFacilDificil.put("name", getName());
		preguntaFacilDificil.put("preguntaFacil", preguntaMasFacil);
		preguntaFacilDificil.put("preguntaDificil", preguntaMasDificil);

		return (List<Category>) preguntaFacilDificil;
	}

	public static List<Category> all() throws Exception {
		return MongoConnection.findAllQuestion();
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
