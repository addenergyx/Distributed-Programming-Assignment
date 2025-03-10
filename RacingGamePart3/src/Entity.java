import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity {
	
	protected Game game;
	protected float x,y; //float for smooth movement of kart
	protected int width, height;
	protected Rectangle bounds; //player boundary for collision detection
	
	public Entity(Game game, float x, float y, int width, int height) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0,width,height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
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
