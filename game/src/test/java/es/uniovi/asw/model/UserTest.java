package es.uniovi.asw.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	User u = new User(1, "TestUser2", "TestPassword2", 2, 2, true);

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLogin() {
		assertEquals("TestUser2", u.getLogin());
	}

	@Test
	public void testGetNumberCorrectAnswer() {
		assertEquals(2, u.getNumberCorrectAnswer());
	}

	@Test
	public void testGetNumberWrongAnswer() {
		assertEquals(2, u.getNumberWrongAnswer());
	}

	@Test
	public void testGetId() {
		assertEquals(1, u.getId());
	}

	@Test
	public void testGetPassword() {
		assertEquals("TestPassword2", u.getPassword());
	}

	@Test
	public void testIsAdmin() {
		assertEquals(true, u.isAdmin());
	}

	@Test
	public void testToString() {
		String compareTo = "User [id=1, login=TestUser2, password=TestPassword2, admin=true, numberCorrectAnswer=2, numberWrongAnswer=2]";
		
		assertEquals(compareTo, u.toString());
	}

	@Test
	public void testToJSON() {
		String compareTo = "{\"id\":1,\"login\":\"TestUser2\",\"password\":\"TestPassword2\",\"admin\":true,\"numberCorrectAnswer\":2,\"numberWrongAnswer\":2,\"posicion\":0}";
		
		assertEquals(compareTo, u.toJSON());
	}

	@Test
	public void testShowStadistics() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosicion() {
		u.setPosicion(1);
		
		assertEquals(1,u.getPosicion());
	}

}
