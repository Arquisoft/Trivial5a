package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class StreetValidatorTest {

	/**
	 * changqu
	 */
	
	private StreetValidator sv = new StreetValidator();
	private String textoPrueba="";
	
	@Test
	public void testStreetValidator() {
		textoPrueba="mount";
		assertEquals(true, sv.validar(textoPrueba));
		textoPrueba="ovieoasifhhsjkgsdhgskdjfhasdkjfshdfkskdfjgasfhs";
		assertEquals(false, sv.validar(textoPrueba));
		textoPrueba="1236G";
		assertEquals(false, sv.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "S�lo texto, menos de 40 caracteres.";
		assertEquals(mensaje, sv.help());
	}

}
