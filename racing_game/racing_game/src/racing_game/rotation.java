package racing_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// For any timer must implement actionlistener
public class Rotation extends JPanel implements ActionListener {

	// Helps to serialise exceptions correctly
	private static final long serialVersionUID = 1L; // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html

	private Timer animator;
	
	// Array containing images for each angle of go-kart
	private ImageIcon imageArray[];
	
	private int delay = 40, totalImages = 16, currentImage = 0;
	
		public Rotation() {
			imageArray = new ImageIcon[totalImages];
			
			for (int i=0; i < imageArray.length; i++) {
				imageArray[i] = new ImageIcon("images/greenKart" + i + ".png");
			}
			
			// Timer determines speed of rotation
			animator = new Timer(delay, this);
			animator.start();
		}
	
		// Draws image to window
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// When reaches end of animation (array) restart
			if (currentImage >= imageArray.length - 1) {
				currentImage = 0;
			}
			currentImage++;
			imageArray[currentImage].paintIcon(this, g, 0, 0);
		}
		
		// Wipes window allowing for new angle to show
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
		
}