package es.uniovi.asw.util.form;

/**
 * @author Pablo Fernández Rodríguez UO218251 71673238V
 * 
 *         Clase que define un formulario específico con los campos
 *         correspondientes para usuarios del Trivial.
 */
public class FormularioUsuarioTrivial extends Form {

	public String[] formularioUsuarioTrivial() {

		Form form = new Form();

		//Tomar de ejemplo el formulario de la clase FormCliente para adaptar
		//un formulario validador para los usuarios del trivial.
		
		/*
			form.addField(new Field("Dni", new DniValidator()));
			form.addField(new Field("Nombre", new NameValidator()));
			form.addField(new Field("Apellido", new SurnameValidator()));
			form.addField(new Field("Ciudad", new CityValidator()));
			form.addField(new Field("Calle", new StreetValidator()));
			form.addField(new Field("Codigo Postal", new PostCodeValidator()));
			form.addField(new Field("Telefono", new TelephoneValidator()));
			form.addField(new Field("Email", new EmailValidator()));
	
			form.readData();
	
			String[] cliente = new String[8];
	
			cliente[0] = form.getField("Dni").getString();
			cliente[1] = form.getField("Nombre").getString();
			cliente[2] = form.getField("Apellido").getString();
			cliente[3] = form.getField("Ciudad").getString();
			cliente[4] = form.getField("Calle").getString();
			cliente[5] = form.getField("Codigo Postal").getString();
			cliente[6] = form.getField("Telefono").getString();
			cliente[7] = form.getField("Email").getString();
	
			return cliente;
		 */
		return null;
	}

}
