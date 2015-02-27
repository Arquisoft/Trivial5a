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
	public void test() {
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("uno");
		question.setQuery("q1");
		assertEquals("Question [identifier= uno, query=q1, correctAnswer=Domingo, "
				+ "wrongAnswers=[Lunes, Martes]]",question.toString());
	}

}
