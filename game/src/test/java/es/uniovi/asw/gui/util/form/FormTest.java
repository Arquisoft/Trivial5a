package es.uniovi.asw.gui.util.form;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.field.Field;
import es.uniovi.asw.gui.util.form.validator.Validator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;

public class FormTest {

	/**
	 * changqu
	 */
	
	private Form form = new Form();
	
	@Test
	public void testAddField() {
		Validator v = new TextValidator();
		form.addField(new Field("label",v));
		Validator v1 = new LengthValidator(20);
		form.addField(new Field("labelotro",v1));
	}

	@Test
	public void testReadData() {
		Validator v = new TextValidator();
		Field f1 = new Field("label",v);
		form.addField(f1);
//		Validator v1 = new LengthValidator(20);
//		Field f2 = new Field("labelotro",v1);
//		form.addField(f2);
		String texto="textoDeConsoleabcdef";
		//metodo para los metodo que tiene como parametro entrada
		ByteArrayInputStream in = new ByteArrayInputStream(texto.getBytes());
		System.setIn(in);
		form.readData();
		assertEquals(texto, f1.getString());
		//assertEquals(texto, f2.getString());
	}

	@Test
	public void testGetField() {
		Validator v = new TextValidator();
		Field f1 = new Field("label",v);
		form.addField(f1);
		
		Validator v1 = new LengthValidator(20);
		Field f2 = new Field("labelotro",v1);
		form.addField(f2);
		
		assertEquals(f1, form.getField("label"));
		assertEquals(f2, form.getField("labelotro"));
	}

}
