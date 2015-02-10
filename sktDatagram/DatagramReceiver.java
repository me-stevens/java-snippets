/*********************************
 * Receiver-Sender example using 
 * Datagrams.
 *********************************/
 
import java.io.*;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReceiver {
	public static void main(String[] args) {
	
		try {
			InetAddress addr              = InetAddress.getByName("localhost");
			DatagramSocket datagramSocket = new DatagramSocket(5555, addr);
			byte[] message                = new byte[512];

			/*********************************
			 * RECEIVING
			 *********************************/
			DatagramPacket datagram       = new DatagramPacket(
				message, 
				512);
			datagramSocket.receive (datagram);

			SRManager srm = new SRManager();
			srm.printDatagram(datagram);

			/*********************************
			 * SENDING
			 *********************************/
			message  = "received".getBytes();
			datagram = new DatagramPacket(
				message, 
				message.length, 
				addr, 
				5556);
			datagramSocket.send(datagram);

			datagramSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
