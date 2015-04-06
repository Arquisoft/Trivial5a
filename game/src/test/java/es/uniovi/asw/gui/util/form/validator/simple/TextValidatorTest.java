package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextValidatorTest {

	/**
	 * changqu
	 */
	
	private TextValidator tv = new TextValidator();
	private String textoDePrueba="";
	
	@Test//si es texto true
	public void testValidar() {
		//assertEquals(false, tv.validar(textoDePrueba));
		textoDePrueba="ho ladsjjsds";
		assertEquals(false, tv.validar(textoDePrueba));
		textoDePrueba="hol5154adsjjsds";
		assertEquals(false, tv.validar(textoDePrueba));
		textoDePrueba="52556743583573";
		assertEquals(false, tv.validar(textoDePrueba));
		textoDePrueba="hola";
		assertEquals(true, tv.validar(textoDePrueba));
	}

	@Test
	public void testHelp() {
		assertNull(tv.help());
	}

}
