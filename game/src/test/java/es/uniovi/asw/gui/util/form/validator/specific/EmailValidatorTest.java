package es.uniovi.asw.gui.util.form.validator.specific;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailValidatorTest {

	/**
	 * changqu
	 */
	
	private EmailValidator ev = new EmailValidator();
	private String textoPrueba="";
	
	@Test//contiene @ . y longitud menor que 40
	public void testEmailValidator() {
		textoPrueba="changquye@yahoo.es";
		assertEquals(true, ev.validar(textoPrueba));
		textoPrueba="@dkjfshdfkskdfjgasfhs";
		assertEquals(false, ev.validar(textoPrueba));
		textoPrueba="1.G";
		assertEquals(false, ev.validar(textoPrueba));
		textoPrueba="1.G@jhfajskgdkgsdkjsdgfajskdfhasdjfahfjhaskfhaksfghawkfgahskjasdbsjfsgbfsjagshjasdgajsdfgajsdfga";
		assertEquals(false, ev.validar(textoPrueba));
	}

	@Test
	public void testHelp() {
		String mensaje = "Tiene que contener @ y . para ser"
				+ " un email v�lido, menor de 40 caracteres.";
		assertEquals(mensaje, ev.help());
	}

}
