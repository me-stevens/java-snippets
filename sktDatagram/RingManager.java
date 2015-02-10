/*********************************************
 * Socket Ring manager
 * Opens as many terminals as sockets
 * in the ring.
 *********************************************/

import java.io.*;
import java.net.DatagramPacket;

public class RingManager {

	/****************************************************************
	 * MAIN
	 ****************************************************************/
	public static void main(String args[]) throws IOException {

		// Avoiding static definitions
		RingManager csm = new RingManager();

		// Proc.exec() parameters
		String[] envp = null;
		File dir      = new File("/home/admin/Documents");

		int nOfProcs = 3;
		for(int i=0; i<nOfProcs; i++) {
			String[] cmdarray = {"gnome-terminal", "--geometry=100x15+1000+" + i*300,  "-x", "sh", "-c", "java SocketRing " + nOfProcs + " " + i + "; exec $SHELL"};
			csm.launch(cmdarray, envp, dir);
		}
	}

	/****************************************************************
	 * PROCESS LAUNCHER
	 ****************************************************************/
	public void launch(String[] cmdarray, String[] envp, File dir) {

		try {
			Runtime rt   = Runtime.getRuntime();
			Process proc = rt.exec(cmdarray, envp, dir);

			InputStreamReader in = new InputStreamReader(proc.getInputStream());
			BufferedReader br    = new BufferedReader(in);
			String line          = null;
			while((line = br.readLine()) != null )
				System.out.println(line);

			if (br != null)
				br.close();
			if (in != null) 
				in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/****************************************************************
	 * PRINTING FUNCTION
	 ****************************************************************/
	public void printDatagram(DatagramPacket datagram, int nOfProcs, int position) {
		try {
			System.out.println("--------------------------");
			System.out.println(" SOCKET " + position + " IN A RING OF " + nOfProcs);
			if (position == 0)
				System.out.println(" Message from socket " + (nOfProcs-1) + ":");
			else
				System.out.println(" Message from socket " + (position-1) + ":");
			System.out.println("--------------------------");
			String string = new String(datagram.getData(), "UTF-8");
			System.out.println(" Text:   " + string);
			System.out.println(" Size:   " + datagram.getLength());
			System.out.println(" Offset: " + datagram.getOffset());
			System.out.println(" Host:   " + datagram.getAddress().getHostName());
			System.out.println(" IP:     " + datagram.getAddress().getHostAddress());
			System.out.println(" Port:   " + datagram.getPort());
			System.out.println(" Full:   " + datagram.getSocketAddress().toString() + "\n");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
