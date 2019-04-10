import java.awt.Graphics;

public abstract  class Entity {
	
	protected float x,y; //float for smooth movement of kart
	
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
