package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.*;
import play.api.mvc.Session;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import model.*;

public class Application extends Controller {

	static Form<User> userForm = Form.form(User.class);
	static Form<Partida> partidaForm = Form.form(Partida.class);
	static Form<Register> registerForm = Form.form(Register.class);


	public static Result index() {
		if (session().containsKey("conectado")) {
			try {
				return ok(partidas.render(Partida.findPartidaUser(new User(
						session().get("conectado"), "", false))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Form<User> filledForm = userForm.bindFromRequest();
			return ok(login.render(filledForm));
		}
		return null;
	}

	public static Result logout() {
		session().clear();
		flash("danger", "Salida de sesión.");
		return redirect("/");
	}

	public static Result admin() {
		String user = session("connected");
		if (user != null) {
			return ok(user);
		} else {
			return unauthorized("Oops, you are not connected");
		}
	}

	public static Result login() {
		Form<User> filledForm = userForm.bindFromRequest();
		User u = new User();
		u.login = filledForm.field("login").value();
		u.password = filledForm.field("password").value();

		try {

			if (User.login(u.login, u.password) != null) {
				session().put("conectado", u.login);
				session().put("admin", String.valueOf(u.admin));
				flash("success", "Login correcto. ¡Bienvenido!");
				return ok(partidas.render(Partida.findPartidaUser(u)));
			}

			else {
				flash("danger", "Login incorrecto.");
				return redirect("/");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Result showQuestions() {

		try {
			return ok(categorias.render(Category.all()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return null;
		return TODO;
	}

	/**
	 * FUNCIONA. FALTARIA PONER MENSAJE SI USUARIOS REPETIDO Y ESO
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Result register() {
		//Form<Register> requestData = Form.form(Register.class)
			//	.bindFromRequest();
		
		Form<Register> requestData = registerForm.bindFromRequest();
		try {
			//if (requestData.hasErrors()) {
			//	return badRequest(registro.render(requestData));
			//} else {
			User u = new User();
			if(requestData.field("password").value().equals(requestData.field("password2").value()) ){
				u.login = requestData.field("login").value();
				u.password = requestData.field("password").value();
				//u.admin = Boolean.valueOf(requestData.get().admin);
				User.create(u);
				flash("success", "Usuario registrado");
				return redirect("/registro");
			}else{
				flash("danger", "Las passwords no coinciden");
				return redirect(routes.Application.registerUser());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Result registerUser() {
		return ok(registro.render(registerForm));
	}

	public static Result showUsers() {
		try {
			return ok(usuarios.render(model.User.all(), userForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Result showUser(String id) {
		try {
			return ok(Json.toJson(User.findOne(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TODO;
	}

	public static Result updateUser(String id) {
		User user = userForm.bindFromRequest().get();
		// User.create(user);
		return ok("Usuario modificado");
	}

	public static Result deleteUser(String id) {
		// User.delete(id);
		return ok("Usuario eliminado");
	}

	public static Result newPartida() {
		try {
			Partida p = new Partida();
			User u = new User(session().get("conectado"), "",
					Boolean.valueOf(session().get("admin")));
			p.id = (long) Partida.all().size();
			p.usuarios.add(u);
			p.activeUser=u;
			tablero.render(Partida.create(p));
			return redirect(routes.Application.showPartida(p.id));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Elimina partidas
	 * @param id
	 * @return
	 */
	public static Result  deletePartida(Long id) {
		try {
			
			Partida.delete(id);
			return redirect(routes.Application.login());
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sale de la partida 
	 * @param id
	 * @return
	 */
	public static Result  exitPartida(Long id) {
		try {
			
			Partida.salirPartida(id,session().get("conectado"));
			return redirect(routes.Application.login());
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static Result showPartidasUser(User user) {
		try {
			return ok(partidas.render(Partida.findPartidaUser(user)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TODO;
	}

	public static Result showPartida(Long id) {
		try {
			return ok(tablero.render(Partida.findOne(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TODO;
	}

	public static Result getQuestion(Long id, String idCategoria) {
		Partida partidaActiva;
		try {
			partidaActiva = Partida.findOne(id);
			Question q = partidaActiva.devolverPregunta(idCategoria);
			return ok(Json.toJson(q));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TODO;

	}

	public static Result getRespuesta(String id, String idQuestion) {

		return null;
	}

	public static Result setRespuesta(String id, String idQuestion,
			String idAnswer) {
		return null;
	}

	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(Routes.javascriptRouter("myJsRoutes",
				routes.javascript.Application.getQuestion()));
	}

	public static class Register {
		public String login;
		public String password;
		public String password2;
		public boolean admin;

		public String validate() {
			if (!password.equals(password2)) {
				return "Las contraseñas deben coincidir";
			} else
				try {
					if (User.findOne(login) != null) {
						return "Usuario ya existe";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}
	}

}
