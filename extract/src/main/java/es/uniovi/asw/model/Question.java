package es.uniovi.asw.model;

import java.util.Arrays;

import com.google.gson.Gson;

public class Question {
	
	/**
	 * Clase que modela una preguna del modelo GIFT
	 */
	public static final int MAX_ANSWER = 3; //Se podria cambiar facilmente si dado el caso
										 // se quisese cambiar el modelo de juego
	
	private Category category; //Una pregunta solo puede tener una categoria asociada
	private String query;	
	private String identifer; //Identifica unequivocamente a la pregunta
	private String correctAnswer; //Solo puede tener una respuesta correcta 
								  // Se podria modificar llegado el caso
	private String[] wrongAnswers; //Array de preguntas incorrectas


	private int vecesFallada;
	private int vecesAcertada;
	/**
	 * Se crea el objeto con el numero de respuestas incorrectas que sean todas menos la correcta
	 * Si hubiera mas respuestas correctas se cambiaria en el constructor
	 */
		public Question()
		{
			wrongAnswers = new String[ MAX_ANSWER-1];
		}
		
		/**
		 * Añade una respuesta incorerecta
		 * @param answer
		 */
		public void addWrongAnswer(String answer,int index)
		{
			wrongAnswers [index]=answer;
		}
		
		public void removeWrongAnswer(String answer)
		{
			for(int i =0;i< wrongAnswers.length;i++)
				if(wrongAnswers[i] == answer)
					wrongAnswers [i]=null;
		}
		

		
	@Override
		public String toString() {
			return "Question [category=" + category + ", query=" + query
					+ ", identifer=" + identifer + ", correctAnswer="
					+ correctAnswer + ", wrongAnswers="
					+ Arrays.toString(wrongAnswers) + ", vecesFallada="
					+ vecesFallada + ", vecesAcertada=" + vecesAcertada + "]";
		}

	/**
	 * Devuelve la representacion en formato JSON de la pregunta.
	 * Cabe añadir que es independiente del formato de entrada
	 * @return String JSON
	 */
	public String toJSON()
	{
		
		Gson g= new Gson();
		return g.toJson(this);
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public String getQuery() {
		return query;
	}



	public void setQuery(String query) {
		this.query = query;
	}



	public String getIdentifer() {
		return identifer;
	}



	public void setIdentifer(String identifer) {
		this.identifer = identifer;
	}



	public String getCorrectAnswer() {
		return correctAnswer;
	}



	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}



	public String[] getWrongAnswers() {
		return wrongAnswers;
	}



	public void setWrongAnswers(String[] wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public int getVecesFallada() {
		return vecesFallada;
	}

	public void setVecesFallada(int vecesFallada) {
		this.vecesFallada = vecesFallada;
	}

	public int getVecesAcertada() {
		return vecesAcertada;
	}

	public void setVecesAcertada(int vecesAcertada) {
		this.vecesAcertada = vecesAcertada;
	}
	
	
	
	
}