/*********************************
 * Server example using
 * Socket Streams
 *********************************/
import java.io.*;
import java.util.Scanner;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class FileServerSS {

	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket();
			InetSocketAddress addr    = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
			System.out.println("Server is listening...");

			// Listens and creates new sockets to connect to clients
			while (true) {
				Socket newSocket = serverSocket.accept();
				InputStream in   = newSocket.getInputStream();
				System.out.println("\nNew Socket created: " + newSocket);

				System.out.println("------------------");
				System.out.println(" File contents:");
				System.out.println("------------------");
				String filecontents = "";
				Scanner s = new Scanner(in, "UTF-8").useDelimiter("\\A | \\n");
				while (s.hasNext())
					filecontents += s.next();
				System.out.println(filecontents);

				in.close();
				newSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
