package es.uniovi.asw.gui.util.form.validator.specific;

import es.uniovi.asw.gui.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.gui.util.form.validator.simple.LowerThanValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;

public class SurnameValidator extends CheckAllValidator {

	public SurnameValidator() {
		super(new TextValidator(), new LowerThanValidator(20));
	}

	@Override
	public String help() {
		return "S�lo texto, menos de 20 caracteres.";
	}

}
