package conf;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import Parser.Parser;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
			db = client.getDB("TRIVIAL22");

			// Crea una tabla si no existe y agrega datos
			table = db.getCollection("categorias");
			
			DBObject[] categorias = new BasicDBObject[JSONarray.length];
			
			for (int i = 0; i < JSONarray.length; i++) { 
				categorias[i]= (DBObject) JSON.parse(JSONarray[i]);
				System.out.println(categorias[i]);

			}
		
				
	/*	categorias[0] = (DBObject) JSON
					.parse("{_id: '10000000L', firstName: 'Carlos',   lastNames: 'García Pérez',  experienceYears: 12, "
							+ "location: {city: 'mad', country: 'es'},  tags: ['java', 'spring', 'javascript', 'backbone']}");
			people[1] = (DBObject) JSON
					.parse("{_id: '20000000L', firstName: 'Isaac',    lastNames: 'López Sánchez', experienceYears: 10, "
							+ "location: {city: 'mad', country: 'es'},  tags: ['java', 'spring', 'javascript', 'html']}");
			people[2] = (DBObject) JSON
					.parse("{_id: '30000000L', firstName: 'Carlos',   lastNames: 'Sevilla Pérez', experienceYears: 2,  "
							+ "location: {city: 'mad', country: 'es'},  tags: ['html', 'javascript']}");
			people[3] = (DBObject) JSON
					.parse("{_id: '40000000L', firstName: 'Edu',      lastNames: 'Ruiz Galera',   experienceYears: 1,  "
							+ "location: {city: 'nav', country: 'es'},  tags: ['html']}");*/
			for (int i = 0; i < categorias.length; i++) { // Insertar tablas

				table.insert(categorias[i]); // System.out.println("Bien insertado");
				

			}

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

			// Listar la tabla "trabajador"
			/*System.out.println("Listar los registros de la tabla: ");
			DBCursor cur = table.find();
			while (cur.hasNext()) {
				System.out.println(" - " + cur.next().get(" Arte y Literatura") + " "
						+ cur.next().get(" M�sica y Cine")
						+ cur.next().get(" Deportes") 
						+ cur.next().get(" Cultura y Tradici�n")
						+ cur.next().get(" Historia y Geograf�a"));
			}
			System.out.println();

		} else {
			System.out.println("Error: Conexión no establecida");
		}*/
	}
}
