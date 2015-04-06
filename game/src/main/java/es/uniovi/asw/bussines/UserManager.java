package es.uniovi.asw.bussines;

import java.util.List;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class UserManager {

	Driver d;

	public UserManager(Driver d) {
		super();
		this.d = d;
	}

	/**
	 * Método de negocio que llama a la persistencia y hace el login en la
	 * aplicación
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(User user) throws Exception {
		User userBBDD = d.findUser(user.getLogin(), user.getPassword());
		if (userBBDD == null)
			throw new Exception("Usuario no encontrado. Introduzca datos");
		return userBBDD;
	}

	/**
	 * Guarda las estadisticas tras cada tirada de cada jugador Sirve para
	 * guardar el numero de preguntas acertadas y falladas Tambien sirve para
	 * actualizar informacion de contraseña
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		d.updateUser(user);
	}

	/**
	 * Llama al addUser de la persistencia
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		d.addUser(user);
	}

	/**
	 * Devuelve true si ya hay un usuario con un login
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public boolean findUserByLogin(String login) throws Exception {
		List<User> usuarios = d.findAllUser();
		for (User u : usuarios)
			if (u.getLogin().equals(login))
				return true;
		return false;
	}
}
