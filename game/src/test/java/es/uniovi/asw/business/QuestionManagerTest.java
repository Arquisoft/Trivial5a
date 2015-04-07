package es.uniovi.asw.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.bussines.QuestionManager;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.persistence.Driver;

public class QuestionManagerTest {

	/**
	 * changqu
	 */
	private Driver d = new Driver();
	private QuestionManager qm = new QuestionManager(d);
	
	@Test
	public void testCargarTablero() {
		//List<Category> tablero = d.findAllQuestion();
		List<Category> tablero = getTablero();
		assertEquals(tablero, qm.cargarTablero());
	}
	
	private List<Category> getTablero(){
		List<Category> tablero = new ArrayList<Category>();
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

}
