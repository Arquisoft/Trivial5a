package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.model.User;

public class PantallaRegistro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Game juego;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField textFieldUser;
	private JTextField textFieldError;
	
 
	/**
	 * Create the dialog.
	 */
	public PantallaRegistro(final Game juego) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaRegistro.class.getResource("/es/uniovi/asw/gui/img/iconoPeque.png")));
		setModal(true);
		this.juego=juego;
		setBounds(100, 100, 482, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRegistro = new JLabel("Rellene los datos para registrarse");
			lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 23));
			lblRegistro.setBounds(45, 11, 383, 33);
			contentPanel.add(lblRegistro);
		}
		{
		
			JButton okButton = new JButton("Registrarse");
			okButton.addActionListener(new ActionListener() {
				@Override
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) {
					textFieldError.setText("");
					if(!validarPassword(passwordField1.getText(), passwordField2.getText()))
					{
						textFieldError.setText("Las contraseñas no son iguales");
						textFieldError.setVisible(true);
					}
					else
					{
						User user = new User();
						user.setLogin(textFieldUser.getText());
						user.setPassword(passwordField1.getText());
						try {
							
							if(juego.getUserManager().findUserByLogin(user.login))
							{
							textFieldError.setText("El usuario  ya existe");
							textFieldError.setVisible(true);
							}
							else
							{
								juego.getUserManager().addUser(user);
								textFieldError.setText("Se ha registrado correctamente");
								textFieldError.setVisible(true);
								textFieldUser.setText("");
								passwordField1.setText("");
								passwordField2.setText("");
								
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
				}
			});
			okButton.setBounds(45, 306, 137, 25);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setBounds(320, 306, 108, 25);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			textFieldError = new JTextField();
			textFieldError.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textFieldError.setEditable(false);
			textFieldError.setVisible(false);
			textFieldError.setBounds(45, 241, 383, 33);
			contentPanel.add(textFieldError);
			textFieldError.setColumns(10);
		}
		
		JPanel pnCentro = new JPanel();
		pnCentro.setBounds(47, 59, 381, 157);
		contentPanel.add(pnCentro);
		pnCentro.setLayout(new GridLayout(3, 2, 20, 20));
		{
			JLabel lblUsuario = new JLabel("Usuario:");
			pnCentro.add(lblUsuario);
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		{
			textFieldUser = new JTextField();
			pnCentro.add(textFieldUser);
			textFieldUser.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			pnCentro.add(lblPassword);
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		{
			passwordField1 = new JPasswordField();
			pnCentro.add(passwordField1);
		}
		{
			JLabel lblPasswordRepetida = new JLabel("Repita su password:");
			pnCentro.add(lblPasswordRepetida);
			lblPasswordRepetida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		{
			passwordField2 = new JPasswordField();
			pnCentro.add(passwordField2);
		}
	}
	
	
	public boolean validarPassword(String cs, String cs2)
	{
		if (cs.equals(cs2))
			return true;
		return false;
	}
}
