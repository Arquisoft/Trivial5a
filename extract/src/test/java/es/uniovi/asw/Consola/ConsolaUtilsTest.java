/**
 * 
 */
package es.uniovi.asw.Consola;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.Parser.Document;

/**
 * @author Fernando Delgado
 *
 */
public class ConsolaUtilsTest {

	ConsolaUtils c = new ConsolaUtils();
	
	@Test
	public void testValidExtension() {
		assertEquals("test", c.getFileName("test.gift"));
	}

	@Test
	public void testNoExtension() {
		assertEquals("test", c.getFileName("test"));
	}
	
	@Test
	public void testTransformJSON() throws Exception {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.gift");
		ConsolaParser p = new ConsolaParser(d);
		try {
			ConsolaUtils.transform(p, "GIFT");
		} catch (Exception e) {
			assertEquals("Formato de salida no valido", e.getMessage());
		}
	}
}
