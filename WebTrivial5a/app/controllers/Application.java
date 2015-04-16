package controllers;

import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import model.*;
//import models.Task;

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
	
	public static Result register() {
		 return null;
	}
	
	public static Result newUser() {
		User user = userForm.bindFromRequest().get();
		user.save();
		return ok("Usuario añadido");
	}
	
	public static Result showUsers() {
		return ok(Json.toJson(User.all()));
	}

	public static Result showUser(Long id) {
		return ok(Json.toJson(User.findById(id)));
	}

	public static Result updateUser(Long id) {
		User user = userForm.bindFromRequest().get();
		User.create(user);
		return ok("Usuario modificado");
	}

	public static Result deleteUser(Long id) {
		User.delete(id);
		return ok("Usuario eliminado");
	}

	public static Result newPartida() {
		return null;
	}
	
	public static Result showPartidas() {
		//return ok(Json.toJson(Partida.all()));
		return null;
	}
	
	public static Result showPartida(Long id) {
		return null;
	}
	
	public static Result getQuestion(Long id, Long idCategoria) {
		return ok(Json.toJson(Question.findById(id)));
	}
	
	public static Result getRespuesta(Long id, Long idQuestion) {
		return null;
	}
	
	public static Result setRespuesta(Long id, Long idQuestion, Long idAnswer) {
		return null;
	}

}
