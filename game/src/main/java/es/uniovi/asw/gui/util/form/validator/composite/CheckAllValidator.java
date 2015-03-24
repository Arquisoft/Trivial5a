package es.uniovi.asw.gui.util.form.validator.composite;

import es.uniovi.asw.gui.util.form.validator.Validator;

public class CheckAllValidator extends CompositeValidator{

	public CheckAllValidator(Validator... validators) {
		super(validators);
	}
	
	@Override
	public boolean validar(String text) {

		for(Validator v : validators){
			if(!v.validar(text)){
				return false;
			}
		}
		return true;		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
