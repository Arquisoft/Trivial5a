package main.java.model;

import java.util.ArrayList;

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
		String JSON= " \n"+"'"+getName()+"'"+":"+" [  \n";
		for( int i =0; i< questions.size();i++)
			if(i==questions.size()-1)
			JSON+="{ \n"+questions.get(i).toJSON();
			else
				JSON+="{ \n"+questions.get(i).toJSON()+",\n";
		
		JSON+="\n]\n}";
		return JSON;
	}
	
}
