package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import controllers.MongoConnection;
import net.vz.mongodb.jackson.*;
import model.Question;
import play.modules.mongodb.jackson.MongoDB;


public class Category{

	@Id
	@ObjectId
	public String id;
	
	public String name;
	
	public List<Question> questions= new ArrayList<Question>();
	
	
	
	
	public Category(String name, List<Question> preguntas) {
		super();
		this.name = name;
		this.questions = preguntas;
	}
	
	public Category(String name)
	{
		super();
		this.name = name;
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
	public void shuffleQuestions(long seed)
	{
		Collections.shuffle(questions, new Random(seed));
	}


	  /**
	   * Metodo que es llamado por el anterior y guarda en la BBDD
	   * @param category
	   */
		  public static void create(Category category) throws Exception {
			
		  }

		  /**
		   * Metodo que crea un usuarios, si es admin o no
		   * @param login
		   * @param password
		   */
		  public static void create(String name,List<Question> preguntas) throws Exception{
		     
		  }

		  /**
		   * Borra un usuario dado un login
		   * @param login
		   */
		  public static void delete(String name) {
			
		  }

		  
		  /**Busca un usuario
		   * 
		   * @param login
		   * @return
		 * @throws Exception 
		   */
		  public static Category findOne(String name) throws Exception
		  {
			return MongoConnection.findCategory(" "+name);
		  }
		  
		  public static List<Category> all() throws Exception
		  {
			  return MongoConnection.findAllQuestion();
		  }
		  
		  /**Busca una categoria, una preguntas y lo actualiza
		   * 
		   * @param login
		   * @return
		   */
		  public static void Update(Question q, Category category)
		  {
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
