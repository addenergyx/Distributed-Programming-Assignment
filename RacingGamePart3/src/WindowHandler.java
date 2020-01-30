import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class WindowHandler implements WindowListener{

	
	private final Game game;
	
	public WindowHandler(Game game) {
        this.game = game;
        game.track.frame.addWindowListener(this);
	}
	
	
	@Override
	public void windowActivated(WindowEvent event) {
		
	}

	@Override
	public void windowClosed(WindowEvent event) {

	}

	@Override
	public void windowClosing(WindowEvent event) {
		Packet01Disconnect packet = new Packet01Disconnect(this.game.player.getUsername());
		packet.writeData(this.game.socketClient); // Remove player from server when close window	
	}

	@Override
	public void windowDeactivated(WindowEvent event) {

	}

	@Override
	public void windowDeiconified(WindowEvent event) {

	}

	@Override
	public void windowIconified(WindowEvent event) {

	}

	@Override
	public void windowOpened(WindowEvent event) {

	}

}
