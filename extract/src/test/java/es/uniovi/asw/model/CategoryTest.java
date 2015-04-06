/**
 * 
 */
package es.uniovi.asw.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author changqu
 *
 */
public class CategoryTest {
	
	Category category = new Category();
	
	@Test
	public void testToString() {
		category.setName("Categoria1");
		Question question = new Question();
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("ID1");
		question.setQuery("Q1");
		category.addQuestions(question);
		assertEquals("Category [name=Categoria1, "
				+ "questions=[Question [identifier= ID1, query=Q1, correctAnswer=Domingo, "
				+ "wrongAnswers=[Lunes, Martes, null]]]]",category.toString());
	}
		
	@Test
	public void testToJSONOne() {
		category.setName("Categoria1");
		Question question = new Question();
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("ID1");
		question.setQuery("Q1");
		category.addQuestions(question);
		String compareTo = "{\"name\":\"Categoria1\",\"questions\":[{\"category\":\"Categoria1\",\"query\":\"Q1\",\"identifer\":\"ID1\",\"correctAnswer\":\"Domingo\",\"wrongAnswers\":[\"Lunes\",\"Martes\",null]}]}";

		assertEquals(compareTo,category.toJSON());
	}
			
	@Test
	public void testToJSONMany() {
		category.setName("Categoria1");
		Question question = new Question();
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("ID1");
		question.setQuery("Q1");
		category.addQuestions(question);
		Question question2 = new Question();
		question2.addWrongAnswer("Lunes", 0);
		question2.addWrongAnswer("Martes",1);
		question2.setCorrectAnswer("Domingo");
		question2.setIdentifer("ID2");
		question2.setQuery("Q2");
		category.addQuestions(question2);
		
		String compareTo = "{\"name\":\"Categoria1\",\"questions\":[{\""
				+ "category\":\"Categoria1\",\"query\":\"Q1\",\""
				+ "identifer\":\"ID1\",\"correctAnswer\":\"Domingo\","
				+ "\"wrongAnswers\":[\"Lunes\",\"Martes\",null]},{\"category\""
				+ ":\"Categoria1\",\"query\":\"Q2\",\"identifer\":\"ID2\",\""
				+ "correctAnswer\":\"Domingo\",\"wrongAnswers\":[\"Lunes\",\"Martes\",null]}]}";
		
		assertEquals(compareTo,category.toJSON());
	}

	@Test
	public void testRemoveQuestion() {
		Question question = new Question();
		category.addQuestions(question);
		category.removeQuestions(question);
		
		ArrayList <Question> questions = new ArrayList <Question>();
		
		assertEquals(questions, category.getQuestions());
	}
}
