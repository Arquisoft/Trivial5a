package es.uniovi.asw.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.bussines.QuestionManager;
import es.uniovi.asw.bussines.UserManager;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class UserManagerTest {

	@Test
	public void testLogin() {
		Driver d = new Driver();
		UserManager um = new UserManager(d);
		try {
			List<User> users = d.findAllUser();
			assertEquals(users.get(0),um.login(users.get(0)));
			User u2 = new User();
			um.login(u2);
		} catch (Exception e1) {
			assertEquals(e1.getMessage(), "Usuario no encontrado. Introduzca datos");
		}
	}

	@Test
	public void testAddUser() {
		Driver d = new Driver();
		UserManager um = new UserManager(d);
		User u = new User();
		u.setLogin("TestUser");
		u.setPassword("TestPassword");
		try {
			um.addUser(u);
			assertTrue(um.findUserByLogin("TestUser"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindUserByLogin() {
		Driver d = new Driver();
		UserManager um = new UserManager(d);
		try {
			List<User> users = d.findAllUser();
			assertTrue(um.findUserByLogin(users.get(0).getLogin()));
			assertFalse(um.findUserByLogin("Random"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
