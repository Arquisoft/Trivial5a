package model;
import java.util.List;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class User {
	
	private static JacksonDBCollection<User, String> coll = MongoDB.getCollection("usuarios", User.class, String.class);
	
	@Id
	@ObjectId
	public String login;
	public String password;
	
	
	public boolean admin;
	
	
	public int numberCorrectAnswer;
	
	
	public int numberWrongAnswer;
	
	public int posicion;
	 
	public boolean isAdmin()
	{
		return admin;
	}

	public User(String login, String password, boolean admin) {
		this.login = login;
		this.password = password;
		this.admin=admin;
	}
	
	
	/**
	 * Metodo que encuentra todos los usuarios y los devuelve en una lista
	 * @return
	 */
	  public static List<User> all() {
		    return User.coll.find().toArray();
		  }

	  /**
	   * Metodo que es llamado por el anterior y guarda en la BBDD
	   * @param user
	   */
		  public static void create(User user) throws Exception {
			  if(User.findOne(user.login)==null)
			  User.coll.save(user);
			  else
				  throw new Exception("Usuario repetido");
		  }

		  /**
		   * Metodo que crea un usuarios, si es admin o no
		   * @param login
		   * @param password
		   */
		  public static void create(String login, String password,boolean admin) throws Exception{
		      create(new User(login,password,admin));
		  }

		  /**
		   * Borra un usuario dado un login
		   * @param login
		   */
		  public static void delete(String login) {
			  User user = User.coll.findOneById(login);
		    if (user != null)
		    	User.coll.remove(user);
		  }

		  /**
		   * Elimina todos los usuarios
		   */
		  public static void removeAll(){
			  User.coll.drop();
		  }
		  
		  /**Busca un usuario
		   * 
		   * @param login
		   * @return
		   */
		  public static User findOne(String login)
		  {
			  return User.coll.findOneById(login);
		  }
	
	
}