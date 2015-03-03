package es.uniovi.asw.util.form.validator.composite;

import java.util.Arrays;
import java.util.List;

import es.uniovi.asw.util.form.validator.Validator;

public abstract class CompositeValidator implements Validator{

	List<Validator> validators;
	
	public CompositeValidator(List<Validator> validators) {
		this.validators = validators;
	}
	
	public CompositeValidator(Validator... validators) {
		this(Arrays.asList(validators));
	}

	public void addValidator(Validator validator) {
		validators.add(validator);
	}
	
	public void removeValidator(Validator validator) {
		validators.remove(validator);
	}

	public abstract boolean validar(String text);
	
}
