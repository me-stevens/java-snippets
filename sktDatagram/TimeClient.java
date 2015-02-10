/*********************************************************
 *  EXAMPLE OF TIME CLIENT USING DATAGRAM SOCKETS
 *********************************************************/
import java.io.*;
import java.net.*;

public class TimeClient {

	public static void main(String[] args) {

		try {
			System.out.println("\nCreating datagram socket...");
			DatagramSocket datagramSocket = new DatagramSocket();

			System.out.println("Sending petition to the server...");
			String message           = "full";
			InetAddress serverAddr   = InetAddress.getByName("localhost");
			DatagramPacket datagram1 = new DatagramPacket(
				message.getBytes(),
				message.getBytes().length,
				serverAddr,
				5555);
			datagramSocket.send(datagram1);

			System.out.println("\nMessage sent.");
			System.out.println("Receiving response...");

			byte[] response          = new byte[100];
			DatagramPacket datagram2 = new DatagramPacket(response, response.length);
			datagramSocket.receive(datagram2);

			System.out.println("\nMessage received: " + new String(response));

			System.out.println("\nClosing datagram socket.");
			datagramSocket.close();
			System.out.println("Finished.\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
