/**
 * 
 */
package es.uniovi.asw.Parser;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.Consola.ConsolaParser;

/**
 * @author ikzer
 *
 */
public class ParserTest {
	
	Parser parser = new Parser();
	
	@Test
	public void testVerifyGift() throws Exception {
		Document archivoEntrada = new Document("src/main/java/es/uniovi/asw/data/preguntas.gift");
		ConsolaParser parser = new ConsolaParser(archivoEntrada);
		parser.reader();
		assertTrue(parser.verificarFormato());
	}
	
	@Test
	public void testVerifyQTI() throws Exception {
		Document archivoEntrada = new Document("src/main/java/es/uniovi/asw/data/preguntasQTI.xml");
		ConsolaParser parser = new ConsolaParser(archivoEntrada);
		parser.reader();
		assertTrue(parser.verificarFormato());
	}

	@Test
	public void testConstructor() {
		String[] l = new String[2];
		l[0] = "a";
		l[1] = "b";
		Parser p = new Parser(l);
		assertArrayEquals(l,p.lineas);
	}
	
	@Test
	public void testGetLineas() {
		String[] l = new String[2];
		l[0] = "a";
		l[1] = "b";
		Parser p = new Parser(l);
		assertArrayEquals(l,p.getLineas());
	}
	
	@Test
	public void testGetTransformado() {
		String[] l = new String[2];
		l[0] = "a";
		l[1] = "b";
		Parser p = new Parser(l);
		assertArrayEquals(l,p.getTransformado());
	}
}
