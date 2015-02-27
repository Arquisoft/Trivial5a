/**
 * 
 */
package es.uniovi.asw.trivial;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author Fernando Delgado
 *
 */
public class ExtractorTest {
	Extractor extractor = new Extractor();
	
	@Test
	public void testAutomatizar_q() {
		ByteArrayInputStream in = new ByteArrayInputStream("Q".getBytes());
		System.setIn(in);

		extractor.automatizar();

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
