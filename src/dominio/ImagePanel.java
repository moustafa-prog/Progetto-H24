
package dominio;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class ImagePanel  extends JPanel{
	  private Image backgroundImage;
	

	    public ImagePanel(String filePath) {
	    	  try {
		            backgroundImage = ImageIO.read(new File(filePath));
		            if (backgroundImage == null) {
		                System.out.println("Immagine non trovata o formato non supportato: " + filePath);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
	

		// Sovrascrivi il metodo paintComponent per disegnare l'immagine
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (backgroundImage != null) {
	            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	        } else {
	            System.out.println("L'immagine non è stata caricata correttamente.");
	        }
	    }
	}
