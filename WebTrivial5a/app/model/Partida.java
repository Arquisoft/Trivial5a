package model;

import play.modules.mongodb.jackson.MongoDB;

import java.util.*;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;


import com.mongodb.DBObject;

import controllers.MongoConnection;
import model.Question;
import net.vz.mongodb.jackson.*;
import model.User;

public class Partida {
	public static final int MAX_CATEGORIAS = 6;

	
	public List<User> usuarios;
	
	public List<Long> idUsers;
	

	public Long id;
	
	public boolean finished;
	
	public  List<String> idAskedQuestions;
	public User activeUser;
	
	public Map<Long,Set<String>> quesitosPorJugador;
	
	/**
	 * Constructor con datos para guardar y recuperar
	 */
	public Partida(List<User> usuarios, List<Long> idUsers, Long id,
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
	 * Metodo que encuentra todas las partidas y los devuelve en una lista
	 * @return
	 * @throws Exception 
	 */
	  public static List<Partida> all() throws Exception {
		 return  MongoConnection.findAllPartidas();
	  }

	  /**
	   * Metodo que es llamado por el anterior y guarda en la BBDD
	   * @param user
	   */
		  public static void create(Partida partida) throws Exception {
			 MongoConnection.addPartida(partida);
		  }

		  /**
		   * Borra una partida dado un id
		   * @param login
		 * @throws Exception 
		   */
		  public static void delete(Long id) throws Exception {
			  
			  MongoConnection.removePartida(id);
			  
		  }

		 
		  
		  /**Busca una partida para un jugador dado
		   * 
		   * 
		   * @param id
		   * @return
		 * @throws Exception 
		   */
		  public static List<Partida> findPartidaUser(User user) throws Exception
		  {	
			  return MongoConnection.findPartidasJugador(user);
		  }
		  
		  /**Busca  partidas no terminadas o terminadas
		   * 
		   * @param id
		   * @return
		 * @throws Exception 
		   */
		  public static List<Partida> findActiveOrNot(boolean finished) throws Exception
		  {
			 return  MongoConnection.findPartidasActiveOrNot(finished)	;
			 
		  }
		  
		  public static Partida findOne(Long id) throws Exception
		  {
			  return MongoConnection.findPartida(id);
		  }
		  
		  
		  /**
			 * Metodo que se llamara cada vez que acierte una pregunta
			 * Actualiza estadisticas
			 */
			public void acierta(Question preg, boolean quesito)
			{
				activeUser.numberCorrectAnswer+=1;
				preg.vecesAcertada+=1;
				if(quesito)
				{
					quesitosPorJugador.get(activeUser).add(preg.category);
				}
				
				if(quesitosPorJugador.get(activeUser).size()==MAX_CATEGORIAS)
					terminarPartida();
				
			}
			
			/**
			 * Metodo que se llamara cada vez que se falle una pregunta
			 * Actualiza estadisticas y pasa el turno
			 */
			public void falla(Question preg)
			{
				activeUser.numberWrongAnswer+=1;
				
				preg.vecesFallada+=1;
				
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
			 * @throws Exception 
			 */
			public Question devolverPregunta(String category) throws Exception
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
			
			
			public boolean  containsUser(User user)
			{
				
				if(usuarios.contains(user))
						return true;
				return false;
			}
			
			/**
			 * Metodo que simula la tirada de un dado. 
			 * @return un numero aleatorio entre 1 y 6
			 */
			public int tirarDado()
			{	Random r = new Random();
				 return r.nextInt(6)+1; 
			}
			
			/**
			 * Si se falla se cambia el turno, al turno siguiente
			 */
			public void turnoSiguiente()
			{
				int indexUsuarioSiguente= (usuarios.indexOf(activeUser)+1)%usuarios.size();
				activeUser= usuarios.get(indexUsuarioSiguente);
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
