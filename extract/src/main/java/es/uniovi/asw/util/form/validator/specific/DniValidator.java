package es.uniovi.asw.util.form.validator.specific;

import es.uniovi.asw.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.util.form.validator.simple.LengthValidator;

public class DniValidator extends CheckAllValidator {

	public DniValidator() {
		super(new LengthValidator(9));
	}
	
	public String help() {
		return "9 caracteres.";
	}
}
