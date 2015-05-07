import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.callAction;
import static play.test.Helpers.status;
import model.Partida;
import model.User;
import model.exception.IdLoginException;
import model.exception.PasswordException;

import org.junit.Test;

import controllers.Application;
import play.mvc.Result;
import play.test.FakeRequest;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {

	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}

	@Test
	public void testCallIndex() {
		Result result = callAction(controllers.routes.ref.Application.index(),
				new FakeRequest(GET, "/"));
		assertThat(status(result)).isEqualTo(OK);
	}

	@Test
	public void testUser() {

		User u = new User();
		try {
			assertThat(User.findOne(u.login)).isEqualTo(null);
			try {
				try {
					User.create(u);

				} catch (IdLoginException loginEx) {
					assertThat(loginEx.getMessage()).isEqualTo(
							"El login del usuario está vacio.");
				}
			} catch (PasswordException passEx) {
				assertThat(passEx.getMessage()).isEqualTo(
						"El password del usuario está vacio.");
			}
			try {
				u.login = "p";
				u.password = "p";
				User.create(u);
				fail("expected PasswordException: El password debe tener al menos 6 caracteres.");
			} catch (PasswordException passEx) {
				assertThat(passEx.getMessage()).isEqualTo(
						"El password debe tener al menos 6 caracteres.");

			}
			u.login = "Prueba";
			u.password = "Prueba";
			User.create(u);
			assertThat(User.findOne(u.login)).isNotNull();
			assertThat(User.all()).contains(u);
			u.password = "Prueba2";
			User.updateUser(u);
			assertThat(User.findOne(u.login).password).isNotEqualTo("Prueba");
			User.borrar(u);
			assertThat(User.all()).excludes(u);

		} catch (Exception e) {
			// rollback de mongolab
			e.printStackTrace();
		}

	}
	
	 @Test
	    public void testPartida() {

		Partida p = new Partida();
		User admin;
		
		try {
			admin = User.findOne("admin");
			p.id = (long) Partida.all().size();
			p.usuarios.add(admin);
			p.activeUser = admin;
			p.inicializarQuesitos();
			Partida.create(p);
			assertTrue(p.usuarios.contains(admin));
			assertThat(Partida.findPartidaUser(admin)).contains(p);
			assertFalse(p.usuarios.contains(User.findOne("luis")));
			assertThat(Partida.findPartidaUser(User.findOne("luis"))).excludes(p);
			assertThat(Partida.all()).contains(p);
			Partida.delete(p.id);
			assertThat(Partida.all()).excludes(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
}
