import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		//GameServer socketServer = null;
		ExecutorService pool = Executors.newFixedThreadPool(5); //	Multi-threading
		
		Game game = new Game("Race Track",850,650);
		game.start();
		
		
//		int reply = JOptionPane.showConfirmDialog(null, "Running server", "Server", JOptionPane.YES_NO_OPTION);
//		//if(JOptionPane.showInputDialog(game, "Running server?").trim() == "yes") {
//		if (reply == JOptionPane.YES_OPTION) {
//			socketServer = new GameServer(game);
//			socketServer.start();
//			pool.execute(socketServer);
//			System.out.println("server");
//		} else {
//			pool.execute(game);
//			System.out.println("game");
//		}
		
		MusicPlayer play = new MusicPlayer("Stadium");
		pool.execute(play);
		pool.execute(game);
		//pool.execute(socketServer);
		
		pool.shutdown();  //close threads
	}

}
