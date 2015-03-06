package es.uniovi.asw.gui.util.form;

import es.uniovi.asw.gui.util.form.field.Field;
import es.uniovi.asw.gui.util.form.validator.specific.CityValidator;
import es.uniovi.asw.gui.util.form.validator.specific.DniValidator;
import es.uniovi.asw.gui.util.form.validator.specific.EmailValidator;
import es.uniovi.asw.gui.util.form.validator.specific.NameValidator;
import es.uniovi.asw.gui.util.form.validator.specific.PostCodeValidator;
import es.uniovi.asw.gui.util.form.validator.specific.StreetValidator;
import es.uniovi.asw.gui.util.form.validator.specific.SurnameValidator;
import es.uniovi.asw.gui.util.form.validator.specific.TelephoneValidator;

/**
 * @author Pablo Fernández Rodríguez UO218251 71673238V
 * 
 *         Clase que define un formulario específico con los campos
 *         correspondientes para clientes.
 */
public class FormCliente extends Form {

	public String[] formularioCliente() {

		Form form = new Form();

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

	}

	public String[] formularioUpdateCliente() {

		Form form = new Form();

		form.addField(new Field("Dni", new DniValidator()));
		form.addField(new Field("Nombre Nuevo", new NameValidator()));
		form.addField(new Field("Apellido Nuevo", new SurnameValidator()));
		form.addField(new Field("Ciudad Nueva", new CityValidator()));
		form.addField(new Field("Calle Nueva", new StreetValidator()));
		form.addField(new Field("Código Postal Nuevo", new PostCodeValidator()));
		form.addField(new Field("Telefono Nuevo", new TelephoneValidator()));
		form.addField(new Field("Email Nuevo", new EmailValidator()));

		form.readData();

		String[] cliente = new String[8];

		cliente[0] = form.getField("Dni").getString();
		cliente[1] = form.getField("Nombre Nuevo").getString();
		cliente[2] = form.getField("Apellido Nuevo").getString();
		cliente[3] = form.getField("Ciudad Nueva").getString();
		cliente[4] = form.getField("Calle Nueva").getString();
		cliente[5] = form.getField("Codigo Postal Nuevo").getString();
		cliente[6] = form.getField("Telefono Nuevo").getString();
		cliente[7] = form.getField("Email Nuevo").getString();

		return cliente;

	}

	public String formularioDni() {
		Form form = new Form();

		form.addField(new Field("Dni", new DniValidator()));

		form.readData();

		return form.getField("Dni").getString();
	}

	public String formularioRecomendador() {
		Form form = new Form();

		form.addField(new Field("Dni Recomendador", new DniValidator()));

		form.readData();

		return form.getField("Dni Recomendador").getString();
	}

}
