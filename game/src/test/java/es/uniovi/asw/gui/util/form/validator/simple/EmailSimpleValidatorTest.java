package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailSimpleValidatorTest {
	
	/**
	 * changqu
	 */
	private EmailSimpleValidator esv = new EmailSimpleValidator();
	private String textoDePrueba="";
	
	@Test//comprobar email
	public void testValidar() {
		textoDePrueba="";
		assertEquals(false, esv.validar(textoDePrueba));
		textoDePrueba="1255@";
		assertEquals(false, esv.validar(textoDePrueba));
		textoDePrueba="1255@yahoo.com";
		assertEquals(true, esv.validar(textoDePrueba));
	}

	@Test
	public void testHelp() {
		assertNull(esv.help());
	}

}
