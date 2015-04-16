package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;

import org.mongojack.ObjectId;

import model.Question;
import play.modules.mongodb.jackson.MongoDB;


public class Category{

	@Id
	@ObjectId
	public String name;
	
	public List<Question> questions= new ArrayList<Question>();
	
	
	private static JacksonDBCollection<Category, String> coll = 
			MongoDB.getCollection("categorias", Category.class, String.class);

	
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
	 * Metodo que encuentra todos los usuarios y los devuelve en una lista
	 * @return
	 */
	  public static List<Category> all() {
		    return Category.coll.find().toArray();
		  }

	  /**
	   * Metodo que es llamado por el anterior y guarda en la BBDD
	   * @param category
	   */
		  public static void create(Category category) throws Exception {
			  if(Category.findOne(category.name)==null)
				  Category.coll.save(category);
			  else
				  throw new Exception("Usuario repetido");
		  }

		  /**
		   * Metodo que crea un usuarios, si es admin o no
		   * @param login
		   * @param password
		   */
		  public static void create(String name,List<Question> preguntas) throws Exception{
		      create(new Category(name,preguntas));
		  }

		  /**
		   * Borra un usuario dado un login
		   * @param login
		   */
		  public static void delete(String name) {
			  Category category = Category.coll.findOneById(name);
		    if (category != null)
		    	Category.coll.remove(category);
		  }

		  /**
		   * Elimina todos los usuarios
		   */
		  public static void removeAll(){
			  Category.coll.drop();
		  }
		  
		  /**Busca un usuario
		   * 
		   * @param login
		   * @return
		   */
		  public static Category findOne(String name)
		  {
			  return Category.coll.findOneById(name);
		  }
		  
		  
		  /**Busca una categoria y lo actualiza
		   * 
		   * @param login
		   * @return
		   */
		  public static void Update(Category category)
		  {
			 	Category.coll.save(category);
		  }
}
