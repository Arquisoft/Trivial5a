package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class SurnameValidatorTest {

	/**
	 * changqu
	 */
	
	private SurnameValidator sv = new SurnameValidator();
	private String textoPrueba="";
	
	@Test
	public void testSurnameValidator() {
		textoPrueba="Manuel";
		assertEquals(true, sv.validar(textoPrueba));
		textoPrueba="ovieoasifhhsjkgsdhgskdjfhasdkjfshdfkskdfjgasfhs";
		assertEquals(false, sv.validar(textoPrueba));
		textoPrueba="1236G";
		assertEquals(false, sv.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "S�lo texto, menos de 20 caracteres.";
		assertEquals(mensaje, sv.help());
	}

}
