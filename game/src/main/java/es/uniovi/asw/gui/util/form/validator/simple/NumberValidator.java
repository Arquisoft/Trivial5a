package es.uniovi.asw.gui.util.form.validator.simple;

import es.uniovi.asw.gui.util.form.validator.Validator;

public class NumberValidator implements Validator {

	@Override
	public boolean validar(String text) {
		for (char ch : text.toCharArray()) {
			if (!Character.isDigit(ch)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String help() {
		return null;
	}
}
