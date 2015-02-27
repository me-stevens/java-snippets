/*********************************
 * Client example using
 * Socket Streams
 *********************************/
 
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ClientSocketStream {
	public static void main(String[] args) {

		try {
			Socket clientSocket    = new Socket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			InputStream is  = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			String message  = "message from client";
			os.write(message.getBytes());

			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
