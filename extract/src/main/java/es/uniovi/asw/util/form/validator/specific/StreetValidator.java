package es.uniovi.asw.util.form.validator.specific;

import es.uniovi.asw.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.util.form.validator.simple.LowerThanValidator;
import es.uniovi.asw.util.form.validator.simple.TextValidator;

public class StreetValidator extends CheckAllValidator {

	public StreetValidator() {
		super(new TextValidator(), new LowerThanValidator(40));
	}
	
	public String help() {
		return "S�lo texto, menos de 40 caracteres.";
	}
}
