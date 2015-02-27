/*********************************************************
 *  EXAMPLE OF MULTITHREAD SERVER USING STREAM SOCKETS
 *  The order in which messages are sent and received
 *  is fixed beforehand.
 *********************************************************/
import java.io.*;
import java.net.*;

public class FileServer extends Thread {

	private Socket clientSocket;
	private String filesdir;

	/*********************************************************
	 *  CONSTRUCTOR
	 *********************************************************/
	public FileServer(Socket socket) {
		clientSocket = socket;
		filesdir     = "./Files";
	}

	/*********************************************************
	 *  RUN() IMPLEMENTATION
	 *********************************************************/
	public void run() {
		try {
			InputStreamReader is  = new InputStreamReader(clientSocket.getInputStream(), "UTF-8");
			OutputStreamWriter os = new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8");

			// Reading filename
			BufferedReader br = new BufferedReader(is);
			String filename   = br.readLine();
			String filepath   = filesdir + "/" + filename + ".txt";
			File file         = new File(filepath);

			// Sending file
			if(file.exists() && !file.isDirectory()) {

				System.out.println("\n-----------------------------------------------------");
				System.out.println("   Sending file " + filename + ".txt");
				System.out.println("   Contents of file:");
				System.out.println("-----------------------------------------------------");
				br = new BufferedReader(new FileReader(filepath));
				String line  = null;
				while ((line = br.readLine()) != null) {
					for(int i=0; i<line.length(); i++) {
						os.write(line.charAt(i));
						os.flush();
						System.out.print(line.charAt(i));
					}
					os.write('\n');
					os.flush();
					System.out.println();
				}
				clientSocket.shutdownOutput();

			} else {
				System.out.println("\nWrong name: File doesn't exist in the server or is a directory");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*********************************************************
	 *  MAIN
	 *********************************************************/
	public static void main(String[] args) {

		System.out.println("\nCreating server socket...");
		ServerSocket serverSocket = null;

		try {
			System.out.println("Binding...");
			serverSocket           = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
		} catch(IOException e) {
			e.printStackTrace();
		}

		System.out.println("Listening...");
		while(serverSocket != null) {
			try {
				Socket newSocket  = serverSocket.accept();
				FileServer thread = new FileServer(newSocket);
				thread.start();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Finished.\n");
	}
}
