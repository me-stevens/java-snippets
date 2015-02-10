/*********************************************
 * Server-Receiver manager
 * Opens a window to run the receiver.
 * Runs a sender in current terminal.
 *********************************************/

import java.io.*;
import java.net.DatagramPacket;

public class SRManager {

	/****************************************************************
	 * MAIN
	 ****************************************************************/
	public static void main(String args[]) throws IOException {

		// Avoiding static definitions
		SRManager csm = new SRManager();

		// Proc.exec() parameters
		String[] envp = null;
		File dir      = new File("/home/admin/Documents");

		String[] cmdarray1 = {"gnome-terminal", "--geometry=100x50+1000+0", "-x", "sh", "-c", "java DatagramReceiver; exec $SHELL"};
		csm.launch(cmdarray1, envp, dir);

		try {
			Thread.sleep(500);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		String[] cmdarray2 = {"java", "DatagramSender"};
		csm.launch(cmdarray2, envp, dir);
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
	public void printDatagram(DatagramPacket datagram) {
		try {
			System.out.println("-----------------------");
			System.out.println(" Message from socket:");
			System.out.println("-----------------------");
			String string = new String(datagram.getData(), "UTF-8");
			System.out.println(" Text: " + string);
			System.out.println(" Size: " + datagram.getLength());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
