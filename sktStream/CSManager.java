/*********************************************
 * Client-Server manager
 * Opens a window to run a file server.
 * Runs a file client in current terminal.
 *
 * Compile: javac CSManager.java
 * Run:     java  CSManager
 *********************************************/

import java.io.*;

public class CSManager {

	/****************************************************************
	 * MAIN
	 * Using String[] for commands with quotes.
	 * Term-cmd: gnome-terminal -x sh -c "java FileServerSS; exec $SHELL"
	 * "--geometry" sets the size and position of the window
	 * "exec $SHELL" recovers prompt
	 ****************************************************************/
	public static void main(String args[]) throws IOException {

		// Avoiding static definitions
		CSManager csm = new CSManager();

		// Proc.exec() parameters
		String[] envp = null;
		File dir      = new File("/home/admin/Documents/");

		String[] cmdarray1 = {"gnome-terminal", "--geometry=100x50+1000+0", "-x", "sh", "-c", "java FileServerSS; exec $SHELL"};
		csm.launch(cmdarray1, envp, dir);

		String[] cmdarray2 = {"java", "FileClientSS"};
		csm.launch(cmdarray2, envp, dir); //"java FileServerSS"
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

}
