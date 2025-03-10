import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity {
	
	protected Handler handler;
	protected float x,y; // Float for smooth movement of kart
	protected int width, height;
	protected Rectangle bounds; // Player boundary for collision detection
	
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
