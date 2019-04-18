import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(3); //	Multi-threading
		
		Game game = new Game("Race Track",850,650);
		//game.start();
		
		MusicPlayer play = new MusicPlayer("Stadium");
		pool.execute(play);
		pool.execute(game);
		
		
		pool.shutdown();  //close threads
	}

}
