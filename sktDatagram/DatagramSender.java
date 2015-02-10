/*********************************
 * Sender-Receiver example using 
 * Datagrams.
 *********************************/
 
import java.io.*;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramSender {
	public static void main(String[] args) {

		try {
			InetAddress addr              = InetAddress.getByName("localhost");
			DatagramSocket datagramSocket = new DatagramSocket(5556, addr);
			byte[] message                = new byte[512];
			message                       = "token".getBytes();

			/*********************************
			 * SENDING
			 *********************************/
			DatagramPacket datagram = new DatagramPacket(
				message, 
				message.length, 
				addr, 
				5555);
			datagramSocket.send(datagram);

			/*********************************
			 * RECEIVING
			 *********************************/
			message  = new byte[512];
			datagram = new DatagramPacket(
				message, 
				512);
			datagramSocket.receive (datagram);

			SRManager srm = new SRManager();
			srm.printDatagram(datagram);

			datagramSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
