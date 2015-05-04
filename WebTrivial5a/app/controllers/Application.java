package controllers;

import java.util.List;

import model.Category;
import model.Partida;
import model.Question;
import model.User;
import play.Routes;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.categorias;
import views.html.inviteuser;
import views.html.login;
import views.html.partidas;
import views.html.preguntaventana;
import views.html.registro;
import views.html.tablero;
import views.html.usuarios;
import play.i18n.Messages;

public class Application extends Controller {

	static Form<User> userForm = Form.form(User.class);
	static Form<Partida> partidaForm = Form.form(Partida.class);
	static Form<Register> registerForm = Form.form(Register.class);
	static Form<Pregunta> preguntaForm = Form.form(Pregunta.class);

	public static Result index() {
		if (session().containsKey("conectado")) {
			try {
				return ok(partidas.render(Partida.findPartidaUser(new User(
						session().get("conectado"), "", false))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Form<User> filledForm = userForm.bindFromRequest();
			return ok(login.render(filledForm));
		}
		return null;
	}

	/**
	 * Cambia de idioma a español
	 * 
	 * @return
	 */
	public static Result es(String returnUrl) {
		Controller.changeLang("es");
		return redirect(returnUrl == null ? "/" : returnUrl);
	}
	
	/**
	 * Cambia de idioma a ingles
	 * 
	 * @return
	 */
	public static Result en(String returnUrl) {
		Controller.changeLang("en");
		return redirect(returnUrl == null ? "/" : returnUrl);
	}
	
	/**
	 * Salir de sesión
	 * 
	 * @return
	 */
	public static Result logout() {
		session().clear();
		flash("danger", Messages.get("application.logout"));
		return redirect("/");
	}

	/**
	 * Comprobar si el usuario está conectado
	 * 
	 * @return
	 */
	public static Result admin() {
		String user = session("connected");
		if (user != null) {
			return ok(user);
		} else {
			return unauthorized("Oops, you are not connected");
		}
	}

	/**
	 * Busca la partida del usuario
	 * 
	 * @return
	 */
	public static Result login() {
		Form<User> filledForm = userForm.bindFromRequest();
		User u = new User();
		u.login = filledForm.field("login").value();
		u.password = filledForm.field("password").value();

		try {
			if (User.login(u.login, u.password) != null) {
				session().put("conectado", u.login);
				if (u.login.equals("admin"))
					session().put("admin", "true");
				else
					session().put("admin", "false");
				flash("success", Messages.get("application.login.correcto"));
				return ok(partidas.render(Partida.findPartidaUser(u)));
			} else {
				flash("danger", Messages.get("application.login.correcto"));
				return redirect("/");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Mustra las categorias con su pregunta más fácil y más difícil
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Result showCategoriesQuestions() throws Exception {
		return ok(categorias.render(Category.all(), Category.easyQuestions(),
				Category.hardQuestions()));

		// return TODO;
	}

	/**
	 * Registro de usuario y comprobación campos
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Result register() {
		Form<Register> requestData = registerForm.bindFromRequest();
		try {
			User u = new User();
			if (requestData.field("password").value()
					.equals(requestData.field("password2").value())) {
				u.login = requestData.field("login").value();
				u.password = requestData.field("password").value();
				if (User.findOne(u.login) == null) {
					User.create(u);
					flash("success", "Usuario registrado");
				} else
					flash("danger", "ya hay un usuario con ese login");
				return redirect("/registro");
			} else {
				flash("danger", "Las passwords no coinciden");
				return redirect(routes.Application.registerUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Registar usuario
	 * 
	 * @return
	 */
	public static Result registerUser() {
		return ok(registro.render(registerForm));
	}

	/**
	 * Mostrar usuarios
	 * 
	 * @return
	 */
	public static Result showUsers() {
		try {
			return ok(usuarios.render(model.User.all(), userForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Mostrar usuario
	 * 
	 * @param id
	 * @return
	 */
	public static Result showUser(String id) {
		try {
			return ok(Json.toJson(User.findOne(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TODO;
	}

	/**
	 * Actualizar usuario
	 * 
	 * @param id
	 * @return
	 */
	public static Result updateUser(String id) {
		User user = userForm.bindFromRequest().get();
		// User.create(user);
		return ok("Usuario modificado");
	}

	/**
	 * Borrar usuario
	 * 
	 * @param id
	 * @return
	 */
	public static Result deleteUser(String id) {
		// User.delete(id);
		return ok("Usuario eliminado");
	}

	/**
	 * Nueva partida
	 * 
	 * @return
	 */
	public static Result newPartida() {
		try {
			Partida p = new Partida();
			User u = new User(session().get("conectado"), "",
					Boolean.valueOf(session().get("admin")));
			p.id = (long) Partida.all().size();
			p.usuarios.add(u);
			p.activeUser = u;
			p.inicializarQuesitos();
			Partida.create(p);
			return ok(inviteuser.render(p, model.User.all()));
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * Invita a usuarios
	 * 
	 * @return
	 */
	public static Result invite(Long partidaId, String login) throws Exception {
		
			User u = User.findOne(login);
			Partida p= Partida.findOne(partidaId);
			p.usuarios.add(u);
			p.inicializarQuesitos();
			Partida.updatePartida(p);
			
			List<User> todos = User.all();
			
			for(User us : p.usuarios)
				if(todos.contains(us))
					todos.remove(us);
			return ok(inviteuser.render(p, todos));
		//return redirect (routes.Application.showPartida(partidaId));
	}

	/**
	 * Elimina partida
	 * 
	 * @param id
	 * @return
	 */
	public static Result deletePartida(Long id) {
		try {
			Partida.delete(id);
			return redirect(routes.Application.login());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Salida de la partida
	 * 
	 * @param id
	 * @return
	 */
	public static Result exitPartida(Long id) {
		try {
			Partida.salirPartida(id, session().get("conectado"));
			return redirect(routes.Application.login());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Muestra las partidas del usuario
	 * 
	 * @param user
	 * @return
	 */
	public static Result showPartidasUser(User user) {
		try {
			return ok(partidas.render(Partida.findPartidaUser(user)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TODO;
	}

	/**
	 * Muestra la partida
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static Result showPartida(Long id) throws Exception {
		return ok(tablero.render(Partida.findOne(id)));
	}

	/**
	 * Muestra la pregunta
	 * 
	 * @param id
	 * @param idCategoria
	 * @param quesito
	 * @return
	 * @throws Exception
	 */
	public static Result getQuestion(Long id, String idCategoria,
			Boolean quesito, int posicion) throws Exception {
		Partida partidaActiva;
		Question q;
		partidaActiva = Partida.findOne(id);
		partidaActiva.activeUser.posicion=posicion;
		User.updateUser(partidaActiva.activeUser);
		q = partidaActiva.devolverPregunta(idCategoria);
		Partida.updatePartida(partidaActiva);
		return ok(preguntaventana.render(id, q, quesito));
	}

	/**
	 * Respuesta a la pregunta
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Result contesta() throws Exception {
		Form<Pregunta> filledForm = preguntaForm.bindFromRequest();
		Partida p = Partida.findOne(Long.parseLong(filledForm.field("id").value())); // coges la partida
		String cat = filledForm.field("category").value();
		System.out.println("POS CONTESTA"+p.activeUser.posicion);
		List<Question> c = Category.findAllQuestions(cat.trim()); // coges la categoria
		for (Question q : c) {
			if (q.query.equals(filledForm.field("query").value())) {// buscas la pregunta
				if (q.correctAnswer.equals(filledForm.field("contestada").value())) { // si contestaste bien
					flash("success", "Respuesta correcta");
					p.acierta(q, Boolean.parseBoolean(filledForm.field(
							"quesito").value())); // donde sea la estrella en el circulo
					if(p.finished==true)
						flash("success", "Partida terminada");
				} else
					flash("danger", "Respuesta incorrecta");
				p.falla(q);
			} else {
				// System.out.println("No hay correspondencia en la pregunta.");
			}
		}
		try {
			return redirect(routes.Application.showPartida(Long.parseLong(filledForm.field("id").value())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TODO;
	}

	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(Routes.javascriptRouter("myJsRoutes", routes.javascript.Application.getQuestion()));
	}

	public static class Register {
		public String login;
		public String password;
		public String password2;
		public boolean admin;

		/**
		 * Comprobar campos
		 * 
		 * @return
		 */
		public String validate() {
			if (!password.equals(password2)) {
				return "Las contraseñas deben coincidir";
			} else
				try {
					if (User.findOne(login) != null) {
						return "Usuario ya existe";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			return null;
		}
	}

	public static class Pregunta {
		public String category;
		public String query;
		public String contestada;
		public Long id;
		public Boolean quesito;
	}

}
