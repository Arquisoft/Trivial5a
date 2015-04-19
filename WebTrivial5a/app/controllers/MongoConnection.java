package controllers;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Category;
import model.Partida;
import model.Question;
import model.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class MongoConnection {

	private static MongoClient client;

	private static DBCollection table;

	public static DB db;

	/**
	 * Conecta con la base de datos MongoDB presente en el equipo en el puerto
	 * po defecto 27017
	 */
	public static void conectDB() {
		try {
			String uri="mongodb://admin:admin@ds061721.mongolab.com:61721/trivial5aweb2";
			MongoClientURI muri = new MongoClientURI(uri);
			client = new MongoClient(muri);
			db = client.getDB(muri.getDatabase());
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
	public static void saveQuestionJSON(String[] JSONarray) throws Exception {
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
			for (int i = 0; i < categorias.length; i++)
				// Insertar tablas
				table.insert(categorias[i]);
			System.out.println();
			client.close();
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	

	/**
	 * Añade una pregunta a la BBDD
	 * 
	 * @param question
	 * @throws Exception
	 */
	public static void addQuestion(Question q) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("preguntas");
			DBObject[] pregunta = new BasicDBObject[1];
			pregunta[0] = (DBObject) JSON.parse(q.toJSON());
			table.insert(pregunta[0]);
			client.close();
		} else {
			throw new Exception("Error: Conexión no establecida");
		}
	}
	
	/**
	 * Añade un usuario a la BBDD
	 * 
	 * @param question
	 * @throws Exception
	 */
	public static void addUser(User u) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBObject[] pregunta = new BasicDBObject[1];
			pregunta[0] = (DBObject) JSON.parse(u.toJSON());
			table.insert(pregunta[0]);
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
	public static User findUser(String login, String password) throws Exception {
		conectDB();

		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBObject user = new BasicDBObject("login", login).append(
					"password", password);
			Gson g = new Gson();
			DBObject obj = table.findOne(user);
			if (obj != null) {
				User usuario = g.fromJson(obj.toString(), User.class);
				return usuario;
			}
			client.close();
			return null;
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	/**
	 * Busca un usuario por su nombre de usuario y su contraseña
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static User findUser(String login) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBObject user = new BasicDBObject("login", login);
			Gson g = new Gson();
			DBObject obj = table.findOne(user);
			if (obj != null) {
				User usuario = g.fromJson(obj.toString(), User.class);
				return usuario;
			}
			client.close();
			return null;
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	/**
	 * Actualizar las estadíticas o datos de un usuario
	 * 
	 * @param user
	 */
	public static void updateUser(User user) {
		conectDB();
		// Crea una tabla si no existe y agrega datos
		table = db.getCollection("usuarios");
		DBObject[] userModificado = new BasicDBObject[1];
		userModificado[0] = (DBObject) JSON.parse(user.toJSON());
		DBObject userActualizar = new BasicDBObject("login", user.login);
		table.findAndModify(userActualizar, userModificado[0]);
		client.close();
	}
	
	/**
	 * Actualizar las estadíticas o datos de un usuario
	 * 
	 * @param user
	 * @throws Exception 
	 */
	public  static void removeUser(User user) throws Exception {
		conectDB();
		// Crea una tabla si no existe y agrega datos
		findUser(user.login);
		table = db.getCollection("usuarios");
		DBObject userAborrar = new BasicDBObject();
		userAborrar = (DBObject) JSON.parse(user.toJSON());
		table.findAndRemove(userAborrar);
		client.close();
	}

	/**
	 * Actualizar las estadíticas o datos de un pregunta
	 * 
	 * @param user
	 */
	public static void updateQuestion(Question question, Category category) {
		conectDB();
		int index = category.questions.indexOf(question);
		category.questions.set(index, question);
		// Crea una tabla si no existe y agrega datos
		table = db.getCollection("categorias");
		DBObject[] categoria = new BasicDBObject[1];
		categoria[0] = (DBObject) JSON.parse(category.toJSON());
		DBObject categoriaActualizar = new BasicDBObject("name",
				category.name);
		table.findAndModify(categoriaActualizar, categoria[0]);
		client.close();
	}

	/**
	 * Devuelve todos los usuarios registrados en la aplicacion
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<User> findAllUser() throws Exception {
		conectDB();
		if (client != null) {
			List<User> usuarios = new ArrayList<User>();
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("usuarios");
			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				User user = new Gson().fromJson(cursor.next().toString(),
						User.class);
				usuarios.add(user);
			}
			client.close();
			for (User user : usuarios)
				System.out.println(user);
			return usuarios;
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	/**
	 * Devuelve todos las preguntas.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Category> findAllQuestion() throws Exception {
		conectDB();
		if (client != null) {
			List<Category> preguntas = new ArrayList<Category>();
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("categorias");
			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				Category category = new Gson().fromJson(cursor.next()
						.toString(), Category.class);
				preguntas.add(category);
			}
			client.close();
			for (Category question : preguntas)
				System.out.println(question);
			return preguntas;
		} else
			throw new Exception("Error al cargar las preguntas");
	}

	/**
	 * Borra la tabla cuyo nombre es pasado como parametro
	 * 
	 * @param DBName
	 * @throws Exception
	 */
	public static void removeTable(String table) throws Exception {
		conectDB();
		// Borrar base de datos
		if (client != null) {
			db.getCollection(table).drop();
			System.out.println();
			// Listas las bases de datos
			System.out
					.println("Lista de todas las tablas de la BBDD tras el borrado de "
							+ db);
			List<String> basesDeDatosBorrada = client.getDatabaseNames();
			for (String nombreBaseDatos : basesDeDatosBorrada)
				System.out.println(" - " + nombreBaseDatos);
			System.out.println();
			client.close();
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	/**
	 * Clase que imprime el contentido de la BBDD para casos de depurabilidad
	 * 
	 * @throws Exception
	 */
	public  static void imprimirDB() throws Exception {
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
			for (String coleccion : tables)
				System.out.println(" - " + coleccion);
			System.out.println();
			client.close();
		} else
			throw new Exception("Error: Conexión no establecida");
	}
/**
 * Devuelve una categoria pasada por parametro
 * @param name
 * @return
 * @throws Exception
 */
	public static Category findCategory(String name) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("categorias");
			DBObject user = new BasicDBObject("name", name);
			Gson g = new Gson();
			DBObject obj = table.findOne(user);
			if (obj != null) {
				Category c = g.fromJson(obj.toString(), Category.class);
				return c;
			}
			client.close();
			return null;
		} else
			throw new Exception("Error: Conexión no establecida");
	}
	
	/**
	 * Devuelve todas las partidas 
	 * @return
	 * @throws Exception
	 */
	public static List<Partida> findAllPartidas() throws Exception {
		conectDB();

		if (client != null) {
			List<Partida> partidas = new ArrayList<Partida>();

			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("partidas");
			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				Partida partida = new Gson().fromJson(cursor.next().toString(),
						Partida.class);
				partidas.add(partida);
			}
			client.close();
			return partidas;
		} else
			throw new Exception("Error: Conexión no establecida");
	}
	
	/**
	 * Devuelve las partidas en las que el login que se pasa por parametro este incluido en la partida
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public static List<Partida> findPartidasJugador(User user) throws Exception {
		conectDB();

		if (client != null) {
			List<Partida> partidas = new ArrayList<Partida>();

			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("categorias");
			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				Partida partida = new Gson().fromJson(cursor.next().toString(),
						Partida.class);
				if(partida.containsUser(user))
				partidas.add(partida);
			}
			
			client.close();
			return partidas;
		} else
			throw new Exception("Error: Conexión no establecida");
	}
	
	/**
	 * Actualizar las estadíticas o datos de un usuario
	 * 
	 * @param user
	 * @throws Exception 
	 */
	public  static void removePartida(Long id) throws Exception {
		conectDB();
		// Crea una tabla si no existe y agrega datos
		Partida aborrar=findPartida(id);
		table = db.getCollection("partidas");
		DBObject partidaAborrar = new BasicDBObject();
		partidaAborrar = (DBObject) JSON.parse(aborrar.toJSON());
		table.findAndRemove(partidaAborrar);
		client.close();
	}
	
	public static Partida findPartida(Long id) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("partidas");
			DBObject user = new BasicDBObject("id", id);
			Gson g = new Gson();
			DBObject obj = table.findOne(user);
			if (obj != null) {
				Partida partida = g.fromJson(obj.toString(), Partida.class);
				return partida;
			}
			client.close();
			return null;
		} else
			throw new Exception("Error: Conexión no establecida");
	}

	public static void addPartida(Partida partida) throws Exception {
		conectDB();
		if (client != null) {
			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("partidas");
			DBObject[] partidas = new BasicDBObject[1];
			partidas[0] = (DBObject) JSON.parse(partida.toJSON());
			table.insert(partidas[0]);
			client.close();
		} else {
			throw new Exception("Error: Conexión no establecida");
		}		
	}

	public static List<Partida> findPartidasActiveOrNot(boolean finished) throws Exception {
		conectDB();

		if (client != null) {
			List<Partida> partidas = new ArrayList<Partida>();

			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("categorias");
			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				Partida partida = new Gson().fromJson(cursor.next().toString(),
						Partida.class);
				if(partida.finished==finished)
				partidas.add(partida);
			}
			
			client.close();
			return partidas;
		} else
			throw new Exception("Error: Conexión no establecida");		
	}
}