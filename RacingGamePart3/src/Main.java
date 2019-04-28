import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
//		ExecutorService pool = Executors.newFixedThreadPool(5); //	Multi-threading
		
		Game game = new Game("Race Track",850,650);
		game.start();
		
//		MusicPlayer play = new MusicPlayer("Stadium");
//		pool.execute(play);
//		pool.execute(game);
		
//		int reply = JOptionPane.showConfirmDialog(null, "Running server", "Server", JOptionPane.YES_NO_OPTION);
//		//if(JOptionPane.showInputDialog(game, "Running server?").trim() == "yes") {
//		if (reply == JOptionPane.YES_OPTION) {
//			GameServer socketServer = new GameServer(game);
//			pool.execute(socketServer);
//			//System.out.println("server");
//		} 
//		
//		GameClient socketClient = new GameClient(game, "localhost");
//		
//		pool.execute(socketClient);
//		//socketClient.sendData("ping".getBytes());
//		
//		Packet00Login loginPacket = new Packet00Login(JOptionPane.showInputDialog(game, "Username: "));
//		loginPacket.writeData(socketClient);
//				
//		pool.shutdown();  //close threads
	}

}
