package es.uniovi.asw.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CategoryTest {
	
	@Test
	public void testHashCode() {
		Category c2 = new Category();
		c2.setName("Test");
		Category c3 = new Category();
		c3.setName("Test");
		assertTrue(c2.hashCode() == c3.hashCode()); // Both objects with name
		
		Category c4 = new Category();
		Category c5 = new Category();
		assertTrue(c4.hashCode() == c5.hashCode()); // Both objects without name
		
	}
	
	@Test
	public void testEqualsObject() {
		
		Category c2 = new Category();
		c2.setName("Test");
		Category c3 = new Category();
		c3.setName("Test");
		assertTrue(c2.equals(c3)); // Both objects with name
		
		Category c4 = new Category();
		c4.setName("Test2");
		assertFalse(c2.equals(c4)); // Both objects with different names
		
		Category c5 = new Category();
		Category c6 = new Category();
		assertTrue(c5.equals(c6)); // Both objects without name
		
		assertTrue(c5.equals(c5)); // Same object
		
		assertFalse(c5.equals(null)); // Null comparison
		
		User u = new User();
		assertFalse(c5.equals(u)); // Wrong class
	}

	@Test
	public void testAddGetRemoveQuestions() {
		Category cat = new Category();
		
		Question q = new Question();
		q.setQuery("QuestionTest1");
		Question q2 = new Question();
		q2.setQuery("QuestionTest2");
		
		ArrayList<Question> questions = new ArrayList<Question>();
		
		questions.add(q);
		questions.add(q2);
		
		cat.addQuestions(q);
		cat.addQuestions(q2);
		
		assertEquals(questions, cat.getQuestions());
		
		questions.remove(q);
		cat.removeQuestions(q);
		
		assertEquals(questions, cat.getQuestions());
	}

	@Test
	public void testSetGetName() {
		Category cat = new Category();
		String t = "TestCategory";
		cat.setName(t);
		
		assertEquals(t, cat.getName());
	}

	@Test
	public void testToString() {
		Category cat = new Category();
		String t = "TestCategory";
		cat.setName(t);
		
		String compareTo = "Category [name=TestCategory, questions=[]]";
		
		assertEquals(compareTo, cat.toString());
	}

	@Test
	public void testToJSON() {
		Category cat = new Category();
		String t = "TestCategory";
		cat.setName(t);
		
		String compareTo = "{\"name\":\"TestCategory\",\"questions\":[],\"usedQuestions\":[]}";
		
		assertEquals(compareTo, cat.toJSON());
	}

	@Test
	public void testShowEstadisticsCategory() {
		Category cat = new Category();
		cat.setName("Test");
		
		Question q = new Question();
		q.setQuery("QuestionTest1");
		q.setVecesAcertada(10);
		q.setVecesFallada(1);
		Question q2 = new Question();
		q2.setQuery("QuestionTest2");
		q2.setVecesAcertada(1);
		q2.setVecesFallada(10);
		Question q3 = new Question();
		q3.setQuery("QuestionTest1");
		q3.setVecesAcertada(15);
		q3.setVecesFallada(1);
		Question q4 = new Question();
		q4.setQuery("QuestionTest2");
		q4.setVecesAcertada(1);
		q4.setVecesFallada(15);
		
		cat.addQuestions(q);
		cat.addQuestions(q2);
		cat.addQuestions(q3);
		cat.addQuestions(q4);
		
		Map<String, Object> preguntaFacilDificil= new HashMap<String,Object>();
		preguntaFacilDificil.put("name", "Test");
		preguntaFacilDificil.put("preguntaFacil", q3);
		preguntaFacilDificil.put("preguntaDificil", q4);
		
		assertEquals(preguntaFacilDificil, cat.showEstadisticsCategory());
	}

	@Test
	public void testAskQuestion() {
		
	}

}
