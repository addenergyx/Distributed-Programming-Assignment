import java.awt.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private Game game;
    private ArrayList<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

    public GameServer(Game game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            
//            String message = new String(packet.getData());
//            System.out.println("CLIENT [" + packet.getAddress().getHostAddress() + ":" + packet.getPort() + "]> "
//            		+ message);
//            if (message.trim().equalsIgnoreCase("ping")) {
//            	System.out.println("Returning pong");
//                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
//            }
            
        }
    }

	private void parsePacket(byte[] data, InetAddress address, int port) {

        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
        
		switch(type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println( "["+address.getHostAddress()+":"+port+"] " + ((Packet00Login)packet).getUsername()+ " has connected...");
			PlayerMP player = new PlayerMP(game, 750, 350, ((Packet00Login) packet).getUsername() ,Assets.player2_move, address, port);
			
			this.addConnection(player, (Packet00Login)packet);
			
//			if(player != null ) {
//				this.connectedPlayers.add(player);
//				
//				game.entityManager.addEntity(player);
//				game.player = player;
//			}
//			
//			if(address.getHostAddress().equalsIgnoreCase("127.0.0.1")) {
//				player = new PlayerMP(game, 700, 500, ((Packet00Login) packet).getUsername() ,Assets.player1_move, "wasd", address, port);
//			} else {
//				player = new PlayerMP(game, 700, 500, ((Packet00Login) packet).getUsername() ,Assets.player2_move, "arrows", address, port);
//			}
//			
//			if(player != null ) {
//				this.connectedPlayers.add(player);
//				game.entityManager.addEntity(player);
//				game.player = player;
//		
//			}
			
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println( "["+address.getHostAddress()+":"+port+"] " + ((Packet01Disconnect)packet).getUsername()+ " has left the game...");
			
			this.removeConnection((Packet01Disconnect)packet);
		
			break;
			
		case MOVE:
			packet = new Packet02Move(data);
			//System.out.println(((Packet02Move)packet).getUsername()+" has moved to " +((Packet02Move)packet).getX()+","+ ((Packet02Move)packet).getY());
			this.handleMove((Packet02Move)packet);
		}
		
	}
	
	public void removeConnection(Packet01Disconnect packet) {

		//Player player = getPlayerMP(packet.getUsername());
		this.connectedPlayers.remove(getPlayerMPId(packet.getUsername()));
		packet.writeData(this);
		
	}
	
	public PlayerMP getPlayerMP(String username) {
		for(PlayerMP player: this.connectedPlayers) {
			if(player.getUsername().equals(username)){
				return player;
			}
		}
		return null;
	}
	
	public int getPlayerMPId(String username) {
		int id = 0;
		for(PlayerMP player: this.connectedPlayers) {
			if(player.getUsername().equals(username)){
				break;
			}
			id++;
		}
		return id;
	}

	public void addConnection(PlayerMP player, Packet00Login packet) {
		boolean alreadyConnected = false;
		
		for (PlayerMP p : this.connectedPlayers) {
			if (player.getUsername().equalsIgnoreCase(p.getUsername())) {
				if(p.ipAddress == null) {
					p.ipAddress = player.ipAddress;
				}
				
				if (p.port == -1) {
					p.port = player.port;
				}
				
				alreadyConnected = true;
			
			} else {
				//Packet00Login loginPacket = new Packet00Login(player.getUsername());
				sendData(packet.getData(), p.ipAddress, p.port); // Tells current player about new player
				
				packet = new Packet00Login(p.getUsername(), p.x, p.y);
				sendData(packet.getData(), player.ipAddress, player.port); // Tells new player about current player
			}
		}
		
		if(!alreadyConnected) {
			this.connectedPlayers.add(player);
			//packet.writeData(this);
		}
		
	}
	
    
    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void sendDataToAllClients(byte[] data) {
		for (PlayerMP p : connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}
	}
	
	private void handleMove(Packet02Move packet) {
		if (getPlayerMP(packet.getUsername()) != null) {
			int index = getPlayerMPId(packet.getUsername());
			PlayerMP player = this.connectedPlayers.get(index);
			player.x = packet.getX();
			player.y = packet.getY();
			player.setCurrentImageIndex(packet.getCurrentImageIndex()); // Player direction information
			
			packet.writeData(this);
		}
	}
}

