
public class Handler {

	private Game game;
	//public GameState gameState; //maybe
	
	public Handler(Game game){
		this.game = game;
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

//	public GameState getGameState() {
//		return gameState;
//	}
//
//	public void setGameState(GameState gameState) {
//		this.gameState = gameState;
//	}
}
