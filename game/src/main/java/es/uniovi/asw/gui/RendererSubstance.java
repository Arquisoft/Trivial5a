package es.uniovi.asw.gui;

import java.awt.Color;
import java.awt.Component;
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
	       
	   //    table.setPreferredScrollableViewportSize(table.getPreferredSize());
	      
	        if (table.getSelectedRow()==row)
		    
	        {
	        	this.setForeground(Color.gray);
	        	this.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		      }
	        
	        return this;
	    }

	

}