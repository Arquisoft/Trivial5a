package es.uniovi.asw.bussines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import es.uniovi.asw.gui.VentanaEstadisticas;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class Game {
	
	
	
	public static final int MAX_CATEGORIAS = 6;
	private List<User> usuarios;
	private QuestionManager questionManager;
	private UserManager userManager;
	private List<Category> tablero;
	private Driver d = new Driver();//Solo se carga una vez el Driver por ejecucion
	
	private Map<User,Set<String>> preguntasAcertadas;
	
	//private Question preguntaActual;											
	
	private User usuarioActivo; //Se usa para gestionar el turno activo del usuario
	
	/**
	 * Constructor de la clase Game.
	 * Crea los gestores de preguntas  y usuarios y les pasa el driver de la BBDD
	 * Este metodo no carga nada
	 * Los usuarios se cargan cuando se loguean en la aplicacion con el metodo accederJuego()
	 * Las preguntas se cargan desde el gestor de preguntas en el metodo Initialize()
	 */
 	public Game() {
 		try {
			d.imprimirDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.questionManager = new QuestionManager(d);
		this.userManager = new UserManager(d);
		this.usuarios= new ArrayList<User>();
		this.preguntasAcertadas= new HashMap<User,Set<String>>();
		
	}

	/**
	 * Muestra las estadisticas del juego. 
	 * Muestra las preguntas acertadas y falladas de cada usuario
	 * Muestra estadisticas de preguntas. Pregunta mas dificil, mas facil,etc
	 * @throws Exception 
	 */
	public List<User> showEstadisticsUser() throws Exception {
		List<User> estadisticasUser =userManager.d.findAllUser();
		
		
		return estadisticasUser;
	}
	
	public List<Category> showEstadisticsQuestion() throws Exception {
		List<Category> estadisticasCategory =questionManager.d.findAllQuestion();
		
		
		return estadisticasCategory;
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
		preguntasAcertadas.put(user, new HashSet<String>());
		
	}
	
	public void validarTodosUsuarios(List<User> usuarios) throws Exception {
		for(User u: usuarios) {
			accederJuego(u);
		}
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
	public void acierta(Question preg, boolean quesito)
	{
		
		usuarioActivo.setNumberCorrectAnswer(usuarioActivo.getNumberCorrectAnswer()+1);
		userManager.updateUser(usuarioActivo);
		
			preg.setVecesAcertada(preg.getVecesAcertada()+1);
		questionManager.updateQuestion(preg,preg.getCategory());

		
		if (quesito) { //Si es quesito mete la el nombre de la categoria, sino no hace nada
				preguntasAcertadas.get(usuarioActivo).add(preg.getCategory());
		}
		
		if(preguntasAcertadas.get(usuarioActivo).size()==MAX_CATEGORIAS)
			terminarPartida();
	}
	
	/**
	 * Metodo final del juego. Cuando algun usuario acaba  se llama a este metodo
	 */
	private void terminarPartida() {
		new VentanaEstadisticas(this);
		
	}

	/**
	 * Metodo que se llamara cada vez que se falle una pregunta
	 * Actualiza estadisticas y pasa el turno
	 */
	public void falla(Question preg)
	{
		
		
		usuarioActivo.setNumberWrongAnswer(usuarioActivo.getNumberWrongAnswer()+1);
		userManager.updateUser(usuarioActivo);
		preg.setVecesFallada(preg.getVecesFallada()+1);
		questionManager.updateQuestion(preg,preg.getCategory());
		turnoSiguiente();
	}
	
}
