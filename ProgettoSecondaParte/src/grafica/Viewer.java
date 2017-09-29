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
		frame.setLocation ((screenSize.width/2)-(frame.getWidth( )/2) , (screenSize.height/2)-(frame.getHeight( )/2));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}