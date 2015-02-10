/*********************************
 * Receptor example using 
 * Datagrams. Create a socket
 * and assign it an address
 *********************************/
 
import java.io.*;
import java.net.InetSocketAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReceptor {
	public static void main(String[] args) {
	
		try {
			InetSocketAddress addr        = new InetSocketAddress("localhost", 5555);
			DatagramSocket datagramSocket = new DatagramSocket(addr);
			byte[] message                = new byte[25];

			// Create and receive datagram for receiving		
			DatagramPacket datagram       = new DatagramPacket(
				message, 
				25);
			datagramSocket.receive (datagram);

			System.out.println("\nReceived " + new String(message) + "\n");

			datagramSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
