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

}
