package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.bussines.Game;

public class PantallaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblIcono;
	private JButton btnJugar;
	
	private Game juego;
	private JPanel pnSur;
	private JButton btnRegistro;

	/**
	 * Create the frame.
	 */
	public PantallaInicial(Game juego) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaInicial.class.getResource("/es/uniovi/asw/gui/img/iconoPeque.png")));
		setBounds(new Rectangle(20, 20, 20, 20));
		setResizable(false);
		this.juego = juego;
		this.setTitle("Trivial 5A");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);
		contentPane.add(getLblIcono(), BorderLayout.CENTER);
		contentPane.add(getPnSur(), BorderLayout.SOUTH);
	}

	private JLabel getLblIcono() {
		if (lblIcono == null) {
			lblIcono = new JLabel("");
			lblIcono.setAlignmentY(2.0f);
			lblIcono.setAlignmentX(2.0f);
			lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
			lblIcono.setIcon(new ImageIcon(PantallaInicial.class.getResource("/es/uniovi/asw/gui/img/iconoGrande.png")));
		}
		return lblIcono;
	}
	private JButton getBtnJugar() {
		if (btnJugar == null) {
			btnJugar = new JButton("Jugar");
			btnJugar.setFont(new Font("Tahoma", Font.PLAIN, 27));
			final PantallaInicial ventana = this;
			btnJugar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ConfigurarPartida cp = new ConfigurarPartida(juego);
					cp.setVisible(true);
					cp.setLocationRelativeTo(null);
					ventana.setVisible(false);
				}
			});
		}
		return btnJugar;
	}
	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setLayout(new GridLayout(0, 2, 30, 0));
			pnSur.add(getBtnRegistro());
			pnSur.add(getBtnJugar());
		}
		return pnSur;
	}
	private JButton getBtnRegistro() {
		if (btnRegistro == null) {
			btnRegistro = new JButton("Registrarme");
			final PantallaInicial ventana = this;
			btnRegistro.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PantallaRegistro pr = new PantallaRegistro(juego);
					pr.setLocationRelativeTo(null);
					pr.setVisible(true);
						
				}
			});
			btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 27));
		}
		return btnRegistro;
	}
}
