import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static ArrayList<ClientHandler> clients = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		ServerSocket listener = new ServerSocket(1234);		
	
		//Constantly listen for clients until program terminates	
		while (true) {
			System.out.println("Server: Waiting for client connection...");
			Socket client = listener.accept(); 

			//Client found once listener accepts client
			System.out.println("Server: Found a client!");

			//Create thread for handling specific client. Client name is given by number
			ClientHandler clientThread = new ClientHandler("Client: " + clients.size(), client, clients);

			//Add new clientThread to clients
			clients.add(clientThread);

			//Gives clientHandlers the new client list
			for (int x = 0; x < clients.size(); x++) {
				clients.get(x).handlersClients = clients;
			}

			clientThread.thread.start();	
		}
		 
	}
}
