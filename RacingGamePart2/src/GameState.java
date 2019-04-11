import java.awt.Graphics;

public class GameState extends State {

	private Player1 player1;
	//private Player2 player2;
	
	public GameState(Handler handler) {
		super(handler);
		player1 = new Player1(handler, 725,350);
	}
	
	@Override
	public void tick() {
		player1.tick();
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.player1,0,0,null);
		player1.render(g);
	}

}
