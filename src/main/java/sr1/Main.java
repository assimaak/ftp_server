package sr1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) throws Exception {

		try (ServerSocket server = new ServerSocket(21)) {

			System.out.println("Waiting...");
			while (true) {
				Socket client = server.accept();
				System.out.println("Client connection received from " + client.getInetAddress().toString());
				Runnable threadClient = new ThreadClient(client);
				new Thread(threadClient).start();
				System.out.println("threadClient for client's ip (" + client.getInetAddress().getHostAddress() + ") ended.");
			}
		} catch (IOException e) {
			System.err.println("Cannot start FTP server : port number already used.");
		}
	}
}
