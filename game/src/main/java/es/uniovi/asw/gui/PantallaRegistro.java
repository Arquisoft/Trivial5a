package es.uniovi.asw.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.model.User;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaRegistro extends JDialog {

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
		setBounds(100, 100, 586, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(40, 59, 84, 16);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblRegistro = new JLabel("Registro");
			lblRegistro.setBounds(216, 10, 98, 33);
			contentPanel.add(lblRegistro);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(40, 88, 98, 16);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblPasswordRepetida = new JLabel("Password repetida");
			lblPasswordRepetida.setBounds(40, 117, 137, 16);
			contentPanel.add(lblPasswordRepetida);
		}
		{
			JButton okButton = new JButton("Registrarse");
			okButton.addActionListener(new ActionListener() {
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
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
				}
			});
			okButton.setBounds(248, 306, 137, 25);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setBounds(425, 306, 108, 25);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			passwordField1 = new JPasswordField();
			passwordField1.setBounds(189, 85, 125, 22);
			contentPanel.add(passwordField1);
		}
		{
			passwordField2 = new JPasswordField();
			passwordField2.setBounds(189, 114, 125, 22);
			contentPanel.add(passwordField2);
		}
		{
			textFieldUser = new JTextField();
			textFieldUser.setBounds(189, 56, 125, 22);
			contentPanel.add(textFieldUser);
			textFieldUser.setColumns(10);
		}
		{
			textFieldError = new JTextField();
			textFieldError.setEditable(false);
			textFieldError.setVisible(false);
			textFieldError.setBounds(171, 203, 269, 33);
			contentPanel.add(textFieldError);
			textFieldError.setColumns(10);
		}
	}
	
	
	public boolean validarPassword(String cs, String cs2)
	{
		if (cs.equals(cs2))
			return true;
		return false;
	}

}
