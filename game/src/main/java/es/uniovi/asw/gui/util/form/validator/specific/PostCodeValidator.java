package es.uniovi.asw.gui.util.form.validator.specific;

import es.uniovi.asw.gui.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.gui.util.form.validator.simple.LengthValidator;
import es.uniovi.asw.gui.util.form.validator.simple.NumberValidator;

public class PostCodeValidator extends CheckAllValidator {

	public PostCodeValidator() {
		super(new NumberValidator(), new LengthValidator(5));
	}

	@Override
	public String help() {
		return "S�lo n�meros, 5 caracteres.";
	}

}
