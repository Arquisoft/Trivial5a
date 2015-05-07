package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.google.gson.Gson;

public class Question {

    public String identifer; // Identifica inequivocamente a la pregunta

    public static final int MAX_ANSWER = 4;

    public String category; // Una pregunta solo puede tener una categoria
			    // asociada
    public String query;

    public String correctAnswer; // Solo puede tener una respuesta correcta. Se
				 // podria modificar llegado el caso
    public String[] wrongAnswers; // Array de preguntas incorrectas

    public int vecesFallada;

    public int vecesAcertada;

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
	long s = System.nanoTime();
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
}
