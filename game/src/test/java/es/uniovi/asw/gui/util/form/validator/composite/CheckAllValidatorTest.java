package es.uniovi.asw.gui.util.form.validator.composite;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.validator.Validator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.NumberValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;

public class CheckAllValidatorTest {

	/**
	 * changqu
	 */
	
	private Validator[] validators = {new LengthValidator(20), new TextValidator()};
	private CheckAllValidator cv;
	private String textoPrueba="";
	
	@Test//todos cumple la condicion devuelve true
	public void testValidar() {
		cv = new CheckAllValidator(validators);
		textoPrueba="skjfds";
		assertEquals(false, cv.validar(textoPrueba));
		textoPrueba="11111111111fds34fghty";
		assertEquals(false, cv.validar(textoPrueba));
		textoPrueba="abcdefghijklmnopqrst";
		assertEquals(true, cv.validar(textoPrueba));
		
//		Validator nv = new NumberValidator();
//		cv.addValidator(nv);
//		assertEquals(false, cv.validar(textoPrueba));
//		cv.removeValidator(nv);
//		assertEquals(true, cv.validar(textoPrueba));
	}

//	@Test
//	public void testHelp() {
//		fail("Not yet implemented");
//	}

}
