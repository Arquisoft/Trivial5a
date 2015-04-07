package es.uniovi.asw.gui.util.form.validator.composite;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.validator.Validator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.NumberValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;

public class CheckAnyValidatorTest {

	/**
	 * changqu
	 */
	private Validator[] validators = {new TextValidator(), new LengthValidator(20), new NumberValidator()};
	private CheckAnyValidator cv = new CheckAnyValidator(validators);
	private String textoPrueba="";
	
	@Test//alguno que cumple la condicion devuelve true
	public void testValidar() {
		textoPrueba="skjfds";
		assertEquals(true, cv.validar(textoPrueba));
		textoPrueba="4352345356";
		assertEquals(true, cv.validar(textoPrueba));
		textoPrueba="skjfdksf84w5s";
		assertEquals(false, cv.validar(textoPrueba));
	}

//	@Test
//	public void testHelp() {
//		fail("Not yet implemented");
//	}

}
