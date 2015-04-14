package model;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.db.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

import java.util.*;

import javax.persistence.*;

import model.User;

@Entity
public class Partida extends Model {
	
	public List<User> usuarios;
	
	public List<Long> idUsers;
	
	@Id
	public Long id;
	
	public  List<Long> idAskedQuestions;
	public User activeUser;
	
	public Map<Long,Set<String>> quesitosPorJugador;
	
	
	public Partida()
	{
		if(findById(this.id) != null) //Ya estaba la partida en la BBDD
		{
			
		}
		else
		{
			
		}
			
	}
	
	/**
	 * Carga los usuarios en memoria
	 * @return
	 */
	public List<User> loadUsers()
	{
		return null;
	}
	
	
	
	public static Partida findById(Long id) {
		return finder.byId(id);
	}
 
	public static List<Partida> all() {
		return finder.all();
	}

	public static void create(Partida partida) {
		partida.save();
	}
 
	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	public static Finder<Long, Partida> finder = new Finder(Long.class, Partida.class);

}
