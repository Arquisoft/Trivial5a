package es.uniovi.asw.bussines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class Game {

	private List<User> usuarios;
	private QuestionManager questionManager;
	private UserManager userManager;
	private List<Category> tablero;
	private Driver d = new Driver();//Solo se carga una vez el Driver por ejecucion
	
	private  User usuarioActivo; //Se usa para gestionar el turno activo del usuario
	
	/**
	 * Constructor de la clase Game.
	 * Crea los gestores de preguntas  y usuarios y les pasa el driver de la BBDD
	 * Este metodo no carga nada
	 * Los usuarios se cargan cuando se loguean en la aplicacion con el metodo accederJuego()
	 * Las preguntas se cargan desde el gestor de preguntas en el metodo Initialize()
	 */
 	public Game() {
		this.questionManager = new QuestionManager(d);
		this.userManager = new UserManager(d);
		this.tablero = new ArrayList<Category>();
		this.usuarios= new ArrayList<User>();
		/*Category c = new Category();
		Question q = new Question();
		q.setVecesFallada(4);
		q.setVecesAcertada(3);
		c.addQuestions(q);
		tablero.add(c);*/
	}

	public void initialize() {
		questionManager.cargarPreguntas();
		
	}
	
	/**
	 * Muestra las estadisticas del juego. 
	 * Muestra las preguntas acertadas y falladas de cada usuario
	 * Muestra estadisticas de preguntas. Pregunta mas dificil, mas facil,etc
	 */
	@SuppressWarnings("rawtypes")
	public void showEstadistics() {
		Map<String,List<Map>> estadisticas = new HashMap<String,List<Map>>();
		
		List<Map> estadisticasUsuarios = new ArrayList<Map>();
		List<Map> estadisticasPreguntas = new ArrayList<Map>();

		for(User user : usuarios)
			estadisticasUsuarios.add(user.showStadistics());
		
		estadisticas.put("estadisticasUsers", estadisticasUsuarios);
		
		for (Category category : tablero) {
			estadisticasPreguntas.add(category.showEstadisticsCategory());
		}
		
		estadisticas.put("estadisticasPreguntas", estadisticasPreguntas);
		
		//MOSTRAR ESTADISTICAS USERS //ESTO SE BORRARIA
		List<Map> user =	estadisticas.get("estadisticasUsers");
		for(Map m : user)
			System.out.println(m);
		
		List<Map> question =	estadisticas.get("estadisticasPreguntas");
		for(Map m : question)
			System.out.println(m.get("preguntaFacil"));
		
	}
	
	/**
	 * Devuelve el valor de usuarios
	 * @return usuarios
	 */
	public List<User> getUsuarios() {
		return usuarios;
	}

	/**
	 * Cambia el valor de usuarios
	 * @param usuarios
	 */
	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Devuelve el valor de questionManager
	 * @return questionManager
	 */
	public QuestionManager getQuestionManager() {
		return questionManager;
	}

	/**
	 * Cambia el valor de questionManager
	 * @param questionManager
	 */
	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	/**
	 * Devuelve el valor de userManager
	 * @return userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Cambia el valor de userManager
	 * @param userManager
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * Devuelve el valor de tablero
	 * @return tablero
	 */
	public List<Category> getTablero() {
		return tablero;
	}

	/**
	 * Cambia el valor de tablero
	 * @param tablero
	 */
	public void setTablero(List<Category> tablero) {
		this.tablero = tablero;
	}

	public User getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(User usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}
	
	/**
	 * Cuando el login acierta, el usuario se mete en los usuarios del juego
	 * Si no se loguea correctamente, lanza una excepcion
	 * @param user
	 * @throws Exception
	 */
	public void accederJuego(User user) throws Exception
	{
		usuarios.add(userManager.login(user));
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
		int indexUsuarioSiguente= (usuarios.indexOf(usuarioActivo)+1)%usuarios.size();
		usuarioActivo= usuarios.get(indexUsuarioSiguente);
	}
	
	/**
	 * Metodo que se llamara cada vez que acierte una pregunta
	 * Actualiza estadisticas
	 */
	public void acierta()
	{
		usuarioActivo.setNumberCorrectAnswer(usuarioActivo.getNumberCorrectAnswer()+1);
		userManager.updateUser(usuarioActivo);
	}
	
	/**
	 * Metodo que se llamara cada vez que se falle una pregunta
	 * Actualiza estadisticas y pasa el turno
	 */
	public void falla()
	{
		usuarioActivo.setNumberWrongAnswer(usuarioActivo.getNumberWrongAnswer()+1);
		userManager.updateUser(usuarioActivo);
		turnoSiguiente();
	}
	
	
}
