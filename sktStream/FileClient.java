/*********************************************************
 *  EXAMPLE OF MULTITHREAD CLIENT USING STREAM SOCKETS
 *  The order in which messages are sent and received
 *  is fixed beforehand.
 *********************************************************/
import java.io.*;
import java.net.*;

public class FileClient {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("\nUsage: java FileClient filename (with no extension)\n");
			System.exit(0);
		}

		String filename = args[0];

		try {
			System.out.println("\nCreating client socket...");
			Socket clientSocket = new Socket();

			System.out.println("Connecting...");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			InputStreamReader is  = new InputStreamReader(clientSocket.getInputStream(), "UTF-8");
			OutputStreamWriter os = new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8");

			System.out.println("\nSending filename...");
			os.write(filename, 0, filename.length());
			os.flush();
			clientSocket.shutdownOutput();

			System.out.println("\n-----------------------------------------------------");
			System.out.println("   Receiving file " + filename + ".txt");
			System.out.println("   Contents of file:");
			System.out.println("-----------------------------------------------------");
			int character;
			while ((character = is.read()) != -1) {
            	char ch = (char) character;
            	System.out.print(ch);
			}

			System.out.println("\nClosing client socket...");
			clientSocket.close();

			System.out.println("Finished.\n");

		} catch(IOException e) { 
			e.printStackTrace(); 
		}
	}
}
