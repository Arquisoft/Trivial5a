/**
 * 
 */
package es.uniovi.asw.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author changqu
 *
 */
public class QuestionTest {

	Question question = new Question();
	
	@Test
	public void testToString() {
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("ID1");
		question.setQuery("TestQuery");
		assertEquals("Question [identifier= ID1, query=TestQuery, correctAnswer=Domingo, "
				+ "wrongAnswers=[Lunes, Martes, null]]",question.toString());
	}
	
	@Test
	public void testToJSON() {
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("ID1");
		question.setQuery("TestQuery");
		String compareTo = "{\"query\":\"TestQuery\",\"identifer\":\"ID1\",\"correctAnswer\":\"Domingo\",\"wrongAnswers\":[\"Lunes\",\"Martes\",null]}";
		assertEquals(compareTo,question.toJSON());
	}
	
	@Test
	public void testCategories() {
		question.setCategory("CategoryTest");
		assertEquals("CategoryTest", question.getCategory());
	}
	
	@Test
	public void testQueries() {
		question.setQuery("TestQuery");
		assertEquals("TestQuery", question.getQuery());
	}
	
	@Test
	public void testIdentifiers() {
		question.setIdentifer("TestID");;
		assertEquals("TestID", question.getIdentifer());
	}
	
	@Test
	public void testWrongAnswers() {
		String[] wrong = new String[2];
		wrong[0] = "Lunes";
		wrong[1] = "Martes";
		question.setWrongAnswers(wrong);
		assertArrayEquals(wrong, question.getWrongAnswers());
	}
	
	@Test
	public void testRemoveWrong() {
		Question q = new Question();
		q.addWrongAnswer("Lunes", 0);
		q.addWrongAnswer("Martes", 1);
		q.removeWrongAnswer("Lunes");
		String[] wrong = new String[3];
		wrong[1] = "Martes";
		assertArrayEquals(wrong,q.getWrongAnswers());
	}

}
