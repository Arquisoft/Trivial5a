package es.uniovi.asw.persistence;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class Driver {

	private static MongoClient client;
	private static DBCollection table;

	public void save(String[] JSONarray) {
		DB db;
		try {
			client = new MongoClient(new ServerAddress("localhost", 27017));

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (client != null) {
			// Si no existe la base de datos la crea
			db = client.getDB("Trivial5a");

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
				// System.out.println("Bien insertado");
			}
			System.out.println();

			// Listas las bases de datos
			System.out.println("Lista de todas las bases de datos: ");
			List<String> basesDeDatos = client.getDatabaseNames();
			for (String nombreBaseDatos : basesDeDatos) {
				System.out.println(" - " + nombreBaseDatos);
			}
			System.out.println();

			// Listar las tablas de la base de datos actual
			System.out.println("Lista de tablas de la base de datos: ");
			Set<String> tables = db.getCollectionNames();
			for (String coleccion : tables) {
				System.out.println(" - " + coleccion);
			}
			System.out.println();

			/*
			// Borrar base de datos
			if (db == client.getDB("Trivial5a")) {//Base de datos que queremos borrar
				db.dropDatabase();
				System.out.println("Base de datos " + db + " borrada");
			}
			System.out.println();
			// Listas las bases de datos
			System.out.println("Lista de todas las bases de datos tras el borrado de "
							+ db);
			List<String> basesDeDatosBorrada = client.getDatabaseNames();
			for (String nombreBaseDatos : basesDeDatosBorrada) {
				System.out.println(" - " + nombreBaseDatos);
			}
			System.out.println();*/

		} else {
			System.out.println("Error: Conexión no establecida");
		}
	}
}