package es.uniovi.asw.bussines;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.persistence.Driver;

public class QuestionManager {

	public Driver d;

	public QuestionManager(Driver d) {
		this.d = d;
	}

	public List<Category> cargarTablero() {
		List<Category> tablero = new ArrayList<Category>();
		List<Question> preguntas = null;
		Category c = new Category();
		try {
			tablero = d.findAllQuestion();
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
		int tamanio = tablero.size();
		if (tamanio == Game.MAX_CATEGORIAS)
			return tablero;
		else
			return null;
	}

	/**
	 * Llama a la persistencia y actualiza las estadisticas
	 * @param preguntaActual
	 * @param category
	 */
	public void updateQuestion(Question preguntaActual, Category category) {
			d.updateQuestion(preguntaActual, category);
	}
	
}
