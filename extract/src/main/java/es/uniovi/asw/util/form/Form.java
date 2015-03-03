package es.uniovi.asw.util.form;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.util.form.field.Field;

/**
 * @author Pablo Fernández Rodríguez UO218251 71673238V
 * 
 * Clase que define un formulario compuesto por campos
 */
public class Form {
	
	/**
	 * Coleccion de campos que forman el formulario
	 */
	private List<Field> fields = new ArrayList<Field>();
	
	/**
	 * Metodo que permite añadir un campo al formulario
	 * @param field Campo a añadir
	 */
	public void addField(Field field) {
		fields.add(field);
	}

	/**
	 * Metodo que pide los datos para rellenar el formulario.
	 * Lo hace pidiendo el data a cada uno de los campos.
	 */
	public void readData() {
		for (Field field : fields) {
			field.readData();
			System.out.println(field.getString());
		}
	}
	
	public Field getField(String label) {
		for(Field campo : fields)
			if(campo.getLabel().equals(label))
				return campo;
		return null;
	}
	
}
