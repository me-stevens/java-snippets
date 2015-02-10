/*********************************
 * Emitter example using 
 * Datagrams. Create a socket
 * with no address, addr is server's
 *********************************/
 
import java.io.*;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramEmitter {
	public static void main(String[] args) {

		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			String message                = "message from emitter";
			InetAddress addr              = InetAddress.getByName("localhost");

			System.out.println("\nSending " + message + "\n");

			// Create and send datagram	for sending		
			DatagramPacket datagram       = new DatagramPacket(
				message.getBytes(), 
				message.getBytes().length, 
				addr, 
				5555);
			datagramSocket.send(datagram);

			datagramSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
