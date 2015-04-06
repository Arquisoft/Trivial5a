package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class LowerThanValidatorTest {

	/**
	 * changquye
	 */
	private int limite=20;
	private LowerThanValidator ltv = new LowerThanValidator(limite);
	private String textoDePrueba="";
	
	@Test//menor que limite true
	public void testValidar() {
		assertEquals(true, ltv.validar(textoDePrueba));
		textoDePrueba="holadsjjsds";
		assertEquals(true, ltv.validar(textoDePrueba));
		textoDePrueba="holasdlskjfgsghbwkegjvhsikghkshbsjkghsjhgdhfgkjfhsigfuwhegfuwjsds";
		assertEquals(false, ltv.validar(textoDePrueba));
	}

	@Test
	public void testHelp() {
		assertNull(ltv.help());
	}

}
