package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
		  private static void create(User user) throws Exception {
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
		  
		  /**
			 * Devuelve las estadísticas del usuario mostrando el número de preguntas
			 * acertadas y falladas
			 * 
			 * @return vecesFallosAciertos
			 */
			public Map<String, Object> showStadistics() {
				Map<String, Object> vecesFallosAciertos = new HashMap<String, Object>();
				vecesFallosAciertos.put("usuario", login);
				vecesFallosAciertos.put("aciertos", numberCorrectAnswer);
				vecesFallosAciertos.put("fallos", numberWrongAnswer);
				return vecesFallosAciertos;
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
		  
		  /**Busca un usuario
		   * 
		   * @param login
		   * @return
		   */
		  public static User login(String login,String pass)
		  {
			  String converted = null;
			try {
				converted = Hash(pass);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  DBObject user = new BasicDBObject("login", login).append("password", converted);
			  return User.coll.findOne(user);
		  }
		  
		  /**
		   * Metodo de seguridad. Coge la contraseña y genera un hash unico para esa contraseña. Lo convierte a bytes
		   * y esos bytes loos vuelve a pasar a texto para introducirlo en la BBDD
		   * @param pass
		   * @return
		 * @throws NoSuchAlgorithmException 
		   */
		  private static String Hash(String pass) throws NoSuchAlgorithmException
		  {
			  MessageDigest md = MessageDigest.getInstance("MD5");
			  byte[] bytesOfMessage =pass.getBytes();
			   md.update(bytesOfMessage);
			 byte[] converter = md.digest();
			 String hash = "";
	         for (int i = 0; i < converter.length; i++) {
			 	hash+=Integer.toString(converter[i]);
		  }
			return hash;
		  }
		  
		  
		  /**Busca un usuario y lo actualiza
		   * 
		   * @param login
		   * @return
		   */
		  public static void Update(User user)
		  {
			  User.coll.save(user);
		  }
	
}