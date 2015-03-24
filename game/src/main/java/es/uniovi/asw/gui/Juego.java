package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.ScrollPaneConstants;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

public class Juego extends JFrame {

	private final static int numcasillas = 30;
	
	private ConfigurarPartida ventana_login;
//	private ModeloNoEditable modeloTabla;
	private DefaultTableModel modeloTabla;
	
	private JPanel contentPane;
	private JPanel pnTablero;
	private JPanel pnNorte;
	private JPanel pnGestion;
	private JLabel lblIcono;
	private JLabel lblTitulo;
	private JPanel pnDado;
	private JScrollPane pnScTabla;
	private JButton btnDado;
	private JTextField txtDado;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public Juego(ConfigurarPartida cp) {
		SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.EmeraldDuskSkin");
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		ventana_login = cp;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 844, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnTablero(), BorderLayout.CENTER);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnGestion(), BorderLayout.WEST);
		generarBotones();
	}

	private JPanel getPnTablero() {
		if (pnTablero == null) {
			pnTablero = new JPanel(new CircleLayout(true));
			pnTablero.setMaximumSize(new Dimension(500,500));
		}
		return pnTablero;
	}
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setLayout(new BorderLayout(0, 0));
			pnNorte.add(getLblIcono(), BorderLayout.WEST);
			pnNorte.add(getLblTitulo(), BorderLayout.CENTER);
		}
		return pnNorte;
	}
	private JPanel getPnGestion() {
		if (pnGestion == null) {
			pnGestion = new JPanel();
			pnGestion.setLayout(new BorderLayout(0, 0));
			pnGestion.add(getPnDado(), BorderLayout.NORTH);
			pnGestion.add(getPnScTabla());
			
			
		}
		return pnGestion;
	}

	//####################################################################
	//######-------L�GICA-------##########################################
	//####################################################################
	
	private void elegirCasillar(JButton casilla) {
		lanzarPregunta();
	}
	
	private void lanzarPregunta() {}
	
	
	private void generarBotones() {
		BotonTablero bt;
		for (int i = 0; i < 30; i++) {
			bt = new BotonTablero(i);
		    getPnTablero().add(bt);
		}
	}
	
	
	private void rellenarFilasTabla() {
		int numJugadores = ventana_login.numJugadores();
		for(int i=0; i<=numJugadores; i++) {
			Object[] fila = new Object[2];
			fila[0] = String.valueOf(i);
			fila[1] = "aqui va el nombre";
			modeloTabla.addRow(fila);
		}
		
	}
	
	private void tirarDado() {
		int x = 1 + new Double(Math.random() * 6).intValue();
		txtDado.setText(String.valueOf(x));
		activarBotones(x, 0);
		
	}
	
	private void activarBotones(int numdado, int btnActual) {
			
		int opc1;
		int opc2;	
		if(btnActual <= 5) 
		{
			int aux = numcasillas+btnActual;
			opc1=aux-numdado;
			opc2=btnActual+numdado;
		}
		else 
		{
			opc1=btnActual-numdado;
			opc2=btnActual+numdado;
		}
		
		System.out.println(opc1);
		System.out.println(opc2);
		
		Component[] botones = pnTablero.getComponents();		
		for(int i=0; i<botones.length; i++) 
		{
			JButton bt = (JButton) botones[i];
			if( Integer.valueOf(bt.getActionCommand()) == opc1 || Integer.valueOf(bt.getActionCommand()) == opc2) 
			{
				bt.setEnabled(true);
			}
		}
		
	}
	
	//####################################################################
	//####################################################################
	//####################################################################
	
	private JLabel getLblIcono() {
		if (lblIcono == null) {
			lblIcono = new JLabel("aqui va el icono");
		}
		return lblIcono;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("TRIVIAL 5A");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblTitulo;
	}
	private JPanel getPnDado() {
		if (pnDado == null) {
			pnDado = new JPanel();
			pnDado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnDado.add(getBtnDado());
			pnDado.add(getTxtDado());
		}
		return pnDado;
	}
	private JScrollPane getPnScTabla() {
		if (pnScTabla == null) {
			pnScTabla = new JScrollPane(table);
			pnScTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pnScTabla.setViewportView(getTable());
		}
		return pnScTabla;
	}
	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("");
			btnDado.setIcon(new ImageIcon(Juego.class.getResource("/img/dados.gif")));
			btnDado.setBackground(null);
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tirarDado();
				}
			});
			
		}
		return btnDado;
	}
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setColumns(2);
			txtDado.setBackground(null);
			txtDado.setHorizontalAlignment(SwingConstants.CENTER);
			txtDado.setFont(new Font("Tekton Pro", Font.PLAIN, 70));
			txtDado.setBounds(txtDado.getX(), txtDado.getY(), 10, 10);
			
		}
		return txtDado;
	}
	private JTable getTable() {
		if (table == null) {
			String[] columnas = {"Jugador", "Nombre", "Puntos"};
			modeloTabla = new ModeloNoEditable(columnas, 0);
			
			table = new JTable(modeloTabla);
			
			rellenarFilasTabla();
			RendererSubstance renderer = new RendererSubstance();
			table.setDefaultRenderer(Object.class, renderer);
			
		//	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
		return table;
	}
	
	
}
