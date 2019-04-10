import javax.swing.JFrame;
import java.awt.*;

public class Track {

	private JFrame frame;
	private Canvas canvas; //All graphics drawn to canvas
	
	private String title;
	private int width, height;
	
	public Track(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	

	private void createDisplay() {
		
		frame = new JFrame(title);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ensures game closes properly
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //Window pops up in centre instead of side
		frame.setVisible(true); //By default frames are invisible
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		// Code below ensures canvas stays at desired size
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setFocusable(false);//JFrame in focus
		
		frame.add(canvas);
		frame.pack(); // Resizes window to see all of canvas
	}

	// Getter
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}