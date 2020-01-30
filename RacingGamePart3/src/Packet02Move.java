import java.awt.image.BufferedImage;

public class Packet02Move extends Packet {

    private String username;
    private float x,y;
    private int currentImageIndex;

    public Packet02Move(byte[] data) {
        super(02);
        String[] dataArray = readData(data).split(",");
        this.username = dataArray[0];
        this.x = Float.parseFloat(dataArray[1]);
        this.y = Float.parseFloat(dataArray[2]);
        this.currentImageIndex =  Integer.parseInt(dataArray[3]);
    }

    //public Packet02Move(String username, float x, float y) {
    public Packet02Move(String username, float x, float y, int currentImageIndex) {
        super(02);
        this.username = username;
        this.x = x;
        this.y = y;
        this.currentImageIndex = currentImageIndex;
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        //return ("02" + this.username + ","+this.x +","+this.y).getBytes();
        return ("02" + this.username + ","+this.x +","+this.y+","+this.currentImageIndex).getBytes();
    }

    public String getUsername() {
        return username;
    }

    public float getX() {
    	return this.x;
    }
    
    public float getY() {
    	return this.y;
    }

	public int getCurrentImageIndex() {
		return currentImageIndex;
	}
    
}