/**
 * 
 */
package es.uniovi.asw.trivial;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
<<<<<<< HEAD
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

=======
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

>>>>>>> 20ad8fe43e656ba87180b589f8a119ac9057a3b1
/**
 * @author Fernando Delgado
 *
 */
public class ExtractorTest {
	Extractor extractor = new Extractor();
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testAutomatizar_q() {
		ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
		System.setIn(in);

		extractor.automatizar(in);
		assertEquals("Ejecutando automaticamente...(Introduzca comando)\nIntroduzca Q para salir\nSaliendo del programa...\n", outContent.toString());
		System.setIn(System.in);
	}
	
	@Test
	public void testAutomatizar_1Arg() {
		ByteArrayInputStream in = new ByteArrayInputStream("TestIncorrectInput".getBytes());
		System.setIn(in);

		extractor.automatizar(in);

		System.setIn(System.in);
	}
	
	@Test
	public void testRunValid() {
		String[] arg = new String[4];
		assertEquals(0, extractor.run(arg));
	}
	
	@Test
	public void testRunInvalid() {
		String[] arg = new String[2];
		try {
			extractor.run(arg);
		} catch (Exception e) {
			assertEquals("Comando mal introducido \n", e.getMessage());
		}
	}

}