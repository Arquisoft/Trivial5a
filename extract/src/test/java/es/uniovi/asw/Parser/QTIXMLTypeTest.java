/**
 * 
 */
package es.uniovi.asw.Parser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * @author changqu
 *
 */
public class QTIXMLTypeTest {
	
	QTIXMLType qt= new QTIXMLType();
	
	@Test
	public void test() throws IOException {
		Document file = new Document("src/main/java/es/uniovi/asw/data/preguntasQTI.xml");
	
		Parser parser = new Parser();
	
		String leido = new String();

		BufferedReader bf = new BufferedReader(new FileReader(file));
		while (bf.ready()) {
			leido += bf.readLine() + "\n";
		}
		parser.lineas = leido.split("[\r\n]");
		bf.close();
	
	
		assertEquals(true, qt.verify(parser.lineas));
	}

}
