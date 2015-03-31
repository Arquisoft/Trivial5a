package es.uniovi.asw.gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class ConfigurarPartida extends JFrame {

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
	private JLabel lblConfiguracinDeLa;
	private JLabel lblLogin3;
	private JTextField txLogin3;
	private JLabel lblPass3;
	private JTextField txPass3;
	private JPanel pnJ1;
	private JLabel lblLogin1;
	private JTextField txLogin1;
	private JLabel lblPass1;
	private JTextField txPass1;
	private JPanel pnJ2;
	private JLabel lblLogin2;
	private JTextField txLogin2;
	private JLabel lblPass2;
	private JTextField txPass2;
	private JPanel pnJ4;
	private JLabel lblLogin4;
	private JTextField txLogin4;
	private JLabel lblPass4;
	private JTextField txPass4;
	private JPanel pnJ6;
	private JLabel lblLogin6;
	private JTextField txLogin6;
	private JLabel lblPass6;
	private JTextField txPass6;
	private JPanel pnJ5;
	private JLabel lblLogin5;
	private JTextField txLogin5;
	private JLabel lblPass5;
	private JTextField txPass5;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnBotones;
	private JButton btnContinuar;
	
	


	/**
	 * Create the frame.
	 */
	public ConfigurarPartida() {
		
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 15, 5, 15));
		contentPane.setLayout(new BorderLayout(30, 30));
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

	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.add(getLblConfiguracinDeLa());
		}
		return pnNorte;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new BorderLayout(0, 20));
			pnCentro.add(getPnSeleccionJugadores(), BorderLayout.NORTH);
			pnCentro.add(getPnJugadores(), BorderLayout.CENTER);
		}
		return pnCentro;
	}
	private JPanel getPnSeleccionJugadores() {
		if (pnSeleccionJugadores == null) {
			pnSeleccionJugadores = new JPanel();
			pnSeleccionJugadores.setBorder(new TitledBorder(null, "Selecciona el n\u00FAmero de jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSeleccionJugadores.add(getRb2());
			pnSeleccionJugadores.add(getRb3());
			pnSeleccionJugadores.add(getRb4());
			pnSeleccionJugadores.add(getRb5());
			pnSeleccionJugadores.add(getRb6());
		}
		return pnSeleccionJugadores;
	}
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
	private JPanel getPnJ3() {
		if (pnJ3 == null) {
			pnJ3 = new JPanel();
			pnJ3.setBorder(new TitledBorder(null, "Jugador 3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJ3.setLayout(new GridLayout(2, 2, 0, 0));
			pnJ3.add(getLblLogin3());
			pnJ3.add(getTxLogin3());
			pnJ3.add(getLblPass3());
			pnJ3.add(getTxPass3());
		}
		return pnJ3;
	}
	private JRadioButton getRb2() {
		if (rb2 == null) {
			rb2 = new JRadioButton("2");
			rb2.setSelected(true);
			rb2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb2);
		}
		return rb2;
	}
	private JRadioButton getRb3() {
		if (rb3 == null) {
			rb3 = new JRadioButton("3");
			rb3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb3);
		}
		return rb3;
	}
	private JRadioButton getRb4() {
		if (rb4 == null) {
			rb4 = new JRadioButton("4");
			rb4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb4);
		}
		return rb4;
	}
	private JRadioButton getRb5() {
		if (rb5 == null) {
			rb5 = new JRadioButton("5");
			rb5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb5);
		}
		return rb5;
	}
	private JRadioButton getRb6() {
		if (rb6 == null) {
			rb6 = new JRadioButton("6");
			rb6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionarPaneles();
				}
			});
			buttonGroup.add(rb6);
		}
		return rb6;
	}
	private JLabel getLblConfiguracinDeLa() {
		if (lblConfiguracinDeLa == null) {
			lblConfiguracinDeLa = new JLabel("Configuraci\u00F3n de la partida - seleccionar num de jugadores y rellenar los datos..");
		}
		return lblConfiguracinDeLa;
	}
	private JLabel getLblLogin3() {
		if (lblLogin3 == null) {
			lblLogin3 = new JLabel("Login");
		}
		return lblLogin3;
	}
	private JTextField getTxLogin3() {
		if (txLogin3 == null) {
			txLogin3 = new JTextField();
			txLogin3.setText("Login");
			txLogin3.setColumns(10);
		}
		return txLogin3;
	}
	private JLabel getLblPass3() {
		if (lblPass3 == null) {
			lblPass3 = new JLabel("Password: ");
		}
		return lblPass3;
	}
	private JTextField getTxPass3() {
		if (txPass3 == null) {
			txPass3 = new JTextField();
			txPass3.setText("Password");
			txPass3.setColumns(10);
		}
		return txPass3;
	}
	private JPanel getPnJ1() {
		if (pnJ1 == null) {
			pnJ1 = new JPanel();
			pnJ1.setBorder(new TitledBorder(null, "Jugador 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJ1.setLayout(new GridLayout(2, 2, 0, 0));
			pnJ1.add(getLblLogin1());
			pnJ1.add(getTxLogin1());
			pnJ1.add(getLblPass1());
			pnJ1.add(getTxPass1());
		}
		return pnJ1;
	}
	private JLabel getLblLogin1() {
		if (lblLogin1 == null) {
			lblLogin1 = new JLabel("Login");
		}
		return lblLogin1;
	}
	private JTextField getTxLogin1() {
		if (txLogin1 == null) {
			txLogin1 = new JTextField();
			txLogin1.setText("Login");
			txLogin1.setColumns(10);
		}
		return txLogin1;
	}
	private JLabel getLblPass1() {
		if (lblPass1 == null) {
			lblPass1 = new JLabel("Password: ");
		}
		return lblPass1;
	}
	private JTextField getTxPass1() {
		if (txPass1 == null) {
			txPass1 = new JTextField();
			txPass1.setText("Password");
			txPass1.setColumns(10);
		}
		return txPass1;
	}
	private JPanel getPnJ2() {
		if (pnJ2 == null) {
			pnJ2 = new JPanel();
			pnJ2.setBorder(new TitledBorder(null, "Jugador2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJ2.setLayout(new GridLayout(2, 2, 0, 0));
			pnJ2.add(getLblLogin2());
			pnJ2.add(getTxLogin2());
			pnJ2.add(getLblPass2());
			pnJ2.add(getTxPass2());
		}
		return pnJ2;
	}
	private JLabel getLblLogin2() {
		if (lblLogin2 == null) {
			lblLogin2 = new JLabel("Login");
		}
		return lblLogin2;
	}
	private JTextField getTxLogin2() {
		if (txLogin2 == null) {
			txLogin2 = new JTextField();
			txLogin2.setText("Login");
			txLogin2.setColumns(10);
		}
		return txLogin2;
	}
	private JLabel getLblPass2() {
		if (lblPass2 == null) {
			lblPass2 = new JLabel("Password: ");
		}
		return lblPass2;
	}
	private JTextField getTxPass2() {
		if (txPass2 == null) {
			txPass2 = new JTextField();
			txPass2.setText("Password");
			txPass2.setColumns(10);
		}
		return txPass2;
	}
	private JPanel getPnJ4() {
		if (pnJ4 == null) {
			pnJ4 = new JPanel();
			pnJ4.setBorder(new TitledBorder(null, "Jugador 4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJ4.setLayout(new GridLayout(2, 2, 0, 0));
			pnJ4.add(getLblLogin4());
			pnJ4.add(getTxLogin4());
			pnJ4.add(getLblPass4());
			pnJ4.add(getTxPass4());
		}
		return pnJ4;
	}
	private JLabel getLblLogin4() {
		if (lblLogin4 == null) {
			lblLogin4 = new JLabel("Login");
		}
		return lblLogin4;
	}
	private JTextField getTxLogin4() {
		if (txLogin4 == null) {
			txLogin4 = new JTextField();
			txLogin4.setText("Login");
			txLogin4.setColumns(10);
		}
		return txLogin4;
	}
	private JLabel getLblPass4() {
		if (lblPass4 == null) {
			lblPass4 = new JLabel("Password: ");
		}
		return lblPass4;
	}
	private JTextField getTxPass4() {
		if (txPass4 == null) {
			txPass4 = new JTextField();
			txPass4.setText("Password");
			txPass4.setColumns(10);
		}
		return txPass4;
	}
	private JPanel getPnJ6() {
		if (pnJ6 == null) {
			pnJ6 = new JPanel();
			pnJ6.setBorder(new TitledBorder(null, "Jugador 6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJ6.setLayout(new GridLayout(2, 2, 0, 0));
			pnJ6.add(getLblLogin6());
			pnJ6.add(getTxLogin6());
			pnJ6.add(getLblPass6());
			pnJ6.add(getTxPass6());
		}
		return pnJ6;
	}
	private JLabel getLblLogin6() {
		if (lblLogin6 == null) {
			lblLogin6 = new JLabel("Login");
		}
		return lblLogin6;
	}
	private JTextField getTxLogin6() {
		if (txLogin6 == null) {
			txLogin6 = new JTextField();
			txLogin6.setText("Login");
			txLogin6.setColumns(10);
		}
		return txLogin6;
	}
	private JLabel getLblPass6() {
		if (lblPass6 == null) {
			lblPass6 = new JLabel("Password: ");
		}
		return lblPass6;
	}
	private JTextField getTxPass6() {
		if (txPass6 == null) {
			txPass6 = new JTextField();
			txPass6.setText("Password");
			txPass6.setColumns(10);
		}
		return txPass6;
	}
	private JPanel getPnJ5() {
		if (pnJ5 == null) {
			pnJ5 = new JPanel();
			pnJ5.setBorder(new TitledBorder(null, "Jugador 5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJ5.setLayout(new GridLayout(2, 2, 0, 0));
			pnJ5.add(getLblLogin5());
			pnJ5.add(getTxLogin5());
			pnJ5.add(getLblPass5());
			pnJ5.add(getTxPass5());
		}
		return pnJ5;
	}
	private JLabel getLblLogin5() {
		if (lblLogin5 == null) {
			lblLogin5 = new JLabel("Login");
		}
		return lblLogin5;
	}
	private JTextField getTxLogin5() {
		if (txLogin5 == null) {
			txLogin5 = new JTextField();
			txLogin5.setText("Login");
			txLogin5.setColumns(10);
		}
		return txLogin5;
	}
	private JLabel getLblPass5() {
		if (lblPass5 == null) {
			lblPass5 = new JLabel("Password: ");
		}
		return lblPass5;
	}
	private JTextField getTxPass5() {
		if (txPass5 == null) {
			txPass5 = new JTextField();
			txPass5.setText("Password");
			txPass5.setColumns(10);
		}
		return txPass5;
	}
	
	//####################################################################
	//######-------LÓGICA-------##########################################
	//####################################################################
	
	/**
	 * Método para invocar en los action de los radiobutton
	 * Activa o desactiva los paneles en función del número de jugadores seleccionados
	 */
	private void gestionarPaneles() {
		int numJugadores = 0;
		
		// Saber el numero de jugadores seleccionado
		Component[] rbs = getPnSeleccionJugadores().getComponents();
		for(int i=0; i<rbs.length; i++) {
			JRadioButton rb = (JRadioButton) rbs[i];
			if(rb.isSelected()) {
				numJugadores = Integer.valueOf(rb.getText());
			}
		}
		
		// Activar
		JPanel[] paneles = new JPanel[]{pnJ1, pnJ2, pnJ3, pnJ4, pnJ5, pnJ6};
		for(int i=0; i<paneles.length; i++) {
			if(i<numJugadores)
				estadoPanel(paneles[i], true);
			else
				estadoPanel(paneles[i], false);
		}
	}
		
	/**
	 * Activa o desactiva todos los componentes de un panel
	 * M�todo auxiliar para el gestionarPaneles general que se invoca en los rb
	 * @param panel	- panel a modificar
	 * @param activar	- true activado, false desactivado
	 */
	private void estadoPanel(JPanel panel, boolean activar) {
		panel.setEnabled(false);
		Component[] componentes = panel.getComponents();
		for(int i=0; i<componentes.length; i++) {
			componentes[i].setEnabled(activar);
			if(!activar) { // Eliminar los datos si vamos a quitar un jugador
				if(componentes[i] instanceof JTextField) {
					((JTextField) componentes[i]).setText("");
				}
			}
		}
	}
	public int numJugadores() {
		int numJugadores = 0;
		
		// Saber el numero de jugadores seleccionado
		Component[] rbs = getPnSeleccionJugadores().getComponents();
		for(int i=0; i<rbs.length; i++) {
			JRadioButton rb = (JRadioButton) rbs[i];
			if(rb.isSelected()) {
				numJugadores = Integer.valueOf(rb.getText());
			}
		}
		return numJugadores;
	}
	
	private boolean comprobarCamposVacios() {
		int numJugadores = numJugadores();
		
		switch (numJugadores) {
			
			case 2: {
				if(
				!txLogin1.getText().equals("") &&
				!txPass1.getText().equals("")  &&
				!txLogin2.getText().equals("") &&
				!txPass2.getText().equals("")  )
					return true;
			}
			
			case 3: {
				if(
				!txLogin1.getText().equals("") &&
				!txPass1.getText().equals("")  &&
				!txLogin2.getText().equals("") &&
				!txPass2.getText().equals("")  &&
				!txLogin3.getText().equals("") &&
				!txPass3.getText().equals("")  )
					return true;
			}
			
			case 4: {
				if(
				!txLogin1.getText().equals("") &&
				!txPass1.getText().equals("")  &&
				!txLogin2.getText().equals("") &&
				!txPass2.getText().equals("")  &&
				!txLogin3.getText().equals("") &&
				!txPass3.getText().equals("")  &&
				!txLogin4.getText().equals("") &&
				!txPass4.getText().equals("")  )
					return true;
			}
		}
		
		return false;
	}
		
/*public String comprobarCamposPanel(JPanel pn) {
		String nombre_panel = pn.getName();
		char jugador = nombre_panel.charAt(nombre_panel.length());
		System.out.println("Jugador "+jugador);
		Component[] comp = pn.getComponents();
		for(int i=0; i<comp.length; i++) {
			if(comp[i] instanceof JTextField) {
				if ( (((JTextField) comp[i]).getText()).equals("") )
				return "Debes rellenar todos los campos";
			}
		}
		return "OK";
		
	}*/
	
	//####################################################################
	//####################################################################
	//####################################################################
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtnContinuar());
		}
		return pnBotones;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			final ConfigurarPartida cp = this;
			btnContinuar = new JButton("Continuar");
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarCamposVacios()) {
						JOptionPane.showMessageDialog(null, "OK");
						Juego j = new Juego(cp);
						j.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Hay campos vac�os");
					}
				}
			});
		}
		return btnContinuar;
	}
}
