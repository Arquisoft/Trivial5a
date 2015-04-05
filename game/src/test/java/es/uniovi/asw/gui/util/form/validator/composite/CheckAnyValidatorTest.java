package es.uniovi.asw.gui.util.form.validator.composite;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.validator.Validator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;

public class CheckAnyValidatorTest {

	/**
	 * changqu
	 */
	private Validator[] validators;
	private CheckAnyValidator cv;
	private String textoPrueba="";
	
	@Test//alguno que cumple la condicion devuelve true
	public void testValidar() {
		LengthValidator lv = new LengthValidator(20);
		TextValidator tv = new TextValidator();
		cv = new CheckAnyValidator(validators);
		cv.addValidator(lv);
		textoPrueba="skjfds";
		assertEquals(false, cv.validar(textoPrueba));
		cv.addValidator(tv);
		assertEquals(true, cv.validar(textoPrueba));
	}

//	@Test
//	public void testHelp() {
//		fail("Not yet implemented");
//	}

}
