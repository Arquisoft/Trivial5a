package es.uniovi.asw.persistence;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import es.uniovi.asw.model.User;

public class Driver {

	private static MongoClient client;
	private static DBCollection table;
	DB db;

	/**
	 * Conecta con la base de datos MongoDB presente en el equipo en el puerto po defecto 27017
	 */
	public void conectDB() {
		try {
			client = new MongoClient(new ServerAddress("localhost", 27017));
			db = client.getDB("Trivial5a");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Guarda en la BBDD el mapa JSON de categorias con las preguntas
	 * 
	 * @param JSONarray
	 * @throws Exception
	 */
	public void saveQuestionJSON(String[] JSONarray) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("categorias");

			DBObject[] categorias = new BasicDBObject[JSONarray.length];

			System.out.println("Tabla " + table);
			for (int i = 0; i < JSONarray.length; i++) {
				categorias[i] = (DBObject) JSON.parse(JSONarray[i]);
				System.out.println(categorias[i]);
			}

			for (int i = 0; i < categorias.length; i++) { // Insertar tablas
				table.insert(categorias[i]);
			}
			System.out.println();

			client.close();
		} else 
			throw new Exception("Error: Conexión no establecida");

	}

	/**
	 * Añade un usuario a la BBDD
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBObject[] usuario = new BasicDBObject[1];
			usuario[0]=(DBObject) JSON.parse(user.toJSON());
			table.insert(usuario[0]);
			
			client.close();
		} else {
			throw new Exception("Error: Conexión no establecida");
		}
	}

	/**
	 * Busca un usuario por su nombre de usuario y su contraseña
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User findUser(String name, String lastName, String password)
			throws Exception {
		conectDB();

		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBObject user = new BasicDBObject("name", name).append("lastName",
					lastName).append("password", password);
			
			Gson g =new Gson();
			DBObject obj =table.findOne(user);
			if(obj!=null) {
				User usuario=g.fromJson(obj.toString(), User.class);
				return usuario;
			}
			client.close();
			return null;
		} else
			throw new Exception("Error: Conexión no establecida");
	}
	
	/**
	 * Actualizar las estadíticas o datos de un usuario
	 * @param user
	 */
	public void updateUser(User user) {
		conectDB();
		
		// Crea una tabla si no existe y agrega datos
		table = db.getCollection("usuarios");
		DBObject [] userModificado = new BasicDBObject[1];
		userModificado[0]=(DBObject) JSON.parse(user.toJSON());
		DBObject userActualizar = new BasicDBObject("name", user.getName())
		.append("lastName",user.getLastName());
		table.findAndModify(userActualizar, userModificado[0]);
		client.close();
	}

	/**
	 * Devuelve todos los usuarios registrados en la aplicacion
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> findAllUser() throws Exception {
		conectDB();
		if (client != null) {
			List<User> usuarios = new ArrayList<User>();

			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				User user = new Gson().fromJson(cursor.next().toString(), User.class);
				usuarios.add(user);
			}
			
			client.close();
			return usuarios;
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	/**
	 * Borra la tabla cuyo nombre es pasado como parametro
	 * 
	 * @param DBName
	 * @throws Exception 
	 */
	public void removeTable(String table) throws Exception {
		conectDB();
		// Borrar base de datos
		if(client!=null) {
			db.getCollection(table).drop();
			System.out.println();

			// Listas las bases de datos
			System.out.println("Lista de todas las tablas de la BBDD tras el borrado de " + db);
			List<String> basesDeDatosBorrada = client.getDatabaseNames();
			for (String nombreBaseDatos : basesDeDatosBorrada) {
				System.out.println(" - " + nombreBaseDatos);
			}
			System.out.println();
			
			client.close();
		} 
		else 
			throw new Exception("Error: Conexión no establecida");
	}
	
	/**
	 * Clase que imprime el contentido de la BBDD para casos de depurabilidad
	 * @throws Exception 
	 */
	public void imprimirDB() throws Exception {
		conectDB();
		// Listas las bases de datos
		if (client != null) {
			System.out.println("Lista de todas las bases de datos: ");
			List<String> basesDeDatos = client.getDatabaseNames();
			for (String nombreBaseDatos : basesDeDatos) {
				System.out.println(" - " + nombreBaseDatos);
			}
			System.out.println();
	
			// Listar las tablas de la base de datos actual
			System.out.println("Lista de tablas de la base de datos: ");
			Set<String> tables = db.getCollectionNames();
			for (String coleccion :tables) {
				System.out.println(" - " + coleccion);
			}
			System.out.println();
			
			client.close();
		}
		else
			throw new Exception("Error: Conexión no establecida");
	}
}