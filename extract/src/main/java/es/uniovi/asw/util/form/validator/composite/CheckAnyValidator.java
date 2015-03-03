package es.uniovi.asw.util.form.validator.composite;

import es.uniovi.asw.util.form.validator.Validator;

public class CheckAnyValidator extends CompositeValidator{

	public CheckAnyValidator(Validator... validators) {
		super(validators);
	}
	
	@Override
	public boolean validar(String text) {

		for(Validator v : validators){
			if(v.validar(text)){
				return true;
			}
		}
		return false;		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
