package es.uniovi.asw.gui.util.form.validator.simple;

import es.uniovi.asw.gui.util.form.validator.Validator;

public class EmailSimpleValidator implements Validator {

	@Override
	public boolean validar(String text) {
		boolean arroba = false;
		boolean punto = false;
		if (text.contains("@")) {
			arroba = true;
		}
		if (text.contains(".")) {
			punto = true;
		}
		if (arroba && punto)
			return true;
		else
			return false;
	}

	@Override
	public String help() {
		return null;
	}
}
