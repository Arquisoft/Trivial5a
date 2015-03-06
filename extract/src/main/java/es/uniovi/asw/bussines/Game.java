package es.uniovi.asw.bussines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;

public class Game {

	private List<User> usuarios;
	private QuestionManager questionManager;
	private UserManager userManager;
	private List<Category> tablero;
	
	
	public Game(List<User> usuarios) {
		this.usuarios = usuarios;
		this.questionManager = new QuestionManager();
		this.tablero = new ArrayList<Category>();
		/*Category c = new Category();
		Question q = new Question();
		q.setVecesFallada(4);
		q.setVecesAcertada(3);
		c.addQuestions(q);
		tablero.add(c);*/
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	/**
	 * Muestra las estadisticas del juego. 
	 * Muestra las preguntas acertadas y falladas de cada usuario
	 * Muestra estadisticas de preguntas. Pregunta mas dificil, mas facil,etc
	 */
	public void showEstadistics()
	{
		Map<String,List<Map>> estadisticas 
		= new HashMap<String,List<Map>>();
		
		List<Map> estadisticasUsuarios = new ArrayList<Map>();
		List<Map> estadisticasPreguntas = new ArrayList<Map>();

		for(User user : usuarios)
			estadisticasUsuarios.add(user.showStadistics());
		
		estadisticas.put("estadisticasUsers", estadisticasUsuarios);
		
		for (Category category : tablero)
		{
			estadisticasPreguntas.add(category.showEstadisticsCategory());
		}
		
		estadisticas.put("estadisticasPreguntas", estadisticasPreguntas);
		
		//MOSTRAR ESTADISTICAS USERS
		List<Map> user =	estadisticas.get("estadisticasUsers");
		for(Map m : user)
			System.out.println(m);
		
		List<Map> question =	estadisticas.get("estadisticasPreguntas");
		for(Map m : question)
			System.out.println(m.get("preguntaFacil"));
		
	}
	
	
	

	public List<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	public QuestionManager getQuestionManager() {
		return questionManager;
	}

	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public List<Category> getTablero() {
		return tablero;
	}

	public void setTablero(List<Category> tablero) {
		this.tablero = tablero;
	}

	
	
}
