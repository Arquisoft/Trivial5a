package es.uniovi.asw.game;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;

public class Game {

	private List<User> usuarios;
	private QuestionManager questionManager;
	private List<Question> tablero;
	
	public Game(List<User> usuarios) {
		this.usuarios = usuarios;
		this.questionManager = new QuestionManager();
		this.tablero = new ArrayList<Question>();
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	
	
}
