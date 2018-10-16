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
    int clientID = -1;

    public UserThread(Server server, Socket socket, int i) {
        this.server = server;
        this.socket = socket;
        this.clientID = i;

    }

    public void run() {
        System.out.println("Accepted Client : Player " + clientID + " : Address - "
                + socket.getInetAddress().getHostName());
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            userName = input.readUTF();
            System.out.println("Player " + clientID + ": " + userName + " joined the server");
            server.sendChatToAll(userName + " joined the server.", this);


            boolean connect = true;
            while (connect) {
                String clientMessage = input.readUTF();
                server.sendChatToAll(userName + ": " + clientMessage, this);
                if(clientMessage.equalsIgnoreCase("quit")) {
                    server.sendChatToAll(userName + " disconnected from the server", this);
                    server.removeUser(this, userName);
                    socket.close();
                    connect = false;
                }
                if (clientMessage.equalsIgnoreCase("start")) {
                    startGame();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        Game game = new Game(server.getUsers());
        game.start();
    }
}
