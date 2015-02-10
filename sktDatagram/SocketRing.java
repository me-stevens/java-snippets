/*********************************
 * Datagram Ring
 *********************************/
import java.io.*;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SocketRing {

	public static void main(String[] args) {

		// Testing input parameters
		int nOfProcs = 0;
		int position = 0;
		try {
  			nOfProcs = Integer.parseInt(args[0]);
			position = Integer.parseInt(args[1]);

		} catch(NumberFormatException e) {
			System.out.println("Please introduce the number of sockets and the position.");
			return;
		}

		if (nOfProcs < 2) {
			System.out.println("The number of sockets must be at least 2.");
			return;
		}

		if (position < 0 || position > nOfProcs - 1) {
			System.out.println("Position out of range.");
			return;
		}

		// Assigning ports
		int startPort = 5550;
		int thisPort  = startPort + position;
		int nextPort  = thisPort + 1;
		if (position == nOfProcs - 1)
			nextPort  = startPort;

		// Printing stuff to the console
		RingManager rm = new RingManager();

		// Sending - receiving
		try {
			InetAddress addr              = InetAddress.getByName("localhost");
			DatagramSocket datagramSocket = new DatagramSocket(thisPort, addr);
			byte[] message                = new byte[512];
			DatagramPacket datagram       = null;

			/************************************
			 *  IF NOT FIRST, LISTEN AND
			 *  RECEIVE FROM POSITION - 1
 			 ************************************/
			if (position > 0) {
				datagram = new DatagramPacket(message, 512);
				datagramSocket.receive (datagram);
				rm.printDatagram(datagram, nOfProcs, position);
			}

			/*********************************
			 * NOW SEND TO POSITION + 1
			 *********************************/
			if (position == 0) {
				message = "token".getBytes();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}

			datagram = new DatagramPacket(
				message, 
				message.length, 
				addr, 
				nextPort);
			datagramSocket.send(datagram);

			/************************************
			 *  IF FIRST, LISTEN AND
			 *  RECEIVE FROM LAST POSITION
			 ************************************/
			if (position == 0) {
				datagram = new DatagramPacket(message, datagram.getLength());
				datagramSocket.receive (datagram);
				rm.printDatagram(datagram, nOfProcs, position);
			}

			datagramSocket.close();

		} catch (IOException | java.lang.IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
