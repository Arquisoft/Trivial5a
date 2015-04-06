/**
 * 
 */
package es.uniovi.asw.Consola;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.extractor.consola.ConsolaParser;
import es.uniovi.asw.extractor.consola.ConsolaUtils;
import es.uniovi.asw.extractor.parser.Document;

/**
 * @author Fernando Delgado
 *
 */
public class ConsolaUtilsTest {

	ConsolaUtils c = new ConsolaUtils();
	
	@Test
	public void testValidExtension() {
		assertEquals("test", ConsolaUtils.getFileName("test.gift"));
	}

	@Test
	public void testNoExtension() {
		assertEquals("test", ConsolaUtils.getFileName("test"));
	}
	
	@Test
	public void testTransformInvalid() throws Exception {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.gift");
		ConsolaParser p = new ConsolaParser(d);
		try {
			ConsolaUtils.transform(p, "GIFT");
		} catch (Exception e) {
			assertEquals("Formato de salida no valido", e.getMessage());
		}
	}
	
	@Test
	public void testTransformGValid() throws Exception {
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.gift");
		ConsolaParser p = new ConsolaParser(d);
		try {
			ConsolaUtils.transform(p, "JSON");
		} catch (Exception e) {
			assertEquals("Formato de salida no valido", e.getMessage());
		}
	}
	
	@Test
	public void testSetTipoDocumentoInvalid_1Arg(){
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.gift");
		try {
			ConsolaUtils.setTipoDocumento(d);
		} catch (Exception e) {
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testSetTipoDocumentoInvalid_2Args(){
		Document d = new Document("src/main/java/es/uniovi/asw/data/test.gift");
		try {
			ConsolaUtils.setTipoDocumento(d,"txt");
		} catch (Exception e) {
			assertEquals("Tipo de archivo no valido", e.getMessage());
		}
	}
	
	@Test
	public void testSaveFile() throws Exception {
		String[] file = new String[2];
		file[0] = "Linea 1";
		file[1] = "Linea 2";
		
		ConsolaUtils.saveFile(file, "testSaveFile");
	}
	
	@Test
	public void testSaveFileExtension() throws Exception {
		String[] file = new String[2];
		file[0] = "Linea 1";
		file[1] = "Linea 2";
		
		ConsolaUtils.saveFile(file, "testSaveFile", "JSON");
	}
}
