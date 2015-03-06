package es.uniovi.asw.model;

import com.google.gson.Gson;


public class User {

	public long id;
	public String name;
	public String lastName;
	public String password;
	
	private boolean admin; //Poder administrar
	
	public int numerCorrectAnswer; //preguntas acertadas
	public int numberWrongAnswer;	// preguntas falladas
	
	
	public User(long id, String name, String lastName, int numerCorrectAnswer,
			int numberWrongAnswer, String password) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.numerCorrectAnswer = numerCorrectAnswer;
		this.numberWrongAnswer = numberWrongAnswer;
		this.password= password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getNumerCorrectAnswer() {
		return numerCorrectAnswer;
	}


	public void setNumerCorrectAnswer(int numerCorrectAnswer) {
		this.numerCorrectAnswer = numerCorrectAnswer;
	}


	public int getNumberWrongAnswer() {
		return numberWrongAnswer;
	}


	public void setNumberWrongAnswer(int numberWrongAnswer) {
		this.numberWrongAnswer = numberWrongAnswer;
	}


	public long getId() {
		return id;
	}

	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName
				+ ", password=" + password + ", admin=" + admin
				+ ", numerCorrectAnswer=" + numerCorrectAnswer
				+ ", numberWrongAnswer=" + numberWrongAnswer + "]";
	}


	public String toJSON()
	{
		Gson g= new Gson();
		return g.toJson(this);
	}
	
	
	
}
