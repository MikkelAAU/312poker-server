package Main_Package;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class UserThread extends Thread {
	private Server server;
	private Socket socket;
	private String userName;
	private DataOutputStream output;
	private DataInputStream input;
	private boolean readyCheck;
	private boolean blindBet;
	private boolean folded;
	private int bettingCash;
	private int myBet;
	private ArrayList<Card> userHand;

	public UserThread(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		bettingCash = 2000;
		userHand = new ArrayList<>();
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

	public void setBettingCashWin(int bettingCash) {
		this.bettingCash += bettingCash;
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
	public HandResult decideHand() {
		Collections.sort(userHand, Collections.reverseOrder(new SortByValue()));
		ArrayList<HandResult> handResults = new ArrayList<>();
		handResults = Rules.checkKinds(userHand, handResults);
		handResults = Rules.checkStraight(userHand, handResults);
		handResults = Rules.checkSuit(userHand, handResults);

		for (int i = 0; i < handResults.size(); i++) {
			for (int j = i+1; j < handResults.size(); j++) {
				if (handResults.get(i).getValue() == handResults.get(j).getValue()) {
					if (userHand.get(handResults.get(i).getPosition()).getCardValue().getCardValue() > userHand
							.get(handResults.get(j).getPosition()).getCardValue().getCardValue()) {
						handResults.add(new HandResult(handResults.get(i).getPosition(), 2));
						handResults.remove(j);
						handResults.remove(i);
					} else {
						handResults.add(new HandResult(handResults.get(j).getPosition(), 2));
						handResults.remove(j);
						handResults.remove(i);
					}			
				}
			}
		}

		for (int i = 0; i < handResults.size(); i++) {
			if(handResults.get(i).getValue() == 1 || handResults.get(i).getValue() == 2) {
				for (int j = 0; j < handResults.size(); j++) {
					if(handResults.get(j).getValue() == 3) {
						handResults.add(new HandResult(handResults.get(j).getPosition(), 6));
						if(i>j) {
							handResults.remove(i);
							handResults.remove(j);
						} else {
							handResults.remove(j);
							handResults.remove(i);
						}
					}
				}
			}
		}

		for (int i = 0; i < handResults.size(); i++) {
			if(handResults.get(i).getValue() == 1 || handResults.get(i).getValue() == 4) {
				for (int j = 0; j < handResults.size(); j++) {
					if(handResults.get(j).getValue() == 5 && handResults.get(i).getPosition() == handResults.get(j).getPosition()) {
						handResults.add(new HandResult(handResults.get(j).getPosition(), 8));
						if(i>j) {
							handResults.remove(i);
							handResults.remove(j);
						} else {
							handResults.remove(j);
							handResults.remove(i);
						}
					}
				}
			}
		}

		HandResult max = new HandResult(0, 0);
		for (int i = 0; i < handResults.size(); i++) {
			if(handResults.get(i).getValue() > max.getValue())
				max = handResults.get(i);
		}
		return max;

	}

}
