package es.uniovi.asw.gui.util.form.validator.specific;

import es.uniovi.asw.gui.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;

public class DniValidator extends CheckAllValidator {

	public DniValidator() {
		super(new LengthValidator(9));
	}

	@Override
	public String help() {
		return "9 caracteres.";
	}
}
