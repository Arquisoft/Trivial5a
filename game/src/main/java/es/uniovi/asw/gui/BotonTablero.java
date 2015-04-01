package es.uniovi.asw.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import org.pushingpixels.substance.internal.utils.ButtonBackgroundDelegate;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.model.Category;

public class BotonTablero extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Game juego;
	Category categoria;
	
	Color color;
	int id_boton;
	boolean quesito;
	
	public BotonTablero(int id, final Game juego){
		super();
		this.juego = juego;
		this.setIgnoreRepaint(true);
		id_boton = id;					// con este campo se relaciona con la logica
		this.setText(String.valueOf(id));
		this.setBackground(pintarBoton(id));
		this.repaint();
		isQuesito(id);
		
		this.setActionCommand(String.valueOf(id)); // el nombre del boton, pero hay veces que java no permite usarlo al generarse de forma dinamica
		this.setEnabled(false);	// por defecto todos desactivados hasta que se tire el dado
		
		
		// Si se solapan los botones en pantallas mas pequeñas reducid un poco estos campos
		this.setPreferredSize(new Dimension(45, 45));
		
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPregunta vp = new VentanaPregunta(quesito, juego);
				vp.setVisible(true);
			}
		});
		
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
	public int getId_boton() {
		return id_boton;
	}
	public void setId_boton(int id_boton) {
		this.id_boton = id_boton;
	}
	public boolean isQuesito() {
		return quesito;
	}
	
	private Color pintarBoton(int bt) { // auxiliar para generar botones
		switch(bt%6) {
		case 0:
			color = new Color(255,51,204); //pink
			return color;
		case 1:
			color = new Color(255,255,153); //orange
			return color;
		case 2: 
			color = new Color(255,140,0);   //yellow
			return color;
		case 3:
			color = new Color(30,144,255);  //blue
			return color;
		case 4:
			color = new Color(156, 93, 82); //brown
			return color;
		case 5:
			color = new Color(0, 153, 51);   //green
			return color;
		}
		return null;
	}
	
	private void isQuesito(int bt) {
		if(bt%5 == 0) { //(bt%numcat-1)
			this.quesito = true;
			this.setText("X"); // para trabajar de forma mas sencilla con la interfaz en desarrollo, luego borrar
		}
	}

}
