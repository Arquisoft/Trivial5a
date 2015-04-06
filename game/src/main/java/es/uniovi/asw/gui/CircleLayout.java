package es.uniovi.asw.gui;

/**
 * This layout manager allows you to place components to form a circle within a
 * Container
 * 
 * @author Oscar De Leon oedeleon@netscape.net
 * 
 */

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;

/**
 * @author Trivial 5A
 * 
 */

class CircleLayout implements LayoutManager {


  private boolean isCircle;
  private JButton bt;

  /**
   * Creates a new CircleLayout that lays out components in a perfect circle
   */

  public CircleLayout() {
    this(true);
    bt = new JButton();
  }

  /**
   * @param circle
   *          Indicated the shape to use. It's true for circle or false for
   *          ellipse.
   */
  public CircleLayout(boolean circle) {
    isCircle = circle;
  }

  /**
   * For compatibility with LayoutManager interface
   */
  @Override
public void addLayoutComponent(String name, Component comp) {
  }

 
  @Override
public void layoutContainer(Container parent) {
    int x=0;
    int y=0;
    int w=0;
    int h=0; 
    int s, c;
    
    int n = parent.getComponentCount();
    
    double parentWidth = parent.getSize().width;
    double parentHeight = parent.getSize().height;
    
    Insets insets = parent.getInsets(); // para tener los bordes en cuenta
    
//    int centerX = (int) (parentWidth- (insets.left + insets.right)) / 2;
//    int centerY = (int) (parentHeight - (insets.top + insets.bottom)) / 2;
    
    int centerX = (int) (parentWidth) / 2;
    int centerY = (int) (parentHeight) / 2;
    
    int ladoBoton = 100; 
    bt.setBounds(centerX-(ladoBoton/2), centerY-(ladoBoton/2), ladoBoton, ladoBoton);
    bt.setBorderPainted(false); 
    bt.setContentAreaFilled(false); 
    bt.setFocusPainted(false); 
    bt.setOpaque(false);
    
    Component comp = null;
    Dimension compPS = null;
    centerX = (int) (parentWidth- (insets.left + insets.right)) / 2;
    centerY = (int) (parentHeight - (insets.top + insets.bottom)) / 2;
    
    if (n == 1) {  // En caso de que solo tenga un componente lo centra
      comp = parent.getComponent(0);
      x = centerX;
      y = centerY;
      compPS = comp.getPreferredSize();
      w = compPS.width;
      h = compPS.height;
      comp.setBounds(x, y, w, h);
    
    } 
    
    else { 
      double r = (Math.min(parentWidth - (insets.left + insets.right), parentHeight
          - (insets.top + insets.bottom))) / 2;   // para tener los bordes en cuenta
     
  // Sin bordes 
 //   double r = (Math.min(parentWidth, parentHeight)) * 0.55;   // radio
 
    	r *= 0.88
    			; // Distancia entre los componentes
    //  
      for (int i = 1; i < n; i++) {
        comp = parent.getComponent(i);
        compPS = comp.getPreferredSize();
        
        if (isCircle) {
          c = (int) (r * Math.cos(2 * (i+1) * Math.PI / (n-1))); // coseno
          s = (int) (r * Math.sin(2 * (i+1) * Math.PI / (n-1))); // seno
        
        } else { // hace un óvalo
          c = (int) ((centerX * 0.75) * Math.cos(2 * i * Math.PI / n));
          s = (int) ((centerY * 0.75) * Math.sin(2 * i * Math.PI / n));
        }
        
        x = c + centerX;
        y = s + centerY;

        w = compPS.width;
        h = compPS.height;

        comp.setBounds(x, y, w, h); 
        
        
      }
      
    }
   
  }

  /**
   * Returns this CircleLayout's preferred size based on its Container
   * 
   * @param target
   *          This CircleLayout's target container
   * @return The preferred size
   */

  @Override
public Dimension preferredLayoutSize(Container target) {
    return target.getSize();
  }

  /**
   * Returns this CircleLayout's minimum size based on its Container
   * 
   * @param target
   *          This CircleLayout's target container
   * @return The minimum size
   */
  @Override
public Dimension minimumLayoutSize(Container target) {
    return target.getSize();
  }

  /**
   * For compatibility with LayoutManager interface
   */
  @Override
public void removeLayoutComponent(Component comp) {
  }

  /**
   * Returns a String representation of this CircleLayout.
   * 
   * @return A String that represents this CircleLayout
   */
  @Override
public String toString() {
    return this.getClass().getName();
  }

public JButton getBoton() {
	return bt;
}


 
}