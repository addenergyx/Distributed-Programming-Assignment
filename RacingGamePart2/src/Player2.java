//import java.awt.Graphics;
//
//public class Player2 extends Players {
//
//		
//	public Player2(Game game, float x, float y) {
//		super(game, x, y, Players.DEFAULT_CREATURE_WIDTH, Players.DEFAULT_CREATURE_HEIGHT);
//	}
//	
//	@Override
//	public void tick() {
//		getInput();
//		move();
//	}
//	
//	//Update any variables for the object
//	private void getInput(){
//		xMove = 0;
//		yMove = 0;
//		
//		if(game.getKeyManager().up)
//			yMove = -speed;
//		if(game.getKeyManager().down)
//			yMove = speed;
//		if(game.getKeyManager().left)
//			xMove = -speed;
//		if(game.getKeyManager().right)
//			xMove = speed;
//	}
//
//	@Override
//	public void render(Graphics g) {
//		g.drawImage(Assets.player1, (int)x, (int)y, width, height, null);
//	}
//
//
//
//}