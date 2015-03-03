package es.uniovi.asw.util.form.validator.specific;

import es.uniovi.asw.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.util.form.validator.simple.NumberValidator;

public class TelephoneValidator extends CheckAllValidator {

	public TelephoneValidator() {
		super(new NumberValidator(), new LengthValidator(9));
	}
	
	public String help() {
		return "S�lo n�meros, 9 caracteres.";
	}
	
}
