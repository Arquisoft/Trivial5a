package es.uniovi.asw.model;

import java.util.ArrayList;
import com.google.gson.Gson;
public class Category {
	
	private String name;
	private ArrayList <Question> questions= new ArrayList<Question>();

	public void addQuestions(Question question)
	{
		questions.add(question);
		question.setCategory(this.name);
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
		Gson g = new Gson();
		return g.toJson(this);
	}
	
}
