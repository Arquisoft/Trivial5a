package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostCodeValidatorTest {

	/**
	 * changqu
	 */
	
	private PostCodeValidator pcv = new PostCodeValidator();
	private String textoPrueba="";
	
	@Test//codigo postal
	public void testPostCodeValidator() {
		textoPrueba="12345";
		assertEquals(true, pcv.validar(textoPrueba));
		textoPrueba="abcde";
		assertEquals(false, pcv.validar(textoPrueba));
		textoPrueba="1236G";
		assertEquals(false, pcv.validar(textoPrueba));
		textoPrueba="1236Ghgkdfjjhfd";
		assertEquals(false, pcv.validar(textoPrueba));
		textoPrueba="1236879896";
		assertEquals(false, pcv.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "S�lo n�meros, 5 caracteres.";
		assertEquals(mensaje, pcv.help());
	}

}
