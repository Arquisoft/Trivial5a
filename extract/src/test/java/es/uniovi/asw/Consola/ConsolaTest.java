/**
 * 
 */
package es.uniovi.asw.Consola;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
