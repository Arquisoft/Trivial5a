package es.uniovi.asw.gui.util.form;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class FormClienteTest {

	/**
	 * autor ChangQu Ye
	 * 
	 */
	
	private FormCliente fc = new FormCliente();

//	@Test
//	public void testFormularioCliente(){
//	
//	}
//	
//	@Test
//	public void testFormularioUpdateCliente(){
//	
//	}
	
	@Test
	public void testFormularioDni() {
		//metodo para los metodo que tiene como parametro entrada
		String texto="12345678Q";
		ByteArrayInputStream in = new ByteArrayInputStream(texto.getBytes());
		System.setIn(in);
		assertEquals(texto, fc.formularioDni());
	}

	@Test
	public void testFormularioRecomendador() {
		String texto="98765432T";
		ByteArrayInputStream in = new ByteArrayInputStream(texto.getBytes());
		System.setIn(in);
		assertEquals(texto, fc.formularioRecomendador());
	}

}
