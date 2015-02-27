/*********************************
 * Server example using
 * Socket Streams
 *********************************/

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerSocketStream {
	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket();
			InetSocketAddress addr    = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);

			// Create new socket and block server socket, connect to client, unblock
			Socket newSocket = serverSocket.accept();
			InputStream is   = newSocket.getInputStream();
			OutputStream os  = newSocket.getOutputStream();
			
			byte[] message   = new byte[25];
			is.read(message);
			System.out.println(new String(message, "UTF-8"));

			newSocket.close();
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
