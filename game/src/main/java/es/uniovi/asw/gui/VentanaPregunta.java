package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.bussines.QuestionManager;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ButtonGroup;

public class VentanaPregunta extends JDialog {
	
	private boolean quesito;
	private Game juego;
	private Juego pantalla;
	private int categoria;
	private Question pregunta;
	private String[] respuestas;
	private int boton;

	private JPanel contentPane;
	private JPanel pnPreguntas;
	private JRadioButton rdbtnOpc1;
	private JRadioButton rdbtnOpc3;
	private JRadioButton rdbtnOpc2;
	private JRadioButton rdbtnOpc4;
	private JLabel lblPregunta;
	private JButton btnResponder;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public VentanaPregunta(boolean quesito, int categoria, Juego pantalla, Game juego, int boton) {
		this.categoria = categoria;
		this.quesito = quesito;
		this.pantalla = pantalla;
		this.juego = juego;
		List<Category> cats = juego.getQuestionManager().cargarTablero();
		pregunta = cats.get(categoria).askQuestion();
		respuestas = pregunta.getAllAnswers();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPregunta.class.getResource("/es/uniovi/asw/gui/img/iconoPeque.png")));
		
		this.quesito = quesito; // para gestionar que hay que hacer con la respuesta
		
		this.setLocationRelativeTo(null);
		this.setModal(true); // para que no se pueda pulsar la ventana del tablero hasta que no respondas
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // para inhabilitar el boton de cerrar y que no puedan saltarse la pregunta
		
		setBounds(100, 100, 604, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);
		contentPane.add(getLblPregunta(), BorderLayout.NORTH);
		contentPane.add(getPnPreguntas(), BorderLayout.CENTER);
		contentPane.add(getBtnResponder(), BorderLayout.SOUTH);
	}

	private JPanel getPnPreguntas() {
		if (pnPreguntas == null) {
			pnPreguntas = new JPanel();
			pnPreguntas.setLayout(new GridLayout(4, 1, 0, 0));
			pnPreguntas.add(getRdbtnOpc1());
			pnPreguntas.add(getRdbtnOpc2());
			pnPreguntas.add(getRdbtnOpc3());
			pnPreguntas.add(getRdbtnOpc4());
		}
		return pnPreguntas;
	}
	private JRadioButton getRdbtnOpc1() {
		if (rdbtnOpc1 == null) {
			rdbtnOpc1 = new JRadioButton("");
			rdbtnOpc1.setActionCommand("0");
			rdbtnOpc1.setText("<html>"+respuestas[0]+"</html>");
			buttonGroup.add(rdbtnOpc1);
			rdbtnOpc1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
		}
		return rdbtnOpc1;
	}
	private JRadioButton getRdbtnOpc3() {
		if (rdbtnOpc3 == null) {
			rdbtnOpc3 = new JRadioButton("");
			rdbtnOpc3.setActionCommand("2");
			rdbtnOpc3.setText("<html>"+respuestas[2]+"</html>");
			buttonGroup.add(rdbtnOpc3);
			
			rdbtnOpc3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc3;
	}
	private JRadioButton getRdbtnOpc2() {
		if (rdbtnOpc2 == null) {
			rdbtnOpc2 = new JRadioButton("Opc2");
			rdbtnOpc2.setActionCommand("1");
			rdbtnOpc2.setText("<html>"+respuestas[1]+"</html>");
			buttonGroup.add(rdbtnOpc2);
			
			rdbtnOpc2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc2;
	}
	private JRadioButton getRdbtnOpc4() {
		if (rdbtnOpc4 == null) {
			rdbtnOpc4 = new JRadioButton("Opc4");
			rdbtnOpc4.setText("<html>"+respuestas[3]+"</html>");
			rdbtnOpc4.setActionCommand("3");
			buttonGroup.add(rdbtnOpc4);
			
			rdbtnOpc4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc4;
	}
	private JLabel getLblPregunta() {
			
		if (lblPregunta == null) {
			lblPregunta = new JLabel();
			lblPregunta.setText("<html>"+pregunta.getQuery()+"</html>");
			lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblPregunta;
	}
	private JButton getBtnResponder() {
		if (btnResponder == null) {
			
			btnResponder = new JButton("Responder");
			btnResponder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					User usuarioActivo = juego.getUsuarioActivo();
					
										
					if(!respuesta()) {
						JOptionPane.showMessageDialog(null, "Has fallado. Pierdes el turno :(");
						juego.falla(pregunta);
						pantalla.pintarPosicion(juego.getUsuarioActivo().getPosicion());
					}
					else {
						if(quesito) {
							JOptionPane.showMessageDialog(null, "¡Respuesta correcta! Has ganado un quesito");
							pantalla.getTable().setAlignmentX(CENTER_ALIGNMENT);
							pantalla.getTable().setAlignmentY(CENTER_ALIGNMENT);
							pantalla.getTable().getModel().setValueAt("X", (int)juego.getUsuarioActivo().getId(), categoria+2);
							juego.acierta(pregunta, true);
						} 
						else {
							JOptionPane.showMessageDialog(null, "¡Respuesta correcta!");
							juego.acierta(pregunta, false);
						}
					}
					
					pantalla.desactivarBotones();
					pantalla.getBtDado().setEnabled(true);
					dispose();
					
				}
			});
		}
		return btnResponder;
	}
	
	public boolean respuesta() {
		int respuestaSeleccionada = -1;
		Component[] botones = (Component[]) pnPreguntas.getComponents();
		for(int i=0; i<botones.length; i++) {
			JRadioButton bt = (JRadioButton) botones[i];
			if(bt.isSelected()) {
				respuestaSeleccionada = Integer.valueOf(bt.getActionCommand());
				if( (pregunta.getCorrectAnswer()).equals(respuestas[respuestaSeleccionada]) ) {
					return true;
				}
			}
		}
		return false;
	}
	
}
