package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;

public class VentanaPregunta extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private int cuenta = 29, paso = 1;

	private Timer tiempo;

	private boolean quesito;

	private Game juego;

	private Juego pantalla;

	private int categoria;

	private Question pregunta;

	private String[] respuestas;

	private JPanel contentPane;

	private JPanel pnPreguntas;

	private JRadioButton rdbtnOpc1;

	private JRadioButton rdbtnOpc3;

	private JRadioButton rdbtnOpc2;

	private JRadioButton rdbtnOpc4;

	private JLabel lblPregunta;

	private JButton btnResponder;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JLabel labelCronometro;

	/**
	 * Crea la ventana de la pregunta
	 * 
	 * @param quesito
	 * @param categoria
	 * @param pantalla
	 * @param juego
	 * @param boton
	 */
	public VentanaPregunta(boolean quesito, int categoria, Juego pantalla,
			Game juego, int boton) {
		tiempo = new Timer(1000, this);
		tiempo.start();
		this.categoria = categoria;
		this.quesito = quesito;
		this.pantalla = pantalla;
		this.juego = juego;
		List<Category> cats = juego.getQuestionManager().cargarTablero();
		pregunta = cats.get(categoria).askQuestion();
		respuestas = pregunta.getAllAnswers();
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaPregunta.class
						.getResource("/es/uniovi/asw/gui/img/iconoPeque.png")));
		this.quesito = quesito;
		this.setLocationRelativeTo(null);
		this.setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 604, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);
		contentPane.add(getLblPregunta(), BorderLayout.NORTH);
		contentPane.add(getPnPreguntas(), BorderLayout.CENTER);
		contentPane.add(getBtnResponder(), BorderLayout.SOUTH);
		contentPane.add(getLabelCronometro(), BorderLayout.WEST);
	}

	/**
	 * Devuelve el valor de pnPreguntas
	 * 
	 * @return pnPreguntas
	 */
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

	/**
	 * Devuelve el valor de rdbtnOpc1
	 * 
	 * @return rdbtnOpc1
	 */
	private JRadioButton getRdbtnOpc1() {
		if (rdbtnOpc1 == null) {
			rdbtnOpc1 = new JRadioButton("");
			rdbtnOpc1.setActionCommand("0");
			rdbtnOpc1.setText("<html>" + respuestas[0] + "</html>");
			buttonGroup.add(rdbtnOpc1);
			rdbtnOpc1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc1;
	}

	/**
	 * Devuelve el valor de rdbtnOpc3
	 * 
	 * @return rdbtnOpc3
	 */
	private JRadioButton getRdbtnOpc3() {
		if (rdbtnOpc3 == null) {
			rdbtnOpc3 = new JRadioButton("");
			rdbtnOpc3.setActionCommand("2");
			rdbtnOpc3.setText("<html>" + respuestas[2] + "</html>");
			buttonGroup.add(rdbtnOpc3);
			rdbtnOpc3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc3;
	}

	/**
	 * Devuelve el valor de rdbtnOpc2
	 * 
	 * @return rdbtnOpc2
	 */
	private JRadioButton getRdbtnOpc2() {
		if (rdbtnOpc2 == null) {
			rdbtnOpc2 = new JRadioButton("Opc2");
			rdbtnOpc2.setActionCommand("1");
			rdbtnOpc2.setText("<html>" + respuestas[1] + "</html>");
			buttonGroup.add(rdbtnOpc2);
			rdbtnOpc2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc2;
	}

	/**
	 * Devuelve el valor de rdbtnOpc4
	 * 
	 * @return rdbtnOpc4
	 */
	private JRadioButton getRdbtnOpc4() {
		if (rdbtnOpc4 == null) {
			rdbtnOpc4 = new JRadioButton("Opc4");
			rdbtnOpc4.setText("<html>" + respuestas[3] + "</html>");
			rdbtnOpc4.setActionCommand("3");
			buttonGroup.add(rdbtnOpc4);
			rdbtnOpc4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return rdbtnOpc4;
	}

	/**
	 * Devuelve el valor de lblPregunta
	 * 
	 * @return lblPregunta
	 */
	private JLabel getLblPregunta() {
		if (lblPregunta == null) {
			lblPregunta = new JLabel();
			lblPregunta.setText("<html>" + pregunta.getQuery() + "</html>");
			lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblPregunta;
	}

	/**
	 * Devuelve el valor de btnResponder
	 * 
	 * @return btnResponder
	 */
	private JButton getBtnResponder() {
		if (btnResponder == null) {
			btnResponder = new JButton("Responder");
			btnResponder.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!respuesta()) {
						JOptionPane.showMessageDialog(null,
								"Has fallado. Pierdes el turno :(");
						juego.falla(pregunta);
						tiempo.stop();
						pantalla.pintarPosicion(juego.getUsuarioActivo()
								.getPosicion());
						pantalla.repintarBotonEstadistica();
					} else {
						if (quesito) {
							JOptionPane
									.showMessageDialog(null,
											"¡Respuesta correcta! Has ganado un quesito");
							pantalla.getTable().setAlignmentX(CENTER_ALIGNMENT);
							pantalla.getTable().setAlignmentY(CENTER_ALIGNMENT);
							juego.acierta(pregunta, true);
							pantalla.getTable()
									.getModel()
									.setValueAt(
											"OK",
											juego.getUsuarios().indexOf(
													juego.getUsuarioActivo()),
											categoria + 2);
							tiempo.stop();
							if(juego.terminarPartida())
							{
								pantalla.dispose();
								VentanaFinJuego vf= new VentanaFinJuego(juego);
							vf.setVisible(true);
							vf.setLocationRelativeTo(null);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"¡Respuesta correcta!");
							juego.acierta(pregunta, false);
							tiempo.stop();
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

	/**
	 * Devuelve true si la respuesta es correcta y false si es incorrecta
	 * 
	 * @return
	 */
	public boolean respuesta() {
		int respuestaSeleccionada = -1;
		Component[] botones = pnPreguntas.getComponents();
		for (int i = 0; i < botones.length; i++) {
			JRadioButton bt = (JRadioButton) botones[i];
			if (bt.isSelected()) {
				respuestaSeleccionada = Integer.valueOf(bt.getActionCommand());
				if ((pregunta.getCorrectAnswer())
						.equals(respuestas[respuestaSeleccionada])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Devuelve el valor de labelCronometro
	 * 
	 * @return labelCronometro
	 */
	private JLabel getLabelCronometro() {
		if (labelCronometro == null) {
			labelCronometro = new JLabel("");
			labelCronometro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return labelCronometro;
	}

	/**
	 * Comprueba si el tiempo está agotado y muestra mensaje una vez finalizado
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		labelCronometro.setText("" + cuenta);
		cuenta -= paso;
		if (cuenta == 0) {
			tiempo.stop();
			JOptionPane.showMessageDialog(null,
					"Tiempo agotado. Pierdes el turno :(");
			juego.falla(pregunta);
			pantalla.pintarPosicion(juego.getUsuarioActivo().getPosicion());
			pantalla.desactivarBotones();
			pantalla.getBtDado().setEnabled(true);
			dispose();
		}
	}
}
