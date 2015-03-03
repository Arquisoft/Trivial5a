package es.uniovi.asw.util.form.validator.simple;

import es.uniovi.asw.util.form.validator.Validator;

public class TextValidator implements Validator {

	@Override
	public boolean validar(String text) {

		for (char ch : text.toCharArray()) {
			if (!Character.isLetter(ch)) {
				return false;
			}
		}
		
		return true;
		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
