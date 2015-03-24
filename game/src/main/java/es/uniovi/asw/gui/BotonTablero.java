package es.uniovi.asw.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import es.uniovi.asw.model.Category;

public class BotonTablero extends JButton {
	
	Category categoria;
	Color color;
	int id_boton;
	boolean quesito;
	
	public BotonTablero(int id){
		super();
		id_boton = id;
		this.setText(String.valueOf(id));
		pintarBoton(id);
		isQuesito(id);
		
		this.setActionCommand(String.valueOf(id));
		this.setEnabled(false);		
		this.setPreferredSize(new Dimension(50,40));
		
	}
	public Category getCategoria() {
		return categoria;
	}
	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getId_boton() {
		return id_boton;
	}
	public void setId_boton(int id_boton) {
		this.id_boton = id_boton;
	}
	public boolean isQuesito() {
		return quesito;
	}
	public void setQuesito(boolean quesito) {
		this.quesito = quesito;
	}
	
	private Color pintarBoton(int bt) { // auxiliar para generar botones
		switch(bt%6) {
		case 0:
			return Color.pink;
		case 1:
			return Color.yellow;
		case 2: 
			return Color.orange;
		case 3:
			return Color.blue;
		case 4:
			return new Color(156, 93, 82);
		case 5:
			return Color.green;
		}
		return null;
	}
	
	private void isQuesito(int bt) {
		if(bt%6 == 0)
			this.setText(bt+"X");
	}

}
