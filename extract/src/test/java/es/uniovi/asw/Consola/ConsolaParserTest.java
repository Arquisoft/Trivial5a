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
public class ConsolaParserTest {

	@Test
	public void testConstructorGift() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.gift");
		try {
			ConsolaParser parser = new ConsolaParser(d);
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testConstructorQTI() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.xml");
		try {
			ConsolaParser parser = new ConsolaParser(d);
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testConstructorInvalid() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.txt");
		try {
			ConsolaParser parser = new ConsolaParser(d);
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testConstructorSinExtensionGift() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test");
		try {
			ConsolaParser parser = new ConsolaParser(d, "gift");
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testConstructorSinExtensionQTI() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test");
		try {
			ConsolaParser parser = new ConsolaParser(d, "xml");
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testConstructorSinExtensionInvalido() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test");
		try {
			ConsolaParser parser = new ConsolaParser(d, "txt");
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testDepurar() {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test");
		try {
			ConsolaParser parser = new ConsolaParser(d, "gift");
		} catch(Exception e) {	
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
}
