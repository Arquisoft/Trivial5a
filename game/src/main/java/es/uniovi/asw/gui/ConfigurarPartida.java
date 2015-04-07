package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.model.User;

public class ConfigurarPartida extends JFrame {

	private static final long serialVersionUID = 1L;

	private Game juego;

	private JPanel contentPane;

	private JPanel pnNorte;

	private JPanel pnCentro;

	private JPanel pnSeleccionJugadores;

	private JPanel pnJugadores;

	private JPanel pnJ3;

	private JRadioButton rb2;

	private JRadioButton rb3;

	private JRadioButton rb4;

	private JRadioButton rb5;

	private JRadioButton rb6;

	private JLabel lblConfiguracin;

	private JLabel lblLogin3;

	private JTextField txLogin3;

	private JLabel lblPass3;

	private JPasswordField txPass3;

	private JPanel pnJ1;

	private JLabel lblLogin1;

	private JTextField txLogin1;

	private JLabel lblPass1;

	private JPasswordField txPass1;

	private JPanel pnJ2;

	private JLabel lblLogin2;

	private JTextField txLogin2;

	private JLabel lblPass2;

	private JPasswordField txPass2;

	private JPanel pnJ4;

	private JLabel lblLogin4;

	private JTextField txLogin4;

	private JLabel lblPass4;

	private JPasswordField txPass4;

	private JPanel pnJ6;

	private JLabel lblLogin6;

	private JTextField txLogin6;

	private JLabel lblPass6;

	private JPasswordField txPass6;

	private JPanel pnJ5;

	private JLabel lblLogin5;

	private JTextField txLogin5;

	private JLabel lblPass5;

	private JPasswordField txPass5;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JPanel pnBotones;

	private JButton btnContinuar;

	private JLabel lblSubt;

	private List<String> logins;

	public ConfigurarPartida(Game juego) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConfigurarPartida.class
						.getResource("/es/uniovi/asw/gui/img/iconoPeque.png")));

		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Trivial 5A - Configurar jugadores");
		this.juego = juego;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 15, 5, 15));
		contentPane.setLayout(new BorderLayout(30, 20));
		setContentPane(contentPane);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);

		estadoPanel(getPnJ1(), true);
		estadoPanel(getPnJ2(), true);
		estadoPanel(getPnJ3(), false);
		estadoPanel(getPnJ4(), false);
		estadoPanel(getPnJ5(), false);
		estadoPanel(getPnJ6(), false);
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
	}

	/**
	 * Devuelve el valor de pnNorte
	 * 
	 * @return pnNorte
	 */
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setLayout(new GridLayout(0, 1, 0, 0));
			pnNorte.add(getLblConfiguracin());
			pnNorte.add(getLblSubt());
		}
		return pnNorte;
	}

	/**
	 * Devuelve el valor de pnCentro
	 * 
	 * @return pnCentro
	 */
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new BorderLayout(0, 10));
			pnCentro.add(getPnSeleccionJugadores(), BorderLayout.NORTH);
			pnCentro.add(getPnJugadores(), BorderLayout.CENTER);
		}
		return pnCentro;
	}

	/**
	 * Devuelve el valor de pnSeleccionJugadores
	 * 
	 * @return pnSeleccionJugadores
	 */
	private JPanel getPnSeleccionJugadores() {
		if (pnSeleccionJugadores == null) {
			pnSeleccionJugadores = new JPanel();
			pnSeleccionJugadores.setBorder(new TitledBorder(null,
					"Selecciona el n\u00FAmero de jugadores",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnSeleccionJugadores.add(getRb2());
			pnSeleccionJugadores.add(getRb3());
			pnSeleccionJugadores.add(getRb4());
			pnSeleccionJugadores.add(getRb5());
			pnSeleccionJugadores.add(getRb6());
		}
		return pnSeleccionJugadores;
	}

	/**
	 * Devuelve el valor de pnJugadores
	 * 
	 * @return pnJugadores
	 */
	private JPanel getPnJugadores() {
		if (pnJugadores == null) {
			pnJugadores = new JPanel();
			pnJugadores.setLayout(new GridLayout(3, 2, 20, 20));
			pnJugadores.add(getPnJ1());
			pnJugadores.add(getPnJ2());
			pnJugadores.add(getPnJ3());
			pnJugadores.add(getPnJ4());
			pnJugadores.add(getPnJ5());
			pnJugadores.add(getPnJ6());
		}
		return pnJugadores;
	}

	/**
	 * Devuelve el valor de pnJ3
	 * 
	 * @return pnJ3
	 */
	private JPanel getPnJ3() {
		if (pnJ3 == null) {
			pnJ3 = new JPanel();
			pnJ3.setBorder(new TitledBorder(null, "Jugador 3",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnJ3.setLayout(new GridLayout(2, 2, 0, 5));
			pnJ3.add(getLblLogin3());
			pnJ3.add(getTxLogin3());
			pnJ3.add(getLblPass3());
			pnJ3.add(getTxPass3());
		}
		return pnJ3;
	}

	/**
	 * Devuelve el valor de rb2
	 * 
	 * @return rb2
	 */
	private JRadioButton getRb2() {
		if (rb2 == null) {
			rb2 = new JRadioButton("2");
			rb2.setSelected(true);
			rb2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb2);
		}
		return rb2;
	}

	/**
	 * Devuelve el valor de rb3
	 * 
	 * @return rb3
	 */
	private JRadioButton getRb3() {
		if (rb3 == null) {
			rb3 = new JRadioButton("3");
			rb3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb3);
		}
		return rb3;
	}

	/**
	 * Devuelve el valor de rb4
	 * 
	 * @return rb4
	 */
	private JRadioButton getRb4() {
		if (rb4 == null) {
			rb4 = new JRadioButton("4");
			rb4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb4);
		}
		return rb4;
	}

	/**
	 * Devuelve el valor de rb5
	 * 
	 * @return rb5
	 */
	private JRadioButton getRb5() {
		if (rb5 == null) {
			rb5 = new JRadioButton("5");
			rb5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb5);
		}
		return rb5;
	}

	/**
	 * Devuelve el valor de rb6
	 * 
	 * @return rb6
	 */
	private JRadioButton getRb6() {
		if (rb6 == null) {
			rb6 = new JRadioButton("6");
			rb6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb6);
		}
		return rb6;
	}

	/**
	 * Devuelve el valor de lblConfiguracin
	 * 
	 * @return lblConfiguracin
	 */
	private JLabel getLblConfiguracin() {
		if (lblConfiguracin == null) {
			lblConfiguracin = new JLabel("Configuraci\u00F3n de la partida ");
			lblConfiguracin.setHorizontalAlignment(SwingConstants.CENTER);
			lblConfiguracin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return lblConfiguracin;
	}

	/**
	 * Devuelve el valor de lblLogin3
	 * 
	 * @return lblLogin3
	 */
	private JLabel getLblLogin3() {
		if (lblLogin3 == null) {
			lblLogin3 = new JLabel("Login");
			lblLogin3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblLogin3;
	}

	/**
	 * Devuelve el valor de txLogin3
	 * 
	 * @return txLogin3
	 */
	private JTextField getTxLogin3() {
		if (txLogin3 == null) {
			txLogin3 = new JTextField();
			txLogin3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txLogin3.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txLogin3.selectAll();
				}
			});
			txLogin3.setText("Login");
			txLogin3.setColumns(10);
		}
		return txLogin3;
	}

	/**
	 * Devuelve el valor de lblPass3
	 * 
	 * @return lblPass3
	 */
	private JLabel getLblPass3() {
		if (lblPass3 == null) {
			lblPass3 = new JLabel("Password: ");
			lblPass3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPass3;
	}

	/**
	 * Devuelve el valor de txPass3
	 * 
	 * @return txPass3
	 */
	private JPasswordField getTxPass3() {
		if (txPass3 == null) {
			txPass3 = new JPasswordField();
			txPass3.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPass3.selectAll();
				}
			});
			txPass3.setText("Password");
			txPass3.setColumns(10);
		}
		return txPass3;
	}

	/**
	 * Devuelve el valor de pnJ1
	 * 
	 * @return pnJ1
	 */
	private JPanel getPnJ1() {
		if (pnJ1 == null) {
			pnJ1 = new JPanel();
			pnJ1.setBorder(new TitledBorder(null, "Jugador 1",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnJ1.setLayout(new GridLayout(2, 2, 0, 5));
			pnJ1.add(getLblLogin1());
			pnJ1.add(getTxLogin1());
			pnJ1.add(getLblPass1());
			pnJ1.add(getTxPass1());
		}
		return pnJ1;
	}

	/**
	 * Devuelve el valor de lblLogin1
	 * 
	 * @return lblLogin1
	 */
	private JLabel getLblLogin1() {
		if (lblLogin1 == null) {
			lblLogin1 = new JLabel("Login");
			lblLogin1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblLogin1;
	}

	/**
	 * Devuelve el valor de txLogin1
	 * 
	 * @return txLogin1
	 */
	private JTextField getTxLogin1() {
		if (txLogin1 == null) {
			txLogin1 = new JTextField();
			txLogin1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txLogin1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txLogin1.selectAll();
				}
			});
			txLogin1.setText("Login");
			txLogin1.setColumns(10);
		}
		return txLogin1;
	}

	/**
	 * Devuelve el valor de lblPass1
	 * 
	 * @return lblPass1
	 */
	private JLabel getLblPass1() {
		if (lblPass1 == null) {
			lblPass1 = new JLabel("Password: ");
			lblPass1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPass1;
	}

	/**
	 * Devuelve el valor de txPass1
	 * 
	 * @return txPass1
	 */
	private JPasswordField getTxPass1() {
		if (txPass1 == null) {
			txPass1 = new JPasswordField();
			txPass1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPass1.selectAll();
				}
			});
			txPass1.setText("Password");
			txPass1.setColumns(10);
		}
		return txPass1;
	}

	/**
	 * Devuelve el valor de pnJ2
	 * 
	 * @return pnJ2
	 */
	private JPanel getPnJ2() {
		if (pnJ2 == null) {
			pnJ2 = new JPanel();
			pnJ2.setBorder(new TitledBorder(null, "Jugador 2",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnJ2.setLayout(new GridLayout(2, 2, 0, 5));
			pnJ2.add(getLblLogin2());
			pnJ2.add(getTxLogin2());
			pnJ2.add(getLblPass2());
			pnJ2.add(getTxPass2());
		}
		return pnJ2;
	}

	/**
	 * Devuelve el valor de lblLogin2
	 * 
	 * @return lblLogin2
	 */
	private JLabel getLblLogin2() {
		if (lblLogin2 == null) {
			lblLogin2 = new JLabel("Login");
			lblLogin2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblLogin2;
	}

	/**
	 * Devuelve el valor de txLogin2
	 * 
	 * @return txLogin2
	 */
	private JTextField getTxLogin2() {
		if (txLogin2 == null) {
			txLogin2 = new JTextField();
			txLogin2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txLogin2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					txLogin2.selectAll();
				}
			});
			txLogin2.setText("Login");
			txLogin2.setColumns(10);
		}
		return txLogin2;
	}

	/**
	 * Devuelve el valor de lblPass2
	 * 
	 * @return lblPass2
	 */
	private JLabel getLblPass2() {
		if (lblPass2 == null) {
			lblPass2 = new JLabel("Password: ");
			lblPass2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPass2;
	}

	/**
	 * Devuelve el valor de txPass2
	 * 
	 * @return txPass2
	 */
	private JPasswordField getTxPass2() {
		if (txPass2 == null) {
			txPass2 = new JPasswordField();
			txPass2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPass2.selectAll();
				}
			});
			txPass2.setText("Password");
			txPass2.setColumns(10);
		}
		return txPass2;
	}

	/**
	 * Devuelve el valor de pnJ4
	 * 
	 * @return pnJ4
	 */
	private JPanel getPnJ4() {
		if (pnJ4 == null) {
			pnJ4 = new JPanel();
			pnJ4.setBorder(new TitledBorder(null, "Jugador 4",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnJ4.setLayout(new GridLayout(2, 2, 0, 5));
			pnJ4.add(getLblLogin4());
			pnJ4.add(getTxLogin4());
			pnJ4.add(getLblPass4());
			pnJ4.add(getTxPass4());
		}
		return pnJ4;
	}

	/**
	 * Devuelve el valor de lblLogin4
	 * 
	 * @return lblLogin4
	 */
	private JLabel getLblLogin4() {
		if (lblLogin4 == null) {
			lblLogin4 = new JLabel("Login");
			lblLogin4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblLogin4;
	}

	/**
	 * Devuelve el valor de txLogin4
	 * 
	 * @return txLogin4
	 */
	private JTextField getTxLogin4() {
		if (txLogin4 == null) {
			txLogin4 = new JTextField();
			txLogin4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txLogin4.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txLogin4.selectAll();
				}
			});
			txLogin4.setText("Login");
			txLogin4.setColumns(10);
		}
		return txLogin4;
	}

	/**
	 * Devuelve el valor de lblPass4
	 * 
	 * @return lblPass4
	 */
	private JLabel getLblPass4() {
		if (lblPass4 == null) {
			lblPass4 = new JLabel("Password: ");
			lblPass4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPass4;
	}

	/**
	 * Devuelve el valor de txPass4
	 * 
	 * @return txPass4
	 */
	private JPasswordField getTxPass4() {
		if (txPass4 == null) {
			txPass4 = new JPasswordField();
			txPass4.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPass4.selectAll();
				}
			});
			txPass4.setText("Password");
			txPass4.setColumns(10);
		}
		return txPass4;
	}

	/**
	 * Devuelve el valor de pnJ6
	 * 
	 * @return pnJ6
	 */
	private JPanel getPnJ6() {
		if (pnJ6 == null) {
			pnJ6 = new JPanel();
			pnJ6.setBorder(new TitledBorder(null, "Jugador 6",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnJ6.setLayout(new GridLayout(2, 2, 0, 5));
			pnJ6.add(getLblLogin6());
			pnJ6.add(getTxLogin6());
			pnJ6.add(getLblPass6());
			pnJ6.add(getTxPass6());
		}
		return pnJ6;
	}

	/**
	 * Devuelve el valor de lblLogin6
	 * 
	 * @return lblLogin6
	 */
	private JLabel getLblLogin6() {
		if (lblLogin6 == null) {
			lblLogin6 = new JLabel("Login");
			lblLogin6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblLogin6;
	}

	/**
	 * Devuelve el valor de txLogin6
	 * 
	 * @return txLogin6
	 */
	private JTextField getTxLogin6() {
		if (txLogin6 == null) {
			txLogin6 = new JTextField();
			txLogin6.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txLogin6.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txLogin6.selectAll();
				}
			});
			txLogin6.setText("Login");
			txLogin6.setColumns(10);
		}
		return txLogin6;
	}

	/**
	 * Devuelve el valor de lblPass6
	 * 
	 * @return lblPass6
	 */
	private JLabel getLblPass6() {
		if (lblPass6 == null) {
			lblPass6 = new JLabel("Password: ");
			lblPass6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPass6;
	}

	/**
	 * Devuelve el valor de txPass6
	 * 
	 * @return txPass6
	 */
	private JPasswordField getTxPass6() {
		if (txPass6 == null) {
			txPass6 = new JPasswordField();
			txPass6.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPass6.selectAll();
				}
			});
			txPass6.setText("Password");
			txPass6.setColumns(10);
		}
		return txPass6;
	}

	/**
	 * Devuelve el valor de pnJ5
	 * 
	 * @return pnJ5
	 */
	private JPanel getPnJ5() {
		if (pnJ5 == null) {
			pnJ5 = new JPanel();
			pnJ5.setBorder(new TitledBorder(null, "Jugador 5",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
							Font.PLAIN, 14), Color.GRAY));
			pnJ5.setLayout(new GridLayout(2, 2, 0, 5));
			pnJ5.add(getLblLogin5());
			pnJ5.add(getTxLogin5());
			pnJ5.add(getLblPass5());
			pnJ5.add(getTxPass5());
		}
		return pnJ5;
	}

	/**
	 * Devuelve el valor de lblLogin5
	 * 
	 * @return lblLogin5
	 */
	private JLabel getLblLogin5() {
		if (lblLogin5 == null) {
			lblLogin5 = new JLabel("Login");
			lblLogin5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblLogin5;
	}

	/**
	 * Devuelve el valor de txLogin5
	 * 
	 * @return txLogin5
	 */
	private JTextField getTxLogin5() {
		if (txLogin5 == null) {
			txLogin5 = new JTextField();
			txLogin5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txLogin5.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txLogin5.selectAll();
				}
			});
			txLogin5.setText("Login");
			txLogin5.setColumns(10);
		}
		return txLogin5;
	}

	/**
	 * Devuelve el valor de lblPass5
	 * 
	 * @return lblPass5
	 */
	private JLabel getLblPass5() {
		if (lblPass5 == null) {
			lblPass5 = new JLabel("Password: ");
			lblPass5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblPass5;
	}

	/**
	 * Devuelve el valor de txPass5
	 * 
	 * @return txPass5
	 */
	private JPasswordField getTxPass5() {
		if (txPass5 == null) {
			txPass5 = new JPasswordField();
			txPass5.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txPass5.selectAll();
				}
			});
			txPass5.setText("Password");
			txPass5.setColumns(10);
		}
		return txPass5;
	}

	// LÓGICA

	/**
	 * Método para invocar en los action de los radiobutton Activa o desactiva
	 * los paneles en funcion del número de jugadores seleccionados
	 */
	private void gestionarPaneles() {
		int numJugadores = 0;

		// Saber el numero de jugadores seleccionado
		Component[] rbs = getPnSeleccionJugadores().getComponents();
		for (int i = 0; i < rbs.length; i++) {
			JRadioButton rb = (JRadioButton) rbs[i];
			if (rb.isSelected()) {
				numJugadores = Integer.valueOf(rb.getText());
			}
		}

		// Activar
		JPanel[] paneles = new JPanel[] { pnJ1, pnJ2, pnJ3, pnJ4, pnJ5, pnJ6 };
		for (int i = 0; i < paneles.length; i++) {
			if (i < numJugadores)
				estadoPanel(paneles[i], true);
			else
				estadoPanel(paneles[i], false);
		}
	}

	/**
	 * Activa o desactiva todos los componentes de un panel Método auxiliar para
	 * el gestionarPaneles general que se invoca en los rb
	 * 
	 * @param panel
	 *            - panel a modificar
	 * @param activar
	 *            - true activado, false desactivado
	 */
	private void estadoPanel(JPanel panel, boolean activar) {
		panel.setEnabled(false);
		Component[] componentes = panel.getComponents();
		for (int i = 0; i < componentes.length; i++) {
			componentes[i].setEnabled(activar);
			if (!activar) { // Eliminar los datos si vamos a quitar un jugador
				if (componentes[i] instanceof JTextField) {
					((JTextField) componentes[i]).setText("");
				}
			}
		}
	}

	/**
	 * Devuelve el número de jugadores
	 * 
	 * @return numJugadores
	 */
	public int numJugadores() {
		int numJugadores = 0;

		// Saber el numero de jugadores seleccionado
		Component[] rbs = getPnSeleccionJugadores().getComponents();
		for (int i = 0; i < rbs.length; i++) {
			JRadioButton rb = (JRadioButton) rbs[i];
			if (rb.isSelected()) {
				numJugadores = Integer.valueOf(rb.getText());
			}
		}
		return numJugadores;
	}

	/**
	 * Comprueba si los campos están vacíos
	 * 
	 * @return
	 */
	private boolean comprobarCamposVacios() {
		int numJugadores = numJugadores();
		logins = new ArrayList<String>();
		switch (numJugadores) {

		case 2: {
			if (!txLogin1.getText().equals("")
					&& txPass1.getPassword().length != 0
					&& !txLogin2.getText().equals("")
					&& txPass2.getPassword().length != 0) {

				logins.add(txLogin1.getText());
				logins.add(txLogin2.getText());
				return true;
			}
		}

		case 3: {
			if (!txLogin1.getText().equals("")
					&& txPass1.getPassword().length != 0
					&& !txLogin2.getText().equals("")
					&& txPass2.getPassword().length != 0
					&& !txLogin3.getText().equals("")
					&& txPass3.getPassword().length != 0) {

				logins.add(txLogin1.getText());
				logins.add(txLogin2.getText());
				logins.add(txLogin3.getText());
				return true;
			}
		}

		case 4: {
			if (!txLogin1.getText().equals("")
					&& txPass1.getPassword().length != 0
					&& !txLogin2.getText().equals("")
					&& txPass2.getPassword().length != 0
					&& !txLogin3.getText().equals("")
					&& txPass3.getPassword().length != 0
					&& !txLogin4.getText().equals("")
					&& txPass4.getPassword().length != 0) {

				logins.add(txLogin1.getText());
				logins.add(txLogin2.getText());
				logins.add(txLogin3.getText());
				logins.add(txLogin4.getText());
				return true;
			}
		}

		case 5: {
			if (!txLogin1.getText().equals("")
					&& txPass1.getPassword().length != 0
					&& !txLogin2.getText().equals("")
					&& txPass2.getPassword().length != 0
					&& !txLogin3.getText().equals("")
					&& txPass3.getPassword().length != 0
					&& !txLogin4.getText().equals("")
					&& txPass4.getPassword().length != 0
					&& !txLogin5.getText().equals("")
					&& txPass5.getPassword().length != 0) {

				logins.add(txLogin1.getText());
				logins.add(txLogin2.getText());
				logins.add(txLogin3.getText());
				logins.add(txLogin4.getText());
				logins.add(txLogin5.getText());
				return true;
			}
		}

		case 6: {
			if (!txLogin1.getText().equals("")
					&& txPass1.getPassword().length != 0
					&& !txLogin2.getText().equals("")
					&& txPass2.getPassword().length != 0
					&& !txLogin3.getText().equals("")
					&& txPass3.getPassword().length != 0
					&& !txLogin4.getText().equals("")
					&& txPass4.getPassword().length != 0
					&& !txLogin5.getText().equals("")
					&& txPass5.getPassword().length != 0
					&& !txLogin6.getText().equals("")
					&& txPass6.getPassword().length != 0) {

				logins.add(txLogin1.getText());
				logins.add(txLogin2.getText());
				logins.add(txLogin3.getText());
				logins.add(txLogin4.getText());
				logins.add(txLogin5.getText());
				logins.add(txLogin6.getText());
				return true;
			}
		}
		}

		return false;
	}

	/**
	 * Devuelve el valor de logins
	 * 
	 * @return logins
	 */

	public List<String> getLogins() {
		return logins;
	}

	/**
	 * Devuelve el valor de pnBotones
	 * 
	 * @return pnBotones
	 */
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtnContinuar());
		}
		return pnBotones;
	}

	/**
	 * Devuelve el valor de btnContinuar
	 * 
	 * @return btnContinuar
	 */
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			final ConfigurarPartida cp = this;
			btnContinuar = new JButton("Continuar");
			btnContinuar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (comprobarCamposVacios()) {
						try {
							crearUsuarios();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									e1.getMessage(), "Bienvenido",
									JOptionPane.PLAIN_MESSAGE);
							return;
						}
						JOptionPane.showMessageDialog(null,
								"¡Comienza el juego!", "Bienvenido",
								JOptionPane.PLAIN_MESSAGE);
						Juego j = new Juego(cp, juego);
						j.setVisible(true);
						cp.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null,
								"Hay campos sin rellenar");
					}
				}
			});
		}
		return btnContinuar;
	}

	/**
	 * Devuelve el valor de lblSubt
	 * 
	 * @return lblSubt
	 */
	private JLabel getLblSubt() {
		if (lblSubt == null) {
			lblSubt = new JLabel(
					"Seleccione el n\u00FAmero de jugadores e introduzca el login y password de cada uno de ellos");
			lblSubt.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubt.setFont(new Font("Arial", Font.PLAIN, 11));
		}
		return lblSubt;
	}

	/**
	 * Se cogen los formularios y se crean los usuarios
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private void crearUsuarios() throws Exception {
		User user1, user2, user3, user4, user5, user6;
		List<User> usuarios = new ArrayList<User>();
		if (rb2.isSelected()) {
			user1 = new User();
			user1.setLogin(txLogin1.getText());
			user1.setPassword(txPass1.getText());
			user2 = new User();
			user2.setLogin(txLogin2.getText());
			user2.setPassword(txPass2.getText());
			usuarios.add(user1);
			usuarios.add(user2);

		} else if (rb3.isSelected()) {
			user3 = new User();
			user3.setLogin(txLogin3.getText());
			user3.setPassword(txPass3.getText());
			usuarios.add(user3);

		} else if (rb4.isSelected()) {
			user4 = new User();
			user4.setLogin(txLogin4.getText());
			user4.setPassword(txPass4.getText());
			usuarios.add(user4);

		} else if (rb5.isSelected()) {
			user5 = new User();
			user5.setLogin(txLogin5.getText());
			user5.setPassword(txPass5.getText());
			usuarios.add(user5);

		} else if (rb6.isSelected()) {
			user6 = new User();
			user6.setLogin(txLogin6.getText());
			user6.setPassword(txPass6.getText());
			usuarios.add(user6);
		}
		if (usuarioUnico(usuarios))
			juego.validarTodosUsuarios(usuarios);
		else
			throw new Exception("Usuario repetido");
	}

	/**
	 * Comprobar si un usuario es único (no usuarios repetidos)
	 * 
	 * @return
	 */
	public boolean usuarioUnico(List<User> usuarios) {
		for (int i = 0; i < usuarios.size(); i++)
			for (int j = i + 1; j <= usuarios.size() - 1; j++) {
				if (usuarios.get(i).equals(usuarios.get(j)))
					return false;
			}
		return true;
	}
}
