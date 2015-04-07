package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class LengthValidatorTest {

	/**
	 * changquye
	 */
	private int longitud=20;
	private LengthValidator lv = new  LengthValidator(longitud);
	private String textoDePrueba="";

	@Test//misma longitud verdadero
	public void testValidar() {
		assertEquals(false, lv.validar(textoDePrueba));
		textoDePrueba="abcdefghiopqrstu";
		assertEquals(false, lv.validar(textoDePrueba));
		textoDePrueba="abcdefghijklmnopqrst";
		assertEquals(true, lv.validar(textoDePrueba));
		textoDePrueba="abcdefghijklmnopqrsssdsdsdsdstu";
		assertEquals(false, lv.validar(textoDePrueba));
	}

	@Test
	public void testHelp() {
		assertNull(lv.help());
	}

}
