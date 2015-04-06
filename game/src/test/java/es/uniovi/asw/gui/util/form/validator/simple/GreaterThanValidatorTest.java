package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreaterThanValidatorTest {

	/**
	 * changquye
	 */
	private int limite=20;
	private GreaterThanValidator gtv = new GreaterThanValidator(limite);
	private String textoDePrueba="";

	@Test//si supera el limite verdadero
	public void testValidar() {
		assertEquals(false, gtv.validar(textoDePrueba));
		textoDePrueba="holadsjjsds";
		assertEquals(false, gtv.validar(textoDePrueba));
		textoDePrueba="holasdlskjfgsghbwkegjvhsikghkshbsjkghsjhgdhfgkjfhsigfuwhegfuwjsds";
		assertEquals(true, gtv.validar(textoDePrueba));
	}

	@Test
	public void testHelp() {
		assertNull(gtv.help());
	}

}
