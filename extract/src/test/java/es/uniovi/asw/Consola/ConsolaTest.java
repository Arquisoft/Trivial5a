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
	public void test2Args() {
		
	}

}
