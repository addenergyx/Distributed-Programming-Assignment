import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity {
	
	protected Handler handler;
	protected float x,y; //float for smooth movement of kart
	protected int width, height;
	protected Rectangle bounds; //player boundary for collision detection
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0,width,height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
//	// Players collide with each other
//	public boolean checkEntityCollisions(float xOffset, float yOffset){
//		ArrayList<Entity> aaa = handler.gameState.aaa;
//		System.out.println("asdasdfad");
//		for(Entity e : aaa ){
//			
//			if(e.equals(this)) // So doesn't check collision with itself
//				continue;
//			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
//				System.exit(0); //end game if players collide
//				return true;
//		}
//		return false;
//	}
//	
//	
//	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
//		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
//	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
