package model;

import play.modules.mongodb.jackson.MongoDB;

import java.util.*;

import javax.persistence.*;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;


import com.mongodb.DBObject;

import net.vz.mongodb.jackson.*;
import net.vz.mongodb.jackson.Id;
import model.User;

public class Partida {
	
	public List<User> usuarios;
	
	private static JacksonDBCollection<Partida, String> coll = 
			MongoDB.getCollection("partidas", Partida.class, String.class);

	
	public List<Long> idUsers;
	
	@Id
	@ObjectId
	public String id;
	
	public boolean finished;
	
	public  List<Long> idAskedQuestions;
	public User activeUser;
	
	public Map<Long,Set<String>> quesitosPorJugador;
	
	/**
	 * Constructor con datos para guardar y recuperar
	 */
	public Partida(List<User> usuarios, List<Long> idUsers, String id,
			boolean finished, List<Long> idAskedQuestions, User activeUser,
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
					boolean finished, List<Long> idAskedQuestions, User activeUser,
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
		  
		  /**Busca  partidas no terminadas
		   * 
		   * @param id
		   * @return
		   */
		  public static List<Partida> findActive(String id)
		  {	
			  DBObject partida = new BasicDBObject("finished", false);
			  DBCursor<Partida> cursor = Partida.coll.find(partida);
			return cursor.toArray();
		  }
	

}
