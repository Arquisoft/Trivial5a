package es.uniovi.asw.gui.util.form.validator.composite;

import es.uniovi.asw.gui.util.form.validator.Validator;

public class CheckAnyValidator extends CompositeValidator {

	public CheckAnyValidator(Validator... validators) {
		super(validators);
	}

	@Override
	public boolean validar(String text) {
		for (Validator v : validators) {
			if (v.validar(text)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String help() {
		return null;
	}
}
