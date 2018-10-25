package Main_Package;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserThread extends Thread {
    public Player player;
    private Server server;
    private Socket socket;
    private String userName;
    private DataOutputStream output;
    private DataInputStream input;
    private boolean readyCheck;
	private int bettingCash;
	private int myBet;

    public UserThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
		bettingCash = 2000;
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            userName = input.readUTF();
            System.out.println(userName + " joined the server");
            server.sendToAll(userName + " joined the server.", this);

            readyCheck = true;
            while (readyCheck) {
                String clientMessage = input.readUTF();
                server.sendToAll(userName + ": " + clientMessage, this);
                if(clientMessage.equalsIgnoreCase("quit")) {
                    server.sendToAll(userName + " disconnected from the server", this);
                    server.removeUser(this, userName);
                    socket.close();

                    readyCheck = false;

                }
                if(clientMessage.equalsIgnoreCase("ready")) {
                    server.sendToAll(userName + " is ready", this);
                    readyCheck = false;
                    server.startGame();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //methods
    
    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void sendInt(int number) {
		try {
			output.writeInt(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
    //setter and getter
	
    public String getUserName() {
        return userName;
    }
	
	public void setMyBet(int myBet) {
		this.myBet = myBet;
	}
	public int getMyBet() {
		return myBet;
	}
	
	public void setBettingCash(int bettingCash) {
		this.bettingCash -= bettingCash;
	}
	public int getBettingCash() {
		return bettingCash;
	}
	
	public boolean isReadyCheck() {
		return readyCheck;
	}
}
