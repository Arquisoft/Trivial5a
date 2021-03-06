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

import es.uniovi.asw.extractor.parser.Document;
import es.uniovi.asw.extractor.parser.GiftType;
import es.uniovi.asw.extractor.parser.Parser;



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

		parser.setLineas(leido.split("[\r\n]"));
		bf.close();
		
		//assertFalse(gt.verify(parser.getLineas()));

	}

}
