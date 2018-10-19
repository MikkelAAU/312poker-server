package Main_Package;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerTest {
	private static int clientNo = 0;
	
	public static void main(String[] args) {

		new Thread(() -> {
			try {
				@SuppressWarnings("resource")
				ServerSocket serverSocket = new ServerSocket(8000);
				System.out.println("Loan Server started at " + new Date() + '\n');

				while (true) {
					Socket socketPlayer = serverSocket.accept();

					System.out.println("client number" + clientNo);
					clientNo++;

					// Display the client number
					InetAddress inetAddress = socketPlayer.getInetAddress();
					System.out.println("Connected to a client " + clientNo + " client name " + inetAddress.getHostName()
							+ " client address " + inetAddress.getHostAddress() + " at " + new Date() + '\n');

					new Thread(new HandleAClient(socketPlayer)).start();
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}).start();
	}

}

class HandleAClient implements Runnable {
	private Socket socket;

	public HandleAClient(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			// TODO Auto-generated method stub

			// Create data input and output streams
			DataInputStream isFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream osToClient = new DataOutputStream(socket.getOutputStream());
			
			osToClient.writeBytes("Welcome to the game");

			while (true) {
				
				

			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
