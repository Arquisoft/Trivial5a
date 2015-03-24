package es.uniovi.asw.gui.util.form.validator.simple;

import es.uniovi.asw.gui.util.form.validator.Validator;


public class LowerThanValidator implements Validator {

	private int limite;

	public LowerThanValidator(int limite) {
		this.limite = limite;
	}

	@Override
	public boolean validar(String texto) {

		if(texto.length() > limite){
			return false;
		}
		return true;

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
