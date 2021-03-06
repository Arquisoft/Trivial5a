package es.uniovi.asw.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class User {

	public long id;

	public String login;

	public String password;

	private boolean admin; // Poder administrar

	public int numberCorrectAnswer; // preguntas acertadas

	public int numberWrongAnswer; // preguntas falladas

	private int posicion;

	public User(long id, String login, String password,
			int numberCorrectAnswer, int numberWrongAnswer, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.numberCorrectAnswer = numberCorrectAnswer;
		this.numberWrongAnswer = numberWrongAnswer;
		this.password = password;
		this.admin = admin;
	}

	public User() {
	}

	/**
	 * Devuelve el valor de login
	 * 
	 * @return name
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Cambia el valor de login
	 * 
	 * @param name
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Devuelve el valor de numerCorrectAnswer
	 * 
	 * @return numerCorrectAnswer
	 */
	public int getNumberCorrectAnswer() {
		return numberCorrectAnswer;
	}

	/**
	 * Cambia el valor de numerCorrectAnswer
	 * 
	 * @param numerCorrectAnswer
	 */
	public void setNumberCorrectAnswer(int numberCorrectAnswer) {
		this.numberCorrectAnswer = numberCorrectAnswer;
	}

	/**
	 * Devuelve el valor de numberWrongAnswer
	 * 
	 * @return numberWrongAnswer
	 */
	public int getNumberWrongAnswer() {
		return numberWrongAnswer;
	}

	/**
	 * Cambia el valor de numberWrongAnswer
	 * 
	 * @param numberWrongAnswer
	 */
	public void setNumberWrongAnswer(int numberWrongAnswer) {
		this.numberWrongAnswer = numberWrongAnswer;
	}

	/**
	 * Devuelve el valor de id
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Devuelve el valor de password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Cambia el valor de password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Devuelve el valor de admin
	 * 
	 * @return admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Cambia el valor de admin
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Devuelve el id del usuario,login, contraseña, si es administrador o no,
	 * el número de respuestas correctas y el númer de respuestas falladas
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", admin=" + admin + ", numberCorrectAnswer="
				+ numberCorrectAnswer + ", numberWrongAnswer="
				+ numberWrongAnswer + "]";
	}

	/**
	 * Devuelve la representacion en formato JSON de la pregunta. Cabe añadir
	 * que es independiente del formato de entrada
	 * 
	 * @return String JSON
	 */
	public String toJSON() {
		Gson g = new Gson();
		return g.toJson(this);
	}

	/**
	 * Devuelve las estadísticas del usuario mostrando el número de preguntas
	 * acertadas y falladas
	 * 
	 * @return vecesFallosAciertos
	 */
	public Map<String, Object> showStadistics() {
		Map<String, Object> vecesFallosAciertos = new HashMap<String, Object>();
		vecesFallosAciertos.put("usuario", login);
		vecesFallosAciertos.put("aciertos", numberCorrectAnswer);
		vecesFallosAciertos.put("fallos", numberWrongAnswer);
		return vecesFallosAciertos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * Devuelve el valor de posicion
	 * 
	 * @return posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * Cambia el valor de posicion
	 * 
	 * @param posicion
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
