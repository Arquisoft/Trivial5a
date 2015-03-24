package es.uniovi.asw.trivial;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.gui.ConfigurarPartida;

public class TrivialDesktop {
	
	public static void main(String [] args) 
	{
		run();
	}

	public static void run() {		
		Game g = new Game();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurarPartida frame = new ConfigurarPartida();
					frame.setVisible(true);
					SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.NebulaBrickWallSkin");
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		g.initialize();
	}

}
