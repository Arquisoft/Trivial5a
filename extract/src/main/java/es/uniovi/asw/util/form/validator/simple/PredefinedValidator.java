package es.uniovi.asw.util.form.validator.simple;

import es.uniovi.asw.util.form.validator.Validator;

public class PredefinedValidator implements Validator {

	/**
	 * Valores predefinidos del campo.
	 */
	private String[] values;
		
	/**
	 * Constructor del campo que recibe la lista de valores
	 * @param values Colecci�n de valores predefinidos.
	 */
	public PredefinedValidator(String... values) {
		this.values = values;
	}
	
	@Override
	public boolean validar(String text) {

		for (String valor : values) {
			if (text.toLowerCase().equals(valor.toLowerCase())) {
				return true;
			}
		}
		return false;	
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
