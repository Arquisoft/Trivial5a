package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberValidatorTest {

	/**
	 * changqu
	 */
	private NumberValidator nv = new NumberValidator();
	private String textoDePrueba="";
	
	@Test//es numero true
	public void testValidar() {
		//numberValidador, bueno en todos anyade siguiente:
		//if(text.Equals("")) return false 
//		assertEquals(false, nv.validar(textoDePrueba));
		textoDePrueba="holadsjjsds";
		assertEquals(false, nv.validar(textoDePrueba));
		textoDePrueba="hol5154adsjjsds";
		assertEquals(false, nv.validar(textoDePrueba));
		textoDePrueba="52556743583573";
		assertEquals(true, nv.validar(textoDePrueba));
	}

//	@Test
//	public void testHelp() {
//		fail("Not yet implemented");
//	}

}
