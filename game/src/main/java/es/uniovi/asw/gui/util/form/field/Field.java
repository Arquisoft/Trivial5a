package es.uniovi.asw.gui.util.form.field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.uniovi.asw.gui.util.form.validator.Validator;

public class Field {

	/**
	 * Etiqueta para identificar al campo.
	 */
	private String label;

	/**
	 * Valor textual del campo.
	 */
	private String text;

	/**
	 * Validador de los campos.
	 */
	private Validator validator;

	public Field(String label, Validator validator) {
		this.label = label;
		this.validator = validator;
	}

	/**
	 * Metodo que pide el dato para rellenar el valor del campo Comprueba que el
	 * valor sea un numero.
	 * 
	 * @see Field#readData()
	 */
	public void readData() {
		BufferedReader consola = new BufferedReader(new InputStreamReader(
				System.in));

		boolean valido;
		boolean repetido = false;
		do {
			valido = true;
			try {
				System.out.print(label + ": ");
				text = consola.readLine();

				valido = validator.validar(getString());

				if (!valido)
					if (repetido)
						System.out.println("HELP: " + validator.help());

				repetido = true;

			} catch (IOException ex) {
				System.out.println(ex);
			}
		} while (!valido);
	}

	/**
	 * Metodo que devuleve el valor del formulario de forma textual
	 * 
	 * @return Devolvera el valor del campo en forma de texto
	 * @see Field#getString()
	 */
	public String getString() {
		return text;
	}

	public String getLabel() {
		return label;
	}

}
