package model;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

@Entity
public class User extends Model {
	@Id
	public Long id;

	@Required
	public String login;
	@Required
	public String password;
	
	public boolean admin;
	public int numberCorrectAnswer;
	public int numberWrongAnswer;
	
	public int posicion;
	
	public boolean isAdmin()
	{
		return admin;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public static User findById(Long id) {
		return finder.byId(id);
	}

	public static List<User> all() {
		return finder.all();
	}

	public static void create(User user) {
		user.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	public static Finder<Long, User> finder = new Finder(Long.class, Question.class);
}