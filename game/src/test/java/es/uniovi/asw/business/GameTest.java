package es.uniovi.asw.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.bussines.QuestionManager;
import es.uniovi.asw.bussines.UserManager;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class GameTest {
	Game game = new Game();

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
	public void testValidateAndStatistics() {
		Game game = new Game();
		Driver d = new Driver();
		QuestionManager qm = new QuestionManager(d);
		try {
			List<User> users = d.findAllUser();
			game.validarTodosUsuarios(users);
			game.setUsuarioActivo(users.get(0));
			assertEquals(users.get(0), game.getUsuarioActivo());
			assertEquals(users, game.showEstadisticsUser());
			assertEquals(qm.d.findAllQuestion(), game.showEstadisticsQuestion());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void testTirarDado() {
		int res = game.tirarDado();
		assertTrue(res <= 6 && res >= 1);
	}

	@Test
	public void testTurnoSiguiente() {
		Game game = new Game();
		
		User u1 = new User(1, "TestUser1", "TestPassword", 1, 1, false);
		User u2 = new User(2, "TestUser2", "TestPassword2", 2, 2, true);
		
		ArrayList<User> usuarios = new ArrayList<User>();
		usuarios.add(u1);
		usuarios.add(u2);
		
		game.setUsuarios(usuarios);
		game.setUsuarioActivo(u1);
		
		assertEquals(u1, game.getUsuarioActivo());
		
		// Comprobar que después del test, el usuario activo es el 2
		game.turnoSiguiente();
		assertEquals(u2, game.getUsuarioActivo());
	}

	/*
	@Test
	public void testAciertaNoQuesito() {
		Game game = new Game();
		ArrayList<User> usuarios = new ArrayList<User>();
		
		List<Category> cats = game.getQuestionManager().cargarTablero();
		Question pregunta = cats.get(1).askQuestion(System.nanoTime());
		
		Driver d = new Driver();
		try {
			List<User> users = d.findAllUser();
			for (User user : users) {
				usuarios.add(user);
				game.accederJuego(user);
				game.setUsuarioActivo(user);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Hace que el usuario activo aumente su número de respuestas acertadas y sigue siendo el mismo usuario activo
		int i = game.getUsuarioActivo().getNumberCorrectAnswer();
		
		game.acierta(pregunta, false);
		
		assertEquals(i+1, game.getUsuarioActivo().getNumberCorrectAnswer());
	}
	*/
	@Test
	public void testFalla() {
		//fail("Not yet implemented");
	}

}
