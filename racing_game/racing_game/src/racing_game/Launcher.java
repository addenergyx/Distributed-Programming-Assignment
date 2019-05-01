package racing_game;

import javax.swing.JFrame;

public class Launcher {
	public static void main(String[] args) {
	JFrame frame = new JFrame();
	Rotation rotation = new Rotation();
	frame.add(rotation);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,400);
	}
}

