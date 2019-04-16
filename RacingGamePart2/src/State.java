import java.awt.Graphics;

public abstract class State {

	private static State currentState = null;
	
	//Game state manager
	public static void setState(State state){
		currentState = state;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public static State getState(){
		return currentState;
	}
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
