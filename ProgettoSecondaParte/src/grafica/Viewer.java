package grafica;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Viewer {	
	public static void main(String[] args) {
		//frame [frame principale]
		//base  [frame sfondo]
		
		JFrame frame = new Sistema();
		JFrame base = new JFrame();
		frame.setUndecorated(true);
		//set immagine con dimensioni schermo
		ImagePanel panel = new ImagePanel(new ImageIcon("apple.jpg").getImage());
		
		//add panels
		base.add(panel);
		base.pack();
		base.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		base.setVisible(true);
		base.setResizable(false);
		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame al centro dello schermo
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ((screenSize.width/4)-(frame.getWidth( )/4) , (screenSize.height/4)-(frame.getHeight( )/4));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}