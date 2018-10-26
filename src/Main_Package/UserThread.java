package Main_Package;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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
	private ArrayList<Card> userHand;
	private boolean folded;

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
	
	public ArrayList<Card> getUserHand() {
		return userHand;
	}
	
	public boolean isFolded() {
		return folded;
	}
	
	public void setFolded(boolean folded) {
		this.folded = folded;
	}
	
	public void sendCard(String value, String suit) {
		try {
			output.writeUTF(value);
			output.writeUTF(suit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int readInt() {
		int value = 0;
		try {
			value = input.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public String readString() {
		String string = "default";
		try {
			string = input.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
	
	public void sendBoolean(boolean condition) {
		try {
			output.writeBoolean(condition);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
