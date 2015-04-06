package es.uniovi.asw.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import es.uniovi.asw.persistence.Driver;

public class UserTest {
	User u = new User(1, "TestUser", "TestPassword", 2, 2, true);

	@Test
	public void testHashCode() {
		User u2 = new User(1, "TestUser", "TestPassword", 2, 2, true);
		assertTrue(u.hashCode() == u2.hashCode()); // Both full objects
		
		User u3 = new User(1, null, "TestPassword", 2, 2, true);
		User u4 = new User(1, null, "TestPassword", 2, 2, true);
		assertTrue(u3.hashCode() == u4.hashCode()); // Objects without login
		
		User u5 = new User(1, "TestUser", null, 2, 2, true);
		User u6 = new User(1, "TestUser", null, 2, 2, true);
		assertTrue(u5.hashCode() == u6.hashCode()); // Objects without password
	}
	
	@Test
	public void testEqualsObject() {
		
		User u2 = new User(1, "TestUser", "TestPassword", 2, 2, true);
		assertTrue(u.equals(u2)); // Two identical objects
		
		Driver d = new Driver();
		assertFalse(u.equals(d)); // Wrong class in parameter
		
		User u3 = new User(1, null, "TestPassword", 2, 2, true);
		
		assertFalse(u3.equals(u)); // Empty login in current object and not in other
		
		User u4 = new User(1, "TestUser", null, 2, 2, true);
		
		assertFalse(u4.equals(u)); // Empty password in current object and not in other
		assertFalse(u.equals(u4)); // Empty password in other object and not in current
	}
	
	@Test
	public void testEqualsNoObject() {
		assertFalse(u.equals(null));
	}

	@Test
	public void testGetLogin() {
		assertEquals("TestUser", u.getLogin());
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
		assertEquals("TestPassword", u.getPassword());
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
		 Map<String, Object> vecesFallosAciertos= new HashMap<String,Object>();
		 vecesFallosAciertos.put("usuario", "TestUser");        
		 vecesFallosAciertos.put("aciertos", 2);
		 vecesFallosAciertos.put("fallos", 2);
		 
		 assertEquals(vecesFallosAciertos, u.showStadistics());
	}

	@Test
	public void testGetPosicion() {
		u.setPosicion(1);
		
		assertEquals(1,u.getPosicion());
	}

}
