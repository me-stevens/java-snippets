/*********************************************************
 *  EXAMPLE OF TIME SERVER USING DATAGRAM SOCKETS
 *********************************************************/
import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {

	public static void main(String[] args) {

		System.out.println("\nLoading Time Sever");
		DatagramSocket datagramSocket = null;

		try {
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			datagramSocket         = new DatagramSocket(addr);
		} catch(SocketException e) {
			e.printStackTrace();
		}

		while(datagramSocket != null) {

			try{
				System.out.println("Listening...\n");

				byte[] buffer            = new byte[4];
				DatagramPacket datagram1 = new DatagramPacket(buffer, 4);
				datagramSocket.receive(datagram1);

				String message         = new String(datagram1.getData());
				InetAddress clientAddr = datagram1.getAddress();
				int clientPort         = datagram1.getPort();

				System.out.println("Message received from " + clientAddr + ", port " + clientPort);
				System.out.println("Message contents: " + message);

				if (message.equals("full")) {
					System.out.println("Sending response...");

					Date d                   = new Date(System.currentTimeMillis());
					byte[] response          = d.toString().getBytes();
					DatagramPacket datagram2 = new DatagramPacket(
						response,
						response.length,
						clientAddr,
						clientPort);
					datagramSocket.send(datagram2);

					System.out.println("\nMessage sent.");
				} else {
					System.out.println("\nI didn't understand the message.");
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Finished.\n");
	}
}
