package es.uniovi.asw.bussines;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.User;

public class Game {

	private List<User> usuarios;
	private QuestionManager questionManager;
	private List<Category> tablero;
	
	public Game(List<User> usuarios) {
		this.usuarios = usuarios;
		this.questionManager = new QuestionManager();
		this.tablero = new ArrayList<Category>();
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	
	
}
