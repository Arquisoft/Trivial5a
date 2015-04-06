package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class DniValidatorTest {
	/**
	 * changqu
	 */
	
	private DniValidator dv = new DniValidator();
	private String textoPrueba="";
	
	@Test
	public void testDniValidator() {
		textoPrueba="12345678C";
		assertEquals(true, dv.validar(textoPrueba));
		textoPrueba="ovieoasifhhsjkgsdhgskdjfhasdkjfshdfkskdfjgasfhs";
		assertEquals(false, dv.validar(textoPrueba));
		textoPrueba="1236G";
		assertEquals(false, dv.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "9 caracteres.";
		assertEquals(mensaje, dv.help());
	}

}
