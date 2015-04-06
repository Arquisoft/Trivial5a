package es.uniovi.asw.gui.util.form.field;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;
import es.uniovi.asw.gui.util.form.validator.Validator;

public class FieldTest {

	/**
	 * changquye
	 */
	private String label="label";
	private String texto="texto";
	private Validator v = new TextValidator();
	private Field f = new Field(label, v);

	@Test
	public void testReadData() {
		f.readData();
	}

	@Test
	public void testGetString() {
		assertEquals(texto, f.getString());
	}

	@Test
	public void testGetLabel() {
		assertEquals(label, f.getLabel());
	}

}
