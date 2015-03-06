package es.uniovi.asw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Category {
	
	private String name;
	private ArrayList <Question> questions= new ArrayList<Question>();

	public void addQuestions(Question question)
	{
		questions.add(question);
	}
	
	public void removeQuestions (Question question)
	{
		questions.remove(question);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		
		return "Category [name=" + name + ", questions=" + questions + "]";
	}
	
	public String toJSON()
	{
		Gson g= new Gson();
		return g.toJson(this);
	}
	
	/**
	 * Devuelve la pregunta mas facil y mas dificil de la categoria
	 * @return
	 */
	public Map<String, Question> showEstadisticsCategory()
	{
		Question preguntaMasDificil=questions.get(0);
		Question preguntaMasFacil=questions.get(0);
		
		Map<String, Question> preguntaFacilDificil= new HashMap<String,Question>();
		for(Question q : questions)
		{		
			if(q.getVecesAcertada()>preguntaMasFacil.getVecesAcertada())
				preguntaMasFacil=q;
			
			if(q.getVecesFallada()>preguntaMasDificil.getVecesFallada())
				preguntaMasDificil=q;
		}
		preguntaFacilDificil.put("preguntaFacil", preguntaMasFacil);
		preguntaFacilDificil.put("preguntaDificil", preguntaMasDificil);
		
		return preguntaFacilDificil;
	}
}
