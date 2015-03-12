package es.uniovi.asw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Category {
	
	private String name;
	private ArrayList <Question> questions= new ArrayList<Question>();

	/**
	 * Añadir una pregunta
	 * @param question
	 */
	public void addQuestions(Question question){
		questions.add(question);
	}
	
	/**
	 * Eliminar un pregunta
	 * @param question
	 */
	public void removeQuestions (Question question){
		questions.remove(question);
	}

	/**
	 * Devuelve el valor de name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve el valor de questions
	 * @return questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Cambia el valor de name id 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el valor de name y questions
	 */
	@Override
	public String toString() {
		return "Category [name=" + name + ", questions=" + questions + "]";
	}
	
	/**
	 * Devuelve la representacion en formato JSON de la pregunta.
	 * Cabe añadir que es independiente del formato de entrada
	 * @return String JSON
	 */
	public String toJSON(){
		Gson g= new Gson();
		return g.toJson(this);
	}
	
	/**
	 * Devuelve la pregunta mas facil y mas dificil de la categoria junto con su nombre
	 * @return
	 */
	public Map<String, Object> showEstadisticsCategory() {
		Question preguntaMasDificil=questions.get(0);
		Question preguntaMasFacil=questions.get(0);
		
		Map<String, Object> preguntaFacilDificil= new HashMap<String,Object>();
		for(Question q : questions)
		{		
			if(q.getVecesAcertada()>preguntaMasFacil.getVecesAcertada())
				preguntaMasFacil=q;
			
			if(q.getVecesFallada()>preguntaMasDificil.getVecesFallada())
				preguntaMasDificil=q;
		}
		preguntaFacilDificil.put("name", getName());
		preguntaFacilDificil.put("preguntaFacil", preguntaMasFacil);
		preguntaFacilDificil.put("preguntaDificil", preguntaMasDificil);
		
		return preguntaFacilDificil;
	}
}
