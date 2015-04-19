package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import model.*;

public class Application extends Controller {


	static Form<User> userForm = Form.form(User.class);
	
	public static Result index() {
		return ok(index.render("Your new application is ready."));
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
		 //Form<Login> loginForm = form(Login.class).bindFromRequest();
		  return ok();
	}
	public static Result showQuestions() {
			
		  return ok(categorias.render(Category.all()));
		//return null;
	}
	
	/**
	 * FUNCIONA. FALTARIA  PONER MENSAJE SI USUARIOS REPETIDO Y ESO
	 * @return
	 * @throws Exception
	 */
	public static Result register() throws Exception {
		 Form<User> filledForm = userForm.bindFromRequest();
		 User.create(filledForm.get());
		return ok(usuarios.render(userForm));
	}
	
	public static Result showUsers() {
		//return ok(Json.toJson(User.all()));
		return ok(usuarios.render(userForm));

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
		//User.create(user);
		return ok("Usuario modificado");
	}

	public static Result deleteUser(String id) {
		//User.delete(id);
		return ok("Usuario eliminado");
	}

	public static Result newPartida() {
		return null;
	}
	
	public static Result showPartidas() {
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode actualObj = mapper.readTree(Json.toJson(Partida.all()));
//		return ok(partidas.render(actualObj));
		try {
			return ok(partidas.render(Partida.all()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TODO;
	}
	
	public static Result showPartida(String id) {
		return null;
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
	
	public static Result setRespuesta(String id, String idQuestion, String idAnswer) {
		return null;
	}

}
