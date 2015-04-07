package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.uniovi.asw.bussines.Game;

public class Juego extends JFrame {

	private static final long serialVersionUID = 1L;

	private final static int numcasillas = 30;

	private Game juego;

	private CircleLayout cl;

	private ConfigurarPartida ventana_login;

	private ModeloNoEditable modeloTabla;

	private JPanelConFondo contentPane;

	private JPanelConFondo pnTablero;

	private JPanelConFondo pnNorte;

	private JPanelConFondo pnGestion;

	private JLabel lblIcono;

	private JPanelConFondo pnDado;

	private JScrollPane pnScTabla;

	private JButton btnDado;

	private JTextField txtDado;

	private JTable table;

	private JPanelConFondo pnGestionCentro;

	private JFileChooser selector;

	private JMenuBar menuBar;

	private JMenu mnJuego;

	private JMenu mnConfiguracin;

	private JMenuItem mntmCambiarFondo;

	private JMenuItem mntmNuevo;

	private JSeparator separator;

	private JMenuItem mntmSalir;

	private JLabel lblDados;

	private JSeparator separator_1;

	private JMenuItem mntmEstadisticas;

	/**
	 * Crea el comienzo de juego
	 * 
	 * @param cp
	 * @param juego
	 */
	public Juego(ConfigurarPartida cp, Game juego) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Juego.class
						.getResource("/es/uniovi/asw/gui/img/iconoPeque.png")));
		cl = new CircleLayout();
		ventana_login = cp;
		this.juego = juego;
		juego.setUsuarioActivo(juego.getUsuarios().get(0));
		setPreferredSize(new java.awt.Dimension(1167, 733));
		this.setTitle("Trivial");

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 1167, 733);

		// para que no se pueda reducir la pantalla, el minimo es lo inicial
		setMinimumSize(getPreferredSize());
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanelConFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 0));
		setContentPane(contentPane);
		contentPane.add(getPnTablero(), BorderLayout.CENTER);
		contentPane.add(getPnGestion(), BorderLayout.WEST);
		pintarPosicion(juego.getUsuarioActivo().getPosicion());
	}

	/**
	 * Devuelve el valor de selector
	 * 
	 * @return selector
	 */
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(false);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos jpg",
					"jpg"));
			String directorio = System.getProperty("user.home") + "/Desktop";
			selector.setCurrentDirectory(new File(directorio));
		}
		return selector;
	}

	/**
	 * Devuelve el valor de pnTablero
	 * 
	 * @return pnTablero
	 */
	private JPanelConFondo getPnTablero() {
		if (pnTablero == null) {
			pnTablero = new JPanelConFondo("/es/uniovi/asw/gui/img/tablero.png");
			pnTablero.setLayout(cl);
			pnTablero.setBorder(new EmptyBorder(1, 1, 45, 40));
			pnTablero.add(getBtDado());
			generarBotones();
		}
		return pnTablero;
	}

	/**
	 * Devuelve el valor de btnDado
	 * 
	 * @return btnDado
	 */
	public JButton getBtDado() {
		if (btnDado == null) {
			btnDado = cl.getBoton();
			btnDado.setIgnoreRepaint(false);
			btnDado.setIcon(new ImageIcon(Juego.class
					.getResource("/es/uniovi/asw/gui/img/dados.gif")));
			btnDado.setDisabledIcon(new ImageIcon(Juego.class
					.getResource("/es/uniovi/asw/gui/img/dd.png")));
			btnDado.setRolloverIcon(new ImageIcon(Juego.class
					.getResource("/es/uniovi/asw/gui/img/dados.gif")));
			btnDado.setBackground(null);
			btnDado.setActionCommand("-1");
			btnDado.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnDado.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					tirarDado();
				}
			});
		}
		return btnDado;
	}

	/**
	 * Devuelve el valor de pnNorte
	 * 
	 * @return pnNorte
	 */
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanelConFondo(
					"/es/uniovi/asw/gui/img/transparente.png");
			pnNorte.setLayout(new GridLayout(0, 1, 0, 0));
			pnNorte.add(getLblIcono());
			pnNorte.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return pnNorte;
	}

	/**
	 * Devuelve el valor de pnGestion
	 * 
	 * @return pnGestion
	 */
	private JPanel getPnGestion() {
		if (pnGestion == null) {
			pnGestion = new JPanelConFondo(
					"/es/uniovi/asw/gui/img/transparente.png");
			pnGestion.setBorder(new EmptyBorder(20, 20, 20, 30));
			pnGestion.setLayout(new BorderLayout(0, 40));
			pnGestion.add(getPnNorte(), BorderLayout.NORTH);
			pnGestion.add(getPnGestionCentro(), BorderLayout.WEST);
		}
		return pnGestion;
	}

	// LÓGICA

	/**
	 * Genera los botones del tablero
	 */
	private void generarBotones() {
		BotonTablero bt;
		for (int i = 0; i < 30; i++) {
			bt = new BotonTablero(i, this, juego);
			getPnTablero().add(bt);
		}
	}

	/**
	 * Rellena las filas de la tabla con la información de cada jugador
	 */
	private void rellenarFilasTabla() {
		List<String> logins = ventana_login.getLogins();
		int numJugadores = ventana_login.numJugadores();
		for (int i = 0; i < numJugadores; i++) {
			Object[] fila = new Object[2];
			fila[0] = String.valueOf(i + 1);
			fila[1] = logins.get(i);
			modeloTabla.addRow(fila);
		}
	}

	/**
	 * Método que asgina un número aleatorio al tirar el dado (entre 1 y 6
	 * incluidos ambos)
	 */
	private void tirarDado() {
		int x = 1 + new Double(Math.random() * 6).intValue();
		txtDado.setText(String.valueOf(x));
		System.out.println("POS TIRAR DADO: "
				+ juego.getUsuarioActivo().getPosicion());
		activarBotones(x, juego.getUsuarioActivo().getPosicion(), true);
		btnDado.setEnabled(false);
	}

	/**
	 * Método que activa el botón en el momento que lanzamos en el dad, para
	 * saber en que posición estamos
	 * 
	 * @param numdado
	 * @param btnActual
	 * @param active
	 */
	private void activarBotones(int numdado, int btnActual, boolean active) {
		int opc1;
		int opc2;
		if (btnActual <= 5) {
			if (btnActual - numdado >= 0)
				opc1 = btnActual - numdado;
			else {
				int aux = numcasillas + btnActual;
				opc1 = aux - numdado;
			}
			opc2 = btnActual + numdado;
		} else if (btnActual >= 24) {
			if (btnActual + numdado >= 30)
				opc1 = (btnActual + numdado) - numcasillas;
			else
				opc1 = btnActual + numdado;
			opc2 = btnActual - numdado;
		} else {
			opc1 = btnActual - numdado;
			opc2 = btnActual + numdado;
		}
		Component[] botones = pnTablero.getComponents();
		for (int i = 0; i < botones.length; i++) {
			JButton bt = (JButton) botones[i];
			if (Integer.valueOf(bt.getActionCommand()) == opc1
					|| Integer.valueOf(bt.getActionCommand()) == opc2) {
				bt.setEnabled(active);
			}
		}
		System.out.println("OPC1: " + opc1);
		System.out.print("OPC: " + opc2);
	}

	/**
	 * Resalta la posición en la que estamos
	 * 
	 * @param btnActual
	 */
	public void pintarPosicion(int btnActual) {
		Component[] botones = pnTablero.getComponents();
		for (int i = 0; i < botones.length; i++) {
			JButton bt = (JButton) botones[i];
			if (Integer.valueOf(bt.getActionCommand()) == btnActual) {
				bt.setText(""
						+ (juego.getUsuarios()
								.indexOf(juego.getUsuarioActivo()) + 1));
			} else {
				// Si no se mete texto los botones se hacen cuadrados
				bt.setText(" ");
			}
		}
	}

	/**
	 * Desactiva el botón un ve se realice la pregunta o volvamos a tirar el
	 * dado
	 */
	public void desactivarBotones() {
		txtDado.setText("");
		btnDado.setIcon(new ImageIcon(Juego.class
				.getResource("/es/uniovi/asw/gui/img/dados.gif")));
		btnDado.setRolloverIcon(new ImageIcon(Juego.class
				.getResource("/es/uniovi/asw/gui/img/dados.gif")));
		Component[] botones = pnTablero.getComponents();
		for (int i = 0; i < botones.length; i++) {
			JButton bt = (JButton) botones[i];
			if (Integer.valueOf(bt.getActionCommand()) != -1) {
				bt.setEnabled(false);
			}
		}
	}

	/**
	 * Devuelve el valor de lblIcono
	 * 
	 * @return lblIcono
	 */
	private JLabel getLblIcono() {
		if (lblIcono == null) {
			lblIcono = new JLabel("");
			lblIcono.setIcon(new ImageIcon(Juego.class
					.getResource("/es/uniovi/asw/gui/img/icon.png")));
			lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
			lblIcono.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		return lblIcono;
	}

	/**
	 * Devuelve el valor de pnDado
	 * 
	 * @return pnDado
	 */
	private JPanelConFondo getPnDado() {
		if (pnDado == null) {
			pnDado = new JPanelConFondo(
					"/es/uniovi/asw/gui/img/transparente.png");
			pnDado.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnDado.setLayout(new GridLayout(0, 2, 5, 0));
			pnDado.add(getLblDados());
			pnDado.add(getTxtDado());
		}
		return pnDado;
	}

	/**
	 * Devuelve el valor de pnScTabla
	 * 
	 * @return pnScTabla
	 */
	private JScrollPane getPnScTabla() {
		if (pnScTabla == null) {
			pnScTabla = new JScrollPane(table);
			pnScTabla.setEnabled(false);
			pnScTabla
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnScTabla.setViewportView(getTable());
		}
		return pnScTabla;
	}

	/**
	 * Devuelve el valor de txtDado
	 * 
	 * @return txtDado
	 */
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setBorder(new LineBorder(new Color(171, 173, 179)));
			txtDado.setToolTipText("");
			txtDado.setEnabled(false);
			txtDado.setEditable(false);
			txtDado.setColumns(2);
			txtDado.setBackground(null);
			txtDado.setMaximumSize(getPreferredSize());
			txtDado.setHorizontalAlignment(SwingConstants.CENTER);
			txtDado.setFont(new Font("Tekton Pro", Font.PLAIN, 70));
		}
		return txtDado;
	}

	/**
	 * Devuelve el valor de table
	 * 
	 * @return table
	 */
	public JTable getTable() {
		if (table == null) {
			Object[] columnas = { "Jugador", "Nombre", "GEO", "DEP", "ARTE",
					"CIEN", "ESP", "HIST" };
			modeloTabla = new ModeloNoEditable(columnas, 0);
			table = new JTable(modeloTabla);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setEnabled(false);
			table.getTableHeader().setReorderingAllowed(false);
			rellenarFilasTabla();
			RendererSubstance renderer = new RendererSubstance(ventana_login,
					juego);
			table.setDefaultRenderer(Object.class, renderer);
		}
		return table;
	}

	/**
	 * Devuelve el valor de pnGestionCentro
	 * 
	 * @return pnGestionCentro
	 */
	private JPanelConFondo getPnGestionCentro() {
		if (pnGestionCentro == null) {
			pnGestionCentro = new JPanelConFondo(
					"/es/uniovi/asw/gui/img/transparente.png");
			pnGestionCentro.setLayout(new BorderLayout(0, 30));
			pnGestionCentro.add(getPnScTabla());
			pnGestionCentro.add(getPnDado(), BorderLayout.NORTH);
		}
		return pnGestionCentro;
	}

	/**
	 * Devuelve el valor de menuBar
	 * 
	 * @return menuBar
	 */
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnJuego());
			menuBar.add(getMnConfiguracin());
		}
		return menuBar;
	}

	/**
	 * Devuelve el valor de mnJuego
	 * 
	 * @return mnJuego
	 */
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getSeparator());
			mnJuego.add(getMntmEstadisticas());
			mnJuego.add(getSeparator_1());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}

	/**
	 * Devuelve el valor de mnConfiguracin
	 * 
	 * @return mnConfiguracin
	 */
	private JMenu getMnConfiguracin() {
		if (mnConfiguracin == null) {
			mnConfiguracin = new JMenu("Configuraci\u00F3n");
			mnConfiguracin.add(getMntmCambiarFondo());
		}
		return mnConfiguracin;
	}

	/**
	 * Devuelve el valor de mntmCambiarFondo
	 * 
	 * @return mntmCambiarFondo
	 */
	private JMenuItem getMntmCambiarFondo() {
		if (mntmCambiarFondo == null) {
			mntmCambiarFondo = new JMenuItem("Cambiar fondo");
			mntmCambiarFondo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int respuesta = getSelector().showOpenDialog(null);
					if (respuesta == JFileChooser.APPROVE_OPTION) {
						File file = selector.getSelectedFile();
						String nombre = file.getAbsolutePath();
						contentPane.setImagen((new ImageIcon(nombre))
								.getImage());
					}
				}
			});
		}
		return mntmCambiarFondo;
	}

	/**
	 * Devuelve el valor de mntmNuevo
	 * 
	 * @return mntmNuevo
	 */
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			final Juego partida = this;
			mntmNuevo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Game juegonuevo = new Game();
					PantallaInicial pn = new PantallaInicial(juegonuevo);
					pn.setVisible(true);
					pn.setLocationRelativeTo(null);
					partida.setVisible(false);
				}
			});
		}
		return mntmNuevo;
	}

	/**
	 * Devuelve el valor de separator
	 * 
	 * @return separator
	 */
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	/**
	 * Devuelve el valor de mntmSalir
	 * 
	 * @return mntmSalir
	 */
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}

	/**
	 * Devuelve el valor de lblDados
	 * 
	 * @return lblDados
	 */
	private JLabel getLblDados() {
		if (lblDados == null) {
			lblDados = new JLabel("");
			lblDados.setHorizontalAlignment(SwingConstants.CENTER);
			lblDados.setIcon(new ImageIcon(Juego.class
					.getResource("/es/uniovi/asw/gui/img/dadosIcono.png")));
			lblDados.setToolTipText("\"Para tirar del dado pulsa en el centro del tablero\"");
		}
		return lblDados;
	}

	/**
	 * Devuelve el valor de separator_1
	 * 
	 * @return separator_1
	 */
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	/**
	 * Devuelve el valor de mntmEstadisticas
	 * 
	 * @return mntmEstadisticas
	 */
	public JMenuItem getMntmEstadisticas() {
		if (mntmEstadisticas == null) {
			mntmEstadisticas = new JMenuItem("Estadisticas");
			if (juego.getUsuarioActivo().isAdmin())
				mntmEstadisticas.setEnabled(true);
			else
				mntmEstadisticas.setEnabled(false);
			mntmEstadisticas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaEstadisticas ve = new VentanaEstadisticas(juego);
					ve.setVisible(true);
					ve.setLocationRelativeTo(null);
				}
			});
		}
		return mntmEstadisticas;
	}

	/**
	 * Repinta el boton de Estadisticas ya que solo los Administradores pueden
	 * ver las estadisticas
	 */
	public void repintarBotonEstadistica() {
		if (juego.getUsuarioActivo().isAdmin())
			mntmEstadisticas.setEnabled(true);
		else
			mntmEstadisticas.setEnabled(false);
		mntmEstadisticas.setVisible(true);
	}

	public void repintarUsuarioActivo() {
		RendererSubstance renderer = new RendererSubstance(ventana_login, juego);
		table.setDefaultRenderer(Object.class, renderer);
	}
}
