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
public class CategoryTest {
	
	Category category = new Category();
	
	@Test
	public void test() {
		category.setName("primer pregunta");
		Question question = new Question();
		question.addWrongAnswer("Lunes", 0);
		question.addWrongAnswer("Martes",1);
		question.setCorrectAnswer("Domingo");
		question.setIdentifer("uno");
		question.setQuery("q1");
		category.addQuestions(question);
		assertEquals("Category [name=primer pregunta, "
				+ "questions=[Question [identifier= uno, query=q1, correctAnswer=Domingo, "
				+ "wrongAnswers=[Lunes, Martes]]]]",category.toString());
	}

}
