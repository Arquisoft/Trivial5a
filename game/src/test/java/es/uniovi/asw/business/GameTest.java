package es.uniovi.asw.business;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.bussines.QuestionManager;
import es.uniovi.asw.bussines.UserManager;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class GameTest {
	Game game = new Game();
	
	@Test
	public void testGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowEstadistics() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsuarios() {
		User u1 = new User();
		u1.setLogin("TestUser1");
		u1.setNumberCorrectAnswer(1);
		u1.setNumberWrongAnswer(1);
		u1.setPassword("TestPassword");
		u1.setPosicion(1);
		u1.setAdmin(false);
		
		User u2 = new User(1, "TestUser2", "TestPassword2", 2, 2, true);
		
		ArrayList<User> usuarios = new ArrayList<User>();
		usuarios.add(u1);
		usuarios.add(u2);
		
		game.setUsuarios(usuarios);
		
		assertEquals(usuarios, game.getUsuarios());
	}

	@Test
	public void testGetQuestionManager() {
		Driver d = new Driver();
		QuestionManager questionManager = new QuestionManager(d);
		
		game.setQuestionManager(questionManager);
		
		assertEquals(questionManager, game.getQuestionManager());
	}

	@Test
	public void testGetUserManager() {
		Driver d = new Driver();
		UserManager userManager = new UserManager(d);
		
		game.setUserManager(userManager);
		
		assertEquals(userManager, game.getUserManager());
	}

	@Test
	public void testGetUsuarioActivo() {
		User u1 = new User();
		u1.setLogin("TestUser1");
		u1.setNumberCorrectAnswer(1);
		u1.setNumberWrongAnswer(1);
		u1.setPassword("TestPassword");
		u1.setPosicion(1);
		
		game.setUsuarioActivo(u1);
		
		assertEquals(u1, game.getUsuarioActivo());
	}

	@Test
	public void testAccederJuego() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidarTodosUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testTirarDado() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnoSiguiente() {
		fail("Not yet implemented");
	}

	@Test
	public void testAcierta() {
		fail("Not yet implemented");
	}

	@Test
	public void testFalla() {
		fail("Not yet implemented");
	}

}
