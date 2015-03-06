package es.uniovi.asw.bussines;

import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.Driver;

public class UserManager {
	
	Driver d;
	
	
	
	/**
	 * Metodo de negocio que llama a la persistencia y hace el login en la aplicacion
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(User user) throws Exception
	{
		d= new Driver();
		User userBBDD=d.findUser(user.getName(), user.getLastName(),
				user.getPassword());
		if(userBBDD==null)
			throw new Exception("Usuario no encontrado. Introduzca datos");
		
		return userBBDD;
	}
	

	/**
	 * Guarda las estadisticas tras cada tirada de cada jugador
	 * Sirve para guardar el numero de preguntas acertadas y falladas
	 * 
	 * Tambien sirve para actualizar informacion de contraseña
	 * @param user
	 */
	public void updateUser(User user)
	{
		d= new Driver();
		d.updateUser(user);
	}
	
}
