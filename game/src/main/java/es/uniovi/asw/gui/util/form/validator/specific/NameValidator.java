package es.uniovi.asw.gui.util.form.validator.specific;

import es.uniovi.asw.gui.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.gui.util.form.validator.simple.LowerThanValidator;
import es.uniovi.asw.gui.util.form.validator.simple.TextValidator;


public class NameValidator extends CheckAllValidator{

	public NameValidator() {
		super(new TextValidator(), new LowerThanValidator(10));
	}
	
	@Override
	public String help() {
		return "S�lo texto, menos de 10 caracteres.";
	}
	
}
