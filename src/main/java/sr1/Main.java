package sr1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Main.
 */
public class Main {
	public static void main(String[] args) throws Exception {
		int port = 21;
		if (args.length>0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Wrong number of port. Integer only.");
			}
		}
		try (ServerSocket server = new ServerSocket(port)) {

			System.out.println("Waiting...");
			while (true) {
				Socket client = server.accept();
				System.out.println("Connection received from client of ip " + client.getInetAddress().toString());
				Runnable threadClient = new ThreadClient(client);
				new Thread(threadClient).start();
				System.out.println("Connection with client of ip (" + client.getInetAddress().getHostAddress() + ") ended.");
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
