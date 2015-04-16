package model;

import play.modules.mongodb.jackson.MongoDB;

import java.util.*;

import javax.persistence.*;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;


import com.mongodb.DBObject;

import model.Question;
import net.vz.mongodb.jackson.*;
import net.vz.mongodb.jackson.Id;
import model.User;

public class Partida {
	public static final int MAX_CATEGORIAS = 6;

	
	public List<User> usuarios;
	
	private static JacksonDBCollection<Partida, String> coll = 
			MongoDB.getCollection("partidas", Partida.class, String.class);

	
	public List<Long> idUsers;
	
	@Id
	@ObjectId
	public String id;
	
	public boolean finished;
	
	public  List<String> idAskedQuestions;
	public User activeUser;
	
	public Map<Long,Set<String>> quesitosPorJugador;
	
	/**
	 * Constructor con datos para guardar y recuperar
	 */
	public Partida(List<User> usuarios, List<Long> idUsers, String id,
			boolean finished, List<String> idAskedQuestions, User activeUser,
			Map<Long, Set<String>> quesitosPorJugador) {
		super();
		this.usuarios = usuarios;
		this.idUsers = idUsers;
		this.id = id;
		this.finished = finished;
		this.idAskedQuestions = idAskedQuestions;
		this.activeUser = activeUser;
		this.quesitosPorJugador = quesitosPorJugador;
	}
	
	
	/**
	 * Comnstructor por defecto
	 */
	public Partida ()
	{
		
	}
	
	

	/**
	 * Metodo que encuentra todos los usuarios y los devuelve en una lista
	 * @return
	 */
	  public static List<Partida> all() {
		    return Partida.coll.find().toArray();
		  }

	  /**
	   * Metodo que es llamado por el anterior y guarda en la BBDD
	   * @param user
	   */
		  public static void create(Partida partida) throws Exception {
			  if(Partida.findOne(partida.id)==null)
				  Partida.coll.save(partida);
			  else
				  throw new Exception("Usuario repetido");
		  }

		  /**
		   * Metodo que crea una partida
		   * @param login
		   * @param password
		   */
		  public static void create(List<User> usuarios, List<Long> idUsers, String id,
					boolean finished, List<String> idAskedQuestions, User activeUser,
					Map<Long, Set<String>> quesitosPorJugador) throws Exception{
		      create(new Partida(usuarios,idUsers,id,finished,idAskedQuestions,activeUser,quesitosPorJugador));
		  }

		  /**
		   * Borra una partida dado un id
		   * @param login
		   */
		  public static void delete(String id) {
			  Partida user = Partida.coll.findOneById(id);
		    if (user != null)
		    	Partida.coll.remove(user);
		  }

		  /**
		   * Elimina todos los usuarios
		   */
		  public static void removeAll(){
			  Partida.coll.drop();
		  }
		  
		  /**Busca una partida
		   * 
		   * @param id
		   * @return
		   */
		  public static Partida findOne(String id)
		  {	
			  return Partida.coll.findOneById(id);
		  }
		  
		  /**Busca  partidas no terminadas o terminadas
		   * 
		   * @param id
		   * @return
		   */
		  public static List<Partida> findActiveOrNot(boolean finished)
		  {	
			  DBObject partida = new BasicDBObject("finished", finished);
			  DBCursor<Partida> cursor = Partida.coll.find(partida);
			return cursor.toArray();
		  }
		  
		  
		  /**
			 * Metodo que se llamara cada vez que acierte una pregunta
			 * Actualiza estadisticas
			 */
			public void acierta(Question preg, boolean quesito)
			{
				activeUser.numberCorrectAnswer+=1;
				
			}
			
			/**
			 * Metodo que se llamara cada vez que se falle una pregunta
			 * Actualiza estadisticas y pasa el turno
			 */
			public void falla(Question preg)
			{
				activeUser.numberWrongAnswer+=1;
			}
			
			/**
			 * Metodo final del juego. Cuando algun usuario acaba  se llama a este metodo
			 */
			public boolean terminarPartida() {
				if (quesitosPorJugador.get(activeUser).size() == MAX_CATEGORIAS)
					return true;
				return false;
				
			}
			
			/**
			 * Devuelve una pregunta de una categoria dada
			 * @param category
			 * @return
			 */
			public Question devolverPregunta(String category)
			{
				Question q;
				do
				{
				Category c =Category.findOne(category);
				c.shuffleQuestions(new Random().nextInt());
				
				 q = c.questions.get(1);
				}
					while(isAsked(q.identifier)==false);	
					idAskedQuestions.add(q.identifier);
				return q;
			}
			
			/**
			 * Metodo que devuele si la pregunta esta repetida o no
			 * @return
			 */
			private boolean isAsked(String identifier)
			{
				if(idAskedQuestions.contains(identifier))
					return true;
				return false;			
			}
}
