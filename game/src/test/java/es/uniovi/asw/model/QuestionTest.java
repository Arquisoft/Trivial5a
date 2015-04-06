package es.uniovi.asw.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuestionTest {

	@Test
	public void testToString() {
		Question q = new Question();

		String compareTo = "Question [category=null, query=null, identifer=null, correctAnswer=null, wrongAnswers=[null, null, null], vecesFallada=0, vecesAcertada=0]";
		
		assertEquals(compareTo, q.toString());
	}

	@Test
	public void testToJSON() {
		Question q = new Question();

		String compareTo = "{\"wrongAnswers\":[null,null,null],\"vecesFallada\":0,\"vecesAcertada\":0}";
		
		assertEquals(compareTo, q.toJSON());
	}

	@Test
	public void testGetAllAnswers() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetters() {
		Question q1 = new Question();
		q1.setCategory("TestCat");
		q1.setIdentifer("TestID");
		q1.setVecesAcertada(10);
		q1.setVecesFallada(5);
		q1.setQuery("TestQuery");
		q1.setCorrectAnswer("CorrectAnswer");
		String[] wrongs = new String[3];
		wrongs[0] = "Wrong1";
		wrongs[1] = "Wrong2";
		wrongs[2] = "Wrong3";
		q1.setWrongAnswers(wrongs);
		q1.addWrongAnswer("Wrong3", 2);
		
		assertEquals("TestCat", q1.getCategory());
		assertEquals("TestID", q1.getIdentifer());
		assertEquals(10, q1.getVecesAcertada());
		assertEquals(5, q1.getVecesFallada());
		assertEquals("TestQuery", q1.getQuery());
		assertEquals("CorrectAnswer", q1.getCorrectAnswer());
		extracted(q1, wrongs);
		
		q1.removeWrongAnswer("Wrong3");
		String[] wrongsb = new String[2];
		wrongsb[0] = "Wrong1";
		wrongsb[1] = "Wrong2";
		extracted(q1, wrongsb);
	}

	private void extracted(Question q1, String[] wrongs) {
		String[] s = q1.getWrongAnswers();
		for (int i = 0; i<wrongs.length;i++) {
			assertEquals(s[i], wrongs[i]);
		}
	}
	
	@Test
	public void testHashCode() {
		Question q1 = new Question();
		q1.setIdentifer("Test");
		Question q2 = new Question();
		q2.setIdentifer("Test");
		assertTrue(q1.hashCode() == q2.hashCode()); // Both objects with name
		
		Question q3 = new Question();
		Question q4 = new Question();
		assertTrue(q3.hashCode() == q4.hashCode()); // Both objects with name
	}
	
	@Test
	public void testEquals (){
		Question q1 = new Question();

		assertTrue(q1.equals(q1)); // Both identical
		
		assertFalse(q1.equals(null)); // Compare with null
		
		Category cat = new Category();
		assertFalse(q1.equals(cat)); // Wrong Class
		
		Question q2 = new Question();
		q2.setIdentifer("Test");
		assertFalse(q1.equals(q2)); // Own identifier null, other not null
		
		Question q3 = new Question();
		q3.setIdentifer("Test2");
		assertFalse(q2.equals(q3)); // Own identifier not null, other distinct

	}

}
