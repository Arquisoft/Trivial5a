package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class TelephoneValidatorTest {

	/**
	 * changqu
	 */
	
	private TelephoneValidator tv = new TelephoneValidator();
	private String textoPrueba="";
	
	@Test
	public void testTelephoneValidator() {
		textoPrueba="123456789";
		assertEquals(true, tv.validar(textoPrueba));
		textoPrueba="ovieoasifhhsjkgsdhgskdjfhasdkjfshdfkskdfjgasfhs";
		assertEquals(false, tv.validar(textoPrueba));
		textoPrueba="1236Gfg67";
		assertEquals(false, tv.validar(textoPrueba));
		textoPrueba="1236253523564376867";
		assertEquals(false, tv.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "S�lo n�meros, 9 caracteres.";
		assertEquals(mensaje, tv.help());
	}

}
