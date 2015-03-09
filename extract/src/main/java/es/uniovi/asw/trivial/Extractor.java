package es.uniovi.asw.trivial;

import java.io.InputStream;
import java.util.Scanner;
import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.bussines.UserManager;
import es.uniovi.asw.extractor.consola.Consola;
import es.uniovi.asw.persistence.Driver;

public class Extractor {
	
	/**
	 * PUNTO DE ENTRADA DEL PROGRAMA
	 * @param args
	 * @return
	 */
	public int run(String[] args)  {
		try {
			Consola.main(args);
		} catch (Exception e) {
			System.err.println(e.getMessage()+"\n");
		}
		return 0;
	}
	
	public static void main(final String[] args) {
		new Extractor().automatizar(System.in);
    }
	
	/**
	 * Metodo que automatiza la ejecucion. Mientras este funcionando
	 * se podran meter comandos siempre que se quiera
	 */
	@SuppressWarnings("resource")
	public void automatizar(InputStream input) {
		try {
			UserManager um = new UserManager();
			Driver d = new Driver();
			//d.addUser(new User(1,"prueba2","prueba2","prueba2password",0,3,false));
			new Game(d.findAllUser()).showEstadistics();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
	}
		
	String cadena = new String();
	while(true) {
		System.out.println("Ejecutando automaticamente...(Introduzca comando)");
		System.out.println("Introduzca Q para salir");
		Scanner sc= new Scanner(input);
		cadena=sc.nextLine();
		if(cadena.toLowerCase().equals("q")) {
			System.out.println("Saliendo del programa...");
			break;
		}
		String[] comando = cadena.split(" ");
		if(comando.length!=0)
			new Extractor().run(comando);
		}
	}
}
