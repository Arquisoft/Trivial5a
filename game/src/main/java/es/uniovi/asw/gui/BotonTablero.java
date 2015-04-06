package es.uniovi.asw.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import es.uniovi.asw.bussines.Game;

public class BotonTablero extends JButton {

	private static final long serialVersionUID = 1L;

	public Game juego;

	public Color color;

	public int id_boton;

	public int categoria;

	public boolean quesito;

	public BotonTablero(int id, final Juego pantalla, final Game juego) {
		super();
		this.juego = juego;
		this.setIgnoreRepaint(true);

		// Éste campo se relaciona con la logica
		id_boton = id;
		this.setText(" ");
		this.setBackground(pintarBoton(id));
		this.repaint();
		isQuesito(id);
		asignarCategoria(id);

		// El nombre del boton, pero hay veces que java no permite usarlo
		// algenerarse de forma dinamica
		this.setActionCommand(String.valueOf(id));

		// Todos desactivados hasta que se tire el dado
		this.setEnabled(false);

		// Si se solapan los botones en pantallas mas pequeñas reducid un poco
		// estos campos
		this.setPreferredSize(new Dimension(45, 45));
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				juego.getUsuarioActivo().setPosicion(id_boton);
				pantalla.pintarPosicion(id_boton);
				System.out.print("POS BtTablero: "
						+ juego.getUsuarioActivo().getPosicion() + "\n");
				VentanaPregunta vp = new VentanaPregunta(quesito, categoria,
						pantalla, juego, id_boton);
				vp.setVisible(true);
			}
		});

	}

	/**
	 * Devuelve el valor de categoria
	 * 
	 * @return categoria
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Cambia el valor de categoria
	 * 
	 * @param categoria
	 */
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve el valor de color
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Devuelve el valor de id_boton
	 * 
	 * @return id_boton
	 */
	public int getId_boton() {
		return id_boton;
	}

	/**
	 * Cambia el valor de id_boton
	 * 
	 * @param id_boton
	 */
	public void setId_boton(int id_boton) {
		this.id_boton = id_boton;
	}

	/**
	 * Devuelve el valor de quesito
	 * 
	 * @return quesito
	 */
	public boolean isQuesito() {
		return quesito;
	}

	/**
	 * Pinta la zona de la tabla con el color del quesito según la categoría
	 * 
	 * @param bt
	 * @return
	 */
	private Color pintarBoton(int bt) { // auxiliar para generar botones
		switch (bt % 6) {
		case 0:
			color = new Color(255, 51, 204); // pink
			return color;
		case 1:
			color = new Color(255, 255, 153); // orange
			return color;
		case 2:
			color = new Color(255, 140, 0); // yellow
			return color;
		case 3:
			color = new Color(30, 144, 255); // blue
			return color;
		case 4:
			color = new Color(156, 93, 82); // brown
			return color;
		case 5:
			color = new Color(0, 153, 51); // green
			return color;
		}
		return null;
	}

	private void isQuesito(int bt) {
		if (bt % 5 == 0) { // (bt%numcat-1)
			this.quesito = true;
		}
	}

	/**
	 * Asignar categoría al color
	 * 
	 * @param bt
	 */
	private void asignarCategoria(int bt) {
		switch (bt % 6) {
		case 0: // rosa
			categoria = 4;
			break;
		case 1: // naranja
			categoria = 5;
			break;
		case 2: // amarillo
			categoria = 1;
			break;
		case 3: // azul
			categoria = 0;
			break;
		case 4: // marron
			categoria = 2;
			break;
		case 5: // verde
			categoria = 3;
		}
	}

	/**
	 * CATEGORIAS - en funcion como estan en la base de datos para organizarlas
	 * a la hora de sacar la pregunta por pantalla
	 * 
	 * 0 - geografia // azul 1- deportes // naranja 2- arte // marron 3 -
	 * ciencias // verde 4- espectaculos // rosa 5- historia // amarillo
	 * 
	 */
}
