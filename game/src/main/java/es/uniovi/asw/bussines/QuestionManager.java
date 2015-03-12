package es.uniovi.asw.bussines;

import java.util.HashSet;
import java.util.Set;

import es.uniovi.asw.model.*;
import es.uniovi.asw.persistence.Driver;

public class QuestionManager {

	public Set<Question> preguntas;
	public Driver d;
	
	public QuestionManager(Driver d) {
		this.d=d;
		this.preguntas = new HashSet<Question>();
	}

	public void cargarPreguntas() {
		// TODO Auto-generated method stub
		
	}
	
	//Hay que hacer un parser de JSON para cargar las preguntas en la lista
	
	
  	
}
