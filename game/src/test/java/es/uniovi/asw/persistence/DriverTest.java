package es.uniovi.asw.persistence;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;

public class DriverTest {

	/**
	 * changqu
	 */
	
	private Driver d = new Driver();

	@Test
	public void testSaveQuestionJSON() throws Exception {
		String[] JSonArray = {"JSon1","JSon2","JSon3"};
		d.saveQuestionJSON(JSonArray);
	}
	
	@Test
	public void testFindUserStringString() throws Exception {
		d.conectDB();
		User u = new User();
		u.setLogin("TestUser");
		u.setPassword("TestPassword");
		d.addUser(u);
		User u1 = new User();
		u.setLogin("TestUser1");
		u.setPassword("TestPassword1");
		d.addUser(u1);
		User u2 = new User();
		u.setLogin("TestUser2");
		u.setPassword("TestPassword2");
		d.addUser(u2);
		assertEquals(u, d.findUser("TestUser", "TestPassword"));
		assertEquals(u1, d.findUser("TestUser1", "TestPassword1"));
		assertEquals(u2, d.findUser("TestUser2", "TestPassword2"));
	}

	@Test
	public void testFindUserString() throws Exception {
		d.conectDB();
		User u = new User();
		u.setLogin("TestUser");
		u.setPassword("TestPassword");
		d.addUser(u);
		User u1 = new User();
		u.setLogin("TestUser1");
		u.setPassword("TestPassword1");
		d.addUser(u1);
		User u2 = new User();
		u.setLogin("TestUser2");
		u.setPassword("TestPassword2");
		d.addUser(u2);
		assertEquals(u, d.findUser("TestUser"));
		assertEquals(u1, d.findUser("TestUser1"));
		assertEquals(u2, d.findUser("TestUser2"));
	}

	@Test
	public void testFindAllUser() throws Exception {
		d.conectDB();
		User u = new User();
		u.setLogin("TestUser");
		u.setPassword("TestPassword");
		d.addUser(u);
		User u1 = new User();
		u.setLogin("TestUser1");
		u.setPassword("TestPassword1");
		d.addUser(u1);
		User u2 = new User();
		u.setLogin("TestUser2");
		u.setPassword("TestPassword2");
		d.addUser(u2);
		List<User> usuarios = new ArrayList<User>();
		usuarios.add(u);usuarios.add(u1);usuarios.add(u2);
		assertEquals(usuarios, d.findAllUser());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		d.conectDB();
		User u = new User();
		u.setLogin("TestUser");
		u.setPassword("TestPassword");
		d.addUser(u);
		d.updateUser(u);
		assertEquals(u, d.findUser("TestUser"));
	}
	
	@Test
	public void testFindAllQuestion() throws Exception {
		d.conectDB();
		Question q =new Question();
		d.addQuestion(q);
		Question q1 =new Question();
		d.addQuestion(q1);
		Question q2 =new Question();
		d.addQuestion(q2);
		List<Question> question = new ArrayList<Question>();
		question.add(q);question.add(q1);question.add(q2);
		assertEquals(question, d.findAllQuestion());
	}
	
	@Test
	public void testUpdateQuestion() throws Exception {
		d.conectDB();
		Question q =new Question();
		d.addQuestion(q);
		List<Question> question = new ArrayList<Question>();
		Category c = new Category();
		d.updateQuestion(q, c);
		question.add(q);
		assertEquals(question, d.findAllQuestion());
	}

	@Test
	public void testRemoveTable() throws Exception {
		String table = "table1";
		d.removeTable(table);
	}

	@Test
	public void testImprimirDB() throws Exception {
		d.imprimirDB();
	}

}
