package es.uniovi.asw.gui.util.form.field;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;
import es.uniovi.asw.gui.util.form.validator.Validator;

public class FieldTest {

	/**
	 * changquye
	 */
	private String label="label";
	private String texto;
	private Validator v = new TextValidator();
	private Field f = new Field(label, v);

	@Test
	public void testReadData() {
		//Validator de texto
		texto="textoDeConsole";
		//metodo para los metodo que tiene como parametro entrada
		ByteArrayInputStream in = new ByteArrayInputStream(texto.getBytes());
		System.setIn(in);
		f.readData();
		assertEquals(texto, f.getString());
		
		texto="texto";
		ByteArrayInputStream inp = new ByteArrayInputStream(texto.getBytes());
		System.setIn(inp);
		f.readData();
		assertEquals(texto, f.getString());
		
		//validator de longitud
		v=new LengthValidator(7);
		f = new Field(label, v);
		texto="tex375o";
		ByteArrayInputStream inp1 = new ByteArrayInputStream(texto.getBytes());
		System.setIn(inp1);
		f.readData();
		assertEquals(texto, f.getString());
	}

	@Test
	public void testGetLabel() {
		assertEquals(label, f.getLabel());
	}

}
