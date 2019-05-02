import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener {

	public boolean play, quit, help;
	
	public MouseManager() {
	
	}	

	public void mousePressed(MouseEvent e) {

//		private Rectangle playButton = new Rectangle(handler.getGame().width / 2 - 50, 200, 100, 50);  
//		private Rectangle helpButton = new Rectangle(handler.getGame().width / 2 - 50, 350, 100, 50);  
//		private Rectangle quitButton = new Rectangle(handler.getGame().width / 2 - 50, 500, 100, 50);  
		int mx = e.getX();
		int my = e.getY();
		
		// Play Button
		if(mx >= 850 / 2 - 50  && mx <= 850 / 2 + 50 ) {
			if(my >= 200 && my <= 250) {
				play = true;
			}
		}
		
		// Quit button
		if(mx >= 850 / 2 - 50  && mx <= 850 / 2 + 50 ) {
			if(my >= 500 && my <= 550) {
				quit = true;
			}
		}
		
		// Help button
		if(mx >= 850 / 2 - 50  && mx <= 850 / 2 + 50 ) {
			if(my >= 350 && my <= 400) {
				help = true;
			}
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
