package controllers;

import java.util.List;

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
	static Form<Pregunta> preguntaForm = Form.form(Pregunta.class);

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
				if(u.login.equals("admin"))
				session().put("admin", "true");
				else
				session().put("admin", "false");
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
	
	public static Result showCategoriesQuestions() {

		try {
			return ok(categorias.render(Category.all(), Category.easyQuestions(), Category.hardQuestions()));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				if(User.findOne(u.login) == null)
				{
				User.create(u);
				flash("success", "Usuario registrado");
				}
				else
					flash("danger", "ya hay un usuario con ese login");
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
	 * Sale de la partida 
	 * @param id
	 * @return
	 */
	public static Result exitPartida(Long id) {
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

	public static Result showPartida(Long id) throws Exception{
	
			return ok(tablero.render(Partida.findOne(id)));
		
	}

	public static Result getQuestion(Long id, String idCategoria, Boolean quesito) throws Exception{
		Partida partidaActiva;
		Question q;
	
			partidaActiva = Partida.findOne(id);
			 q = partidaActiva.devolverPregunta(idCategoria);
			return ok(preguntaventana.render(id, q, quesito));
  

	}
	
	public static Result contesta() throws Exception
	{	
		Form<Pregunta> filledForm = preguntaForm.bindFromRequest();
		
		Partida p= Partida.findOne(Long.parseLong(filledForm.field("id").value()));	//coges la partida
		String cat = filledForm.field("category").value();
		List<Question> c = Category.findAllQuestions(cat.trim()); //coges la categoria
		
		for (Question q : c) { 
			
			if(q.query.equals(filledForm.field("query").value())) //buscas la pregunta
			{
				System.out.println("Pregunta encontrada.");				
				if(q.correctAnswer.equals(filledForm.field("contestada").value())){ //si contestaste bien
					flash("success", "Respuesta correcta");
					
					p.acierta(q, Boolean.parseBoolean(filledForm.field("quesito").value())); //donde sea la estrella en el circulo
				}else
					flash("danger", "Respuesta incorrecta");
					p.falla(q);
			}
			else {
				//System.out.println("No hay correspondencia en la pregunta.");
			}
		}
		try {
			return redirect(routes.Application.showPartida(Long.parseLong(filledForm.field("id").value())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TODO;
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
	
	public static class Pregunta {
		public String category;
		public String query; 
		public String contestada;
		public Long id;
		public Boolean quesito;
	}

}
