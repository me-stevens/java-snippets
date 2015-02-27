/*********************************************************
 *  EXAMPLE OF MULTITHREAD CLIENT USING STREAM SOCKETS
 *  The order in which messages are sent and received
 *  is fixed beforehand.
 *********************************************************/
import java.io.*;
import java.net.*;

public class CalcClient {

	public static void main(String[] args) {

		try {
			System.out.println("\nCreating client socket...");
			Socket clientSocket = new Socket();

			System.out.println("Connecting...");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			DataInputStream is  = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

			System.out.println("\nOperation: Addition");
			os.write("/".getBytes());

			System.out.println("Sending first operand...");
			os.writeDouble(130);

			System.out.println("Sending second operand...");
			os.writeDouble(1000);

			System.out.println("\nReceiving results...");
			double result = is.readDouble();
			System.out.println("Addition results: " + result);

			System.out.println("\nClosing client socket...");
			clientSocket.close();
			System.out.println("Finished.\n");

		} catch(IOException e) { e.printStackTrace(); }
	}
}
