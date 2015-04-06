package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameValidatorTest {

	/**
	 * changqu
	 */
	
	private NameValidator nv = new NameValidator();
	private String textoPrueba="";
	
	@Test
	public void testNameValidator() {
		textoPrueba="Juan";
		assertEquals(true, nv.validar(textoPrueba));
		textoPrueba="ovieoasifhhsjkgsdhgskdjfhasdkjfshdfkskdfjgasfhs";
		assertEquals(false, nv.validar(textoPrueba));
		textoPrueba="1236G";
		assertEquals(false, nv.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "S�lo texto, menos de 10 caracteres.";
		assertEquals(mensaje, nv.help());
	}

}
