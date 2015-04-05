package es.uniovi.asw.gui.util.form.validator.simple;

import static org.junit.Assert.*;

import org.junit.Test;

public class PredefinedValidatorTest {

	/**
	 * changqu
	 */
	
	private String[] values={"hola", "adios", "joder"};
	private PredefinedValidator pv = new PredefinedValidator(values);
	private String textoDePrueba="";

	@Test//si es predefinido true
	public void testValidar() {
		assertEquals(false, pv.validar(textoDePrueba));
		textoDePrueba="hola";
		assertEquals(true, pv.validar(textoDePrueba));
		textoDePrueba="adiOs";
		assertEquals(true, pv.validar(textoDePrueba));
		textoDePrueba="Joder";
		assertEquals(true, pv.validar(textoDePrueba));
		textoDePrueba="puta";
		assertEquals(false, pv.validar(textoDePrueba));
	}

//	@Test
//	public void testHelp() {
//		fail("Not yet implemented");
//	}

}
