package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;

import controllers.MongoConnection;

public class Partida {
	
	public int MAX_CATEGORIAS = 6;

	public List<User> usuarios = new ArrayList<User>();

	public Long id;

	public boolean finished;

	public List<String> idAskedQuestions = new ArrayList<String>();
	
	public User activeUser;
	
	public Map<String, Set<String>> quesitosPorJugador = new HashMap<String, Set<String>>();

	/**
	 * Constructor con datos para guardar y recuperar
	 */
	public Partida(List<User> usuarios, List<Long> idUsers, Long id,
			boolean finished, List<String> idAskedQuestions, User activeUser,
			Map<String, Set<String>> quesitosPorJugador) {
		super();
		this.usuarios = usuarios;
		this.id = id;
		this.finished = finished;
		this.idAskedQuestions = idAskedQuestions;
		this.activeUser = activeUser;
		this.quesitosPorJugador = quesitosPorJugador;
	}

	/**
	 * Comnstructor por defecto
	 */
	public Partida() {

	}

	/**
	 * Encuentra todas las partidas y los devuelve en una lista
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Partida> all() throws Exception {
		return MongoConnection.findAllPartidas();
	}

	/**
	 * Crea partida
	 * 
	 * @param user
	 */
	public static Partida create(Partida partida) throws Exception {
		MongoConnection.addPartida(partida);
		return MongoConnection.findPartida(partida.id);
	}

	/**
	 * Borra una partida dado un id
	 * 
	 * @param login
	 * @throws Exception
	 */
	public static void delete(Long id) throws Exception {
		MongoConnection.removePartida(id);
	}

	/**
	 * Sale de la partida
	 * 
	 * @param login
	 * @throws Exception
	 */
	public static void salirPartida(Long id, String login) throws Exception {
		User u = MongoConnection.findUser(login);
		Partida p = MongoConnection.findPartida(id);
		if(p.activeUser == u)
			p.turnoSiguiente();
		System.out.println(p.usuarios);
		p.usuarios.remove(u);
		Partida.updatePartida(p);
		System.out.println(p.usuarios);
		MongoConnection.addPartida(p);
	}

	/**
	 * Busca una partida para un jugador dado
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<Partida> findPartidaUser(User user) throws Exception {
		return MongoConnection.findPartidasJugador(user);
	}
	
	/**
	 * Actualiza partida
	 * 
	 * @param partida
	 * @throws Exception
	 */
	public static void updatePartida(Partida partida) throws Exception {
		MongoConnection.updatePartida(partida);
	}

	/**
	 * Busca partidas no terminadas o terminadas
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<Partida> findActiveOrNot(boolean finished)
			throws Exception {
		return MongoConnection.findPartidasActiveOrNot(finished);
	}

	public static Partida findOne(Long id) throws Exception {
		return MongoConnection.findPartida(id);
	}

	/**
	 * Metodo que se llamara cada vez que acierte una pregunta Actualiza
	 * estadisticas
	 */
	public void acierta(Question preg, boolean quesito) {
		activeUser.numberCorrectAnswer += 1;
		preg.vecesAcertada += 1;
		if (quesito) {
				quesitosPorJugador.get(activeUser.login).add(preg.category);
		}
		if (quesitosPorJugador.get(activeUser.login).size() == MAX_CATEGORIAS)
			terminarPartida();
		try {
			usuarios.get(usuarios.indexOf(activeUser)).numberCorrectAnswer += 1;
			Partida.updatePartida(this);
			User.updateUser(activeUser);
			Category.Update(preg, Category.findOne(preg.category));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que se llamara cada vez que se falle una pregunta Actualiza
	 * estadisticas y pasa el turno
	 */
	public void falla(Question preg) throws Exception {
		activeUser.numberWrongAnswer += 1;
		preg.vecesFallada += 1;
		try {
			usuarios.get(usuarios.indexOf(activeUser)).posicion = activeUser.posicion;
			usuarios.get(usuarios.indexOf(activeUser)).numberWrongAnswer += 1;
			
			User.updateUser(activeUser);
			turnoSiguiente();
			Category.Update(preg, Category.findOne(preg.category));
			Partida.updatePartida(this);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo final del juego. Cuando algun usuario acaba se llama a este metodo
	 */
	public boolean terminarPartida() {
		if (quesitosPorJugador.get(activeUser.login).size() == MAX_CATEGORIAS){
			this.finished = true;
			return true;
		}
		return false;
	}

	/**
	 * Devuelve una pregunta de una categoria dada
	 * 
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public Question devolverPregunta(String category) throws Exception {
		Question q;
		do {
			Category c = Category.findOne(category);
			c.shuffleQuestions(new Random().nextInt());

			q = c.questions.get(0);
		} while (isAsked(q.identifer) != false);
		idAskedQuestions.add(q.identifer);
		return q;
	}

	/**
	 * Metodo que devuele si la pregunta esta repetida o no
	 * 
	 * @return
	 */
	private boolean isAsked(String identifier) {
		if (idAskedQuestions.contains(identifier))
			return true;
		return false;
	}

	public boolean containsUser(User user) {
		if (usuarios.contains(user))
			return true;
		return false;
	}

	/**
	 * Metodo que simula la tirada de un dado.
	 * 
	 * @return un numero aleatorio entre 1 y 6
	 */
	public static int tirarDado() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}

	/**
	 * Si se falla se cambia el turno, al turno siguiente
	 */
	public void turnoSiguiente() {
		int indexUsuarioSiguente = (usuarios.indexOf(activeUser) + 1)
				% usuarios.size();
		activeUser = usuarios.get(indexUsuarioSiguente);
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
	
	public void inicializarQuesitos()
	{	
		for(User u : usuarios)
			quesitosPorJugador.put(u.login, new HashSet<String>());
	}
	
	
	public User getUserByLogin(String login)
	{
		for (User u : usuarios)
			if(u.login.equals(login))
				return u;
		return null;
		
	}
	
}
