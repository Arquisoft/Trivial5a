package es.uniovi.asw.gui.util.form.validator.composite;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.validator.Validator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;

public class CheckAllValidatorTest {

	/**
	 * changqu
	 */
	
	private Validator[] validators;
	private CheckAllValidator cv;
	private String textoPrueba="";
	
	@Test//todos cumple la condicion devuelve true
	public void testValidar() {
		LengthValidator lv = new LengthValidator(20);
		TextValidator tv = new TextValidator();
		cv = new CheckAllValidator(validators);
		cv.addValidator(lv);
		textoPrueba="skjfds";
		assertEquals(false, cv.validar(textoPrueba));
		cv.addValidator(tv);
		assertEquals(false, cv.validar(textoPrueba));
		cv.removeValidator(lv);
		assertEquals(true, cv.validar(textoPrueba));
	}

//	@Test
//	public void testHelp() {
//		fail("Not yet implemented");
//	}

}
