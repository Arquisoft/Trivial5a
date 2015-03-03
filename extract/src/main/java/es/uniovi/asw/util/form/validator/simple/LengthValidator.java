package es.uniovi.asw.util.form.validator.simple;

import es.uniovi.asw.util.form.validator.Validator;

public class LengthValidator implements Validator {

	private int longitud;

	public LengthValidator(int longitud) {
		this.longitud = longitud;
	}

	@Override
	public boolean validar(String texto) {

		if(texto.length() == longitud){
			return true;
		}
		return false;

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
