package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityValidatorTest {

	/**
	 * changqu
	 */
	
	private CityValidator cv = new CityValidator();
	private String textoPrueba="";
	
	@Test//si es valido una ciudad
	public void testCityValidator() {
		textoPrueba="oviedo";
		assertEquals(true, cv.validar(textoPrueba));
		textoPrueba="ovieoasifhhsjkgsdhgskdjfhasdkjfshdfkskdfjgasfhs";
		assertEquals(false, cv.validar(textoPrueba));
		textoPrueba="123134244";
		assertEquals(false, cv.validar(textoPrueba));
	}
	
	@Test
	public void testHelp() {
		String mensaje = "S�lo texto, menos de 20 caracteres.";
		assertEquals(mensaje, cv.help());
	}

	

}
