package es.uniovi.asw.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;

import org.pushingpixels.substance.api.renderers.SubstanceDefaultTableCellRenderer;

public class RendererSubstance extends SubstanceDefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;
	
	private int numeroJugadores;
	
	public RendererSubstance(ConfigurarPartida cp) {
		numeroJugadores = cp.numJugadores();
	}
	
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	
	       super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      
	       this.setFont(new Font("Dialog", Font.PLAIN, 12));
	       
	       int altura = table.getParent().getHeight();
	       
	       table.setRowHeight(altura/numeroJugadores);
	       
	       
	       if(column == 2) {
	    	   this.setBackground(new Color(255,140,0));
	    	   this.setMaximumSize(new Dimension(10, 100));
	       }
	       	
	       if(column == 3)
	    	   this.setBackground(new Color(156, 93, 82));
	       
	       if(column == 4)
	    	   this.setBackground(new Color(30,144,255));
	       
	       if(column == 5)
	    	   this.setBackground(new Color(255,255,153));
	       
	       if(column == 6)
	    	   this.setBackground(new Color(0, 153, 51));
	       
	       if(column == 7)
	    	   this.setBackground(new Color(255,51,204));


	        this.setForeground(Color.black);
	        this.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
	       
	        
	        return this;
	    }

	

}