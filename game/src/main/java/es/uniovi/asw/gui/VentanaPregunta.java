package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaPregunta extends JDialog {
	
	private boolean quesito;

	private JPanel contentPane;
	private JPanel pnPreguntas;
	private JRadioButton rdbtnOpc1;
	private JRadioButton rdbtnOpc3;
	private JRadioButton rdbtnOpc2;
	private JRadioButton rdbtnOpc4;
	private JLabel lblPregunta;
	private JButton btnResponder;

	public VentanaPregunta(boolean quesito) {
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
			rdbtnOpc1 = new JRadioButton("<html>esdkjhf asdhflksdhf laksdfhlksadf hdiufbdjk vdnvo este es un texto tan tan tan grande que no entra en una sola linea<html>");
			rdbtnOpc1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
		}
		return rdbtnOpc1;
	}
	private JRadioButton getRdbtnOpc3() {
		if (rdbtnOpc3 == null) {
			rdbtnOpc3 = new JRadioButton("<html>Aseguraos de que el texto de la opción va entre las etiquetas html para que divida la linea si no entra<html>");
			rdbtnOpc3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc3;
	}
	private JRadioButton getRdbtnOpc2() {
		if (rdbtnOpc2 == null) {
			rdbtnOpc2 = new JRadioButton("Opc2");
			rdbtnOpc2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc2;
	}
	private JRadioButton getRdbtnOpc4() {
		if (rdbtnOpc4 == null) {
			rdbtnOpc4 = new JRadioButton("Opc4");
			rdbtnOpc4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc4;
	}
	private JLabel getLblPregunta() {
		if (lblPregunta == null) {
			lblPregunta = new JLabel("<html>El texto va entre etiquetas html por si no entra en una sola linea para que se divida de forma automatica<html>");
			lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblPregunta;
	}
	private JButton getBtnResponder() {
		if (btnResponder == null) {
			final VentanaPregunta vp = this;
			btnResponder = new JButton("Responder");
			btnResponder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					/**
					 * IF - respuesta incorrecta
					 * {
					 * 	JOptionPane.showMessageDialog(null, "Has fallado. Pierdes el turno");
						
					 * 
					 * }
					 * 	
					 * ELSE - i la respuesta es acertada
					 * 
					 * {
					 * 		if(quesito)
					 * 
					 * 		else
					 * 		
					 * }
					 * 
					 */
					
					vp.setVisible(false);
				}
			});
		}
		return btnResponder;
	}
	
}
