package es.uniovi.asw.model;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class User {
	public long id;
	public String name;
	public String lastName;
	public String password;
	private boolean admin; //Poder administrar
	public int numerCorrectAnswer; //preguntas acertadas
	public int numberWrongAnswer;	// preguntas falladas
	
	public User(long id, String name, String lastName,String password,
			int numerCorrectAnswer,
			int numberWrongAnswer,boolean admin) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.numerCorrectAnswer = numerCorrectAnswer;
		this.numberWrongAnswer = numberWrongAnswer;
		this.password= password;
		this.admin=admin;
	}

	/**
	 * Devuelve el valor de name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Cambia el valor de name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el valor de lastName
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Cambia el valor de lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Devuelve el valor de numerCorrectAnswer
	 * @return numerCorrectAnswer
	 */
	public int getNumerCorrectAnswer() {
		return numerCorrectAnswer;
	}

	/**
	 * Cambia el valor de numerCorrectAnswer
	 * @param numerCorrectAnswer
	 */
	public void setNumerCorrectAnswer(int numerCorrectAnswer) {
		this.numerCorrectAnswer = numerCorrectAnswer;
	}

	/**
	 * Devuelve el valor de numberWrongAnswer
	 * @return numberWrongAnswer
	 */
	public int getNumberWrongAnswer() {
		return numberWrongAnswer;
	}

	/**
	 * Cambia el valor de numberWrongAnswer
	 * @param numberWrongAnswer
	 */
	public void setNumberWrongAnswer(int numberWrongAnswer) {
		this.numberWrongAnswer = numberWrongAnswer;
	}

	/**
	 * Devuelve el valor de id
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Devuelve el valor de password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Cambia el valor de password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Devuelve el valor de admin
	 * @return admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Cambia el valor de admin
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Devuelve el id del usuario, nombre, apellidos, contraseña, si es administrador
	 * o no, el número de respuestas correctas y el númer de respuestas falladas
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName
				+ ", password=" + password + ", admin=" + admin
				+ ", numerCorrectAnswer=" + numerCorrectAnswer
				+ ", numberWrongAnswer=" + numberWrongAnswer + "]";
	}

	/**
	 * Devuelve la representacion en formato JSON de la pregunta.
	 * Cabe añadir que es independiente del formato de entrada
	 * @return String JSON
	 */
	public String toJSON() {
		Gson g= new Gson();
		return g.toJson(this);
	}
	
	/**
	 * Devuelve las estadísticas del usuario mostrando el número de preguntas acertadas y falladas
	 * @return vecesFallosAciertos
	 */
	public Map<String, Object> showStadistics() {
		Map<String, Object> vecesFallosAciertos= new HashMap<String,Object>();
		vecesFallosAciertos.put("usuario", name);	
		vecesFallosAciertos.put("aciertos", numerCorrectAnswer);
		vecesFallosAciertos.put("fallos", numberWrongAnswer);
		return vecesFallosAciertos;
	}
}
