package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import com.google.gson.Gson;
import controllers.MongoConnection;

public class User {

	@Id
	@ObjectId
	public String id;

	public String login;
	
	public String password;

	public boolean admin;

	public int numberCorrectAnswer;

	public int numberWrongAnswer;

	public int posicion;

	public boolean isAdmin() {
		return admin;
	}

	public User(String login, String password, boolean admin) {
		this.login = login;
		this.password = password;
		this.admin = admin;
	}

	public User() {

	}

	/**
	 * Añade un usuario a la BBDD
	 * 
	 * @param user
	 * @throws Exception
	 */
	public static void create(User user) throws Exception {
		user.password = Hash(user.password);
		MongoConnection.addUser(user);
	}

	/**
	 * Actualiza un usuario
	 * 
	 * @param user
	 * @throws Exception
	 */
	public static void updateUser(User user) throws Exception {
		MongoConnection.updateUser(user);
	}

	/**
	 * Busca un usuario pasando el login
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public static User findOne(String login) throws Exception {
		return MongoConnection.findUser(login);
	}

	/**
	 * Buscar un usuario pasando el login y password
	 * 
	 * @param login
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public static User login(String login, String pass) throws Exception {
		return MongoConnection.findUser(login, Hash(pass));
	}

	/**
	 * Busca todos los usuarios
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<User> all() throws Exception {
		return MongoConnection.findAllUser();
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

	/**
	 * Metodo de seguridad. Coge la contraseña y genera un hash unico para esa
	 * contraseña. Lo convierte a bytes y esos bytes loos vuelve a pasar a texto
	 * para introducirlo en la BBDD
	 * 
	 * @param pass
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String Hash(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytesOfMessage = pass.getBytes();
		md.update(bytesOfMessage);
		byte[] converter = md.digest();
		String hash = "";
		for (int i = 0; i < converter.length; i++) {
			hash += Integer.toString(converter[i]);
		}
		return hash;
	}

	/**
	 * Devuelve la representacion en formato JSON de la pregunta. Cabe añadir
	 * que es independiente del formato de entrada
	 * 
	 * @return String JSON
	 */
	public String toJSON() {
		Gson g = new Gson();
		return g.toJson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}