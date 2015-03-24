/**
 * 
 */
package es.uniovi.asw.Consola;

import static org.junit.Assert.*;

import org.junit.Test;

<<<<<<< HEAD
=======
import es.uniovi.asw.extractor.consola.Consola;


>>>>>>> 20ad8fe43e656ba87180b589f8a119ac9057a3b1
/**
 * @author ikzer
 *
 */
public class ConsolaTest {

	Consola c = new Consola();
	
	@Test
	public void testIncorrectArgs() {
		try {
			String[] args = new String[5];
			Consola.main(args);
		} catch (Exception e) {
			assertEquals("Comando mal introducido ", e.getMessage());
		}
	}
	
	@Test
	public void test2ArgsNoExiste() {
		try {
			String[] args = new String[2];
			args[0] = "testNoExiste.gift";
			Consola.main(args);
		} catch (Exception e) {
			assertEquals("src/main/java/es/uniovi/asw/data/testNoExiste.gift (No existe el archivo o el directorio)", e.getMessage());
		}
	}
	
	@Test
	public void test2ArgsFormatoInvalido() {
		try {
			String[] args = new String[2];
			args[0] = "preguntasMalFormato.gift";
			Consola.main(args);
		} catch (Exception e) {
			assertEquals("Formato de entrada no valido", e.getMessage());
		}
	}
	
	@Test
	public void test4ArgsNoExiste() {
		try {
			String[] args = new String[4];
			args[0] = "testNoExiste.gift";
			args[1] = "gift";
			args[3] = "json";
			
			Consola.main(args);
		} catch (Exception e) {
			assertEquals("src/main/java/es/uniovi/asw/data/testNoExiste.gift (No existe el archivo o el directorio)", e.getMessage());
		}
	}
	
	@Test
	public void test4ArgsFormatoSalidaInvalido() {
		try {
			String[] args = new String[4];
			args[0] = "preguntasMalFormato.gift";
			args[1] = "gift";
			
			args[3] = "txt";
			Consola.main(args);
		} catch (Exception e) {
			assertEquals("Formato de salida no valido", e.getMessage());
		}
	}
	
	@Test
	public void test4ArgsFormatoInvalido() {
		try {
			String[] args = new String[4];
			args[0] = "preguntas.gift";
			args[1] = "gift";
			
			args[3] = "json";
			Consola.main(args);
		} catch (Exception e) {
			assertEquals("Formato de salida no valido", e.getMessage());
		}
	}

}
