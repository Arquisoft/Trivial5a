package es.uniovi.asw.util.form.validator.specific;

import es.uniovi.asw.util.form.validator.composite.CheckAllValidator;
import es.uniovi.asw.util.form.validator.simple.EmailSimpleValidator;
import es.uniovi.asw.util.form.validator.simple.LowerThanValidator;

public class EmailValidator extends CheckAllValidator {

	public EmailValidator() {
		super(new EmailSimpleValidator(), new LowerThanValidator(40));
	}

	public String help() {
		return "Tiene que contener @ y . para ser"
				+ " un email v�lido, menor de 40 caracteres.";
	}

}
