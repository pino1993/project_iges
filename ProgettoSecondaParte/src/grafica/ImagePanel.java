package grafica;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore che riceve in ingresso una stringa rappresente l'immagine da impostare come sfondo
	 * @param img
	 */
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	/**
	 * Costruttore che riceve in ingresso un immagine da impostare come sfondo
	 * @param img
	 */
	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	/**
	 * Metodo che disegna l'immagine
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}
	
	private Image img;
}