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
	}
	
	public static Result register() {
		User user = userForm.bindFromRequest().get();
		//user.save();
		return ok("Usuario añadido");
	}
	
	public static Result showUsers() {
		return ok(Json.toJson(User.all()));
	}

	public static Result showUser(String id) {
		return ok(Json.toJson(User.findOne(id)));
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
		return ok(partidas.render(Partida.all()));
	}
	
	public static Result showPartida(String id) {
		return null;
	}
	
	public static Result getQuestion(String id, String idCategoria) {
		Partida partidaActiva = Partida.findOne(id);
		Question q = partidaActiva.devolverPregunta(idCategoria);
		return ok(Json.toJson(q));
	}
	
	public static Result getRespuesta(String id, String idQuestion) {
		
		return null;
	}
	
	public static Result setRespuesta(String id, String idQuestion, String idAnswer) {
		return null;
	}

}
