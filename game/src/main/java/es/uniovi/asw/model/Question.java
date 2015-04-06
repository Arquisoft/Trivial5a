package es.uniovi.asw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.google.gson.Gson;

public class Question {

	/**
	 * Clase que modela una preguna del modelo GIFT
	 */
	public static final int MAX_ANSWER = 4;
	private String category; // Una pregunta solo puede tener una categoria
								// asociada
	private String query;
	private String identifer; // Identifica inequivocamente a la pregunta
	private String correctAnswer; // Solo puede tener una respuesta correcta. Se
									// podria modificar llegado el caso
	private String[] wrongAnswers; // Array de preguntas incorrectas
	private int vecesFallada;
	private int vecesAcertada;

	/**
	 * Se crea el objeto con el numero de respuestas incorrectas que sean todas
	 * menos la correcta Si hubiera mas respuestas correctas se cambiaria en el
	 * constructor
	 */
	public Question() {
		wrongAnswers = new String[MAX_ANSWER - 1];
	}

	/**
	 * Añade una respuesta incorrecta
	 * 
	 * @param answer
	 */
	public void addWrongAnswer(String answer, int index) {
		wrongAnswers[index] = answer;
	}

	/**
	 * Elimina una respuesta incorrecta
	 * 
	 * @param answer
	 */
	public void removeWrongAnswer(String answer) {
		for (int i = 0; i < wrongAnswers.length; i++)
			if (wrongAnswers[i] == answer)
				wrongAnswers[i] = null;
	}

	/**
	 * Devuelve la categoría de la pregunta, la pregunta, el identificador, la
	 * respuesta correcta, las respuestas incorrectas, el número de veces
	 * fallada y el número de veces acertada
	 */
	@Override
	public String toString() {
		return "Question [category=" + category + ", query=" + query
				+ ", identifer=" + identifer + ", correctAnswer="
				+ correctAnswer + ", wrongAnswers="
				+ Arrays.toString(wrongAnswers) + ", vecesFallada="
				+ vecesFallada + ", vecesAcertada=" + vecesAcertada + "]";
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
	 * Devuelve el valor de category
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Cambia el valor de category
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Devuelve el valor de query
	 * 
	 * @return query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Cambia el valor de query
	 * 
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Devuelve el valor de identifer
	 * 
	 * @return identifer
	 */
	public String getIdentifer() {
		return identifer;
	}

	/**
	 * Cambia el valor de identifer
	 * 
	 * @param identifer
	 */
	public void setIdentifer(String identifer) {
		this.identifer = identifer;
	}

	/**
	 * Devuelve el valor de correctAnswer
	 * 
	 * @return correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * Cambia el valor de correctAnswer
	 * 
	 * @param correctAnswer
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * Devuelve el valor de wrongAnswers
	 * 
	 * @return wrongAnswers
	 */
	public String[] getWrongAnswers() {
		return wrongAnswers;
	}

	/**
	 * Cambia el valor de wrongAnswers
	 * 
	 * @param wrongAnswers
	 */
	public void setWrongAnswers(String[] wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	/**
	 * Devuelve todas las respuesas mezcladas
	 * 
	 * @return allAnswers
	 */
	public String[] getAllAnswers() {
		String[] ans = new String[4];
		for (int i = 0; i < wrongAnswers.length; i++)
			ans[i] = wrongAnswers[i];
		ans[3] = correctAnswer;
		long s = System.nanoTime(); // seed
		Collections.shuffle(Arrays.asList(ans), new Random(s));
		return ans;
	}

	/**
	 * Devuelve el valor de vecesFallada
	 * 
	 * @return vecesFallada
	 */
	public int getVecesFallada() {
		return vecesFallada;
	}

	/**
	 * Cambia el valor de vecesFallada
	 * 
	 * @param vecesFallada
	 */
	public void setVecesFallada(int vecesFallada) {
		this.vecesFallada = vecesFallada;
	}

	/**
	 * Devuelve el valor de vecesAcertada
	 * 
	 * @return vecesAcertada
	 */
	public int getVecesAcertada() {
		return vecesAcertada;
	}

	/**
	 * Cambia el valor de vecesAcertada
	 * 
	 * @param vecesAcertada
	 */
	public void setVecesAcertada(int vecesAcertada) {
		this.vecesAcertada = vecesAcertada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifer == null) ? 0 : identifer.hashCode());
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
		Question other = (Question) obj;
		if (identifer == null) {
			if (other.identifer != null)
				return false;
		} else if (!identifer.equals(other.identifer))
			return false;
		return true;
	}
}