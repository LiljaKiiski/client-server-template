import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server Class
 * A class to represent a server
 * Creates threads for new clients and gives each client a client handler
 */
public class Server {

	//List of all clients
	private static ArrayList<ClientHandler> clients = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║     Server has started...   ║");
		System.out.println("╚═════════════════════════════╝");
		System.out.println();

		ServerSocket listener = new ServerSocket(1234);		
	
		//Constantly listen for clients until program terminates	
		while (true) {
			System.out.println("Server: Waiting for client connection...\n");
			Socket client = listener.accept(); 

			//Client found once listener accepts client
			System.out.println("Server: Found a client! + \n");

			//Create thread for handling specific client. Client name is given by number
			ClientHandler clientThread = new ClientHandler("Client: " + clients.size(), client, clients);

			//Add new clientThread to clients
			clients.add(clientThread);

			//Gives clientHandlers the new client list
			for (int x = 0; x < clients.size(); x++) {
				clients.get(x).setClients(clients);
			}

			clientThread.startThread();	
		}
		 
	}
}
