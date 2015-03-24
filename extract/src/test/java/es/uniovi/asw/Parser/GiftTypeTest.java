/**
 * 
 */
package es.uniovi.asw.Parser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.Test;

=======
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import es.uniovi.asw.extractor.parser.Document;
import es.uniovi.asw.extractor.parser.GiftType;
import es.uniovi.asw.extractor.parser.Parser;


>>>>>>> 20ad8fe43e656ba87180b589f8a119ac9057a3b1
/**
 * @author changqu
 *
 */
public class GiftTypeTest {

	GiftType gt = new GiftType();//prueba de giftType
	
	@Test
	public void test() throws IOException {
		Document file = new Document("src/main/java/es/uniovi/asw/data/preguntas.gift");
		
		Parser parser = new Parser();
		
		String leido = new String();

		BufferedReader bf = new BufferedReader(new FileReader(file));
		while (bf.ready()) {
			leido += bf.readLine() + "\n";
		}
<<<<<<< HEAD
		parser.lineas = leido.split("[\r\n]");
		bf.close();
		
		assertEquals(true, gt.verify(parser.lineas));
=======
		parser.setLineas(leido.split("[\r\n]"));
		bf.close();
		
		assertEquals(true, gt.verify(parser.getLineas()));
>>>>>>> 20ad8fe43e656ba87180b589f8a119ac9057a3b1
	}

}
