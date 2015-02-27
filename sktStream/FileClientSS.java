/*********************************
 * Client example using
 * Socket Streams
 *********************************/
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FileClientSS {
	public static void main(String[] args) {
		OutputStream os   = null;
		BufferedReader br = null;

		try {
			Socket clientSocket    = new Socket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);
			System.out.println("Client established connection\n");

			os = clientSocket.getOutputStream();			
			br = new BufferedReader(new FileReader("file.txt"));

			String fileline   = br.readLine();
			while (fileline  != null) {
				fileline += "\n";
				os.write(fileline.getBytes());
				fileline      = br.readLine();
			}

			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();			

		} finally {

			try {
				if (br != null)
					br.close();
				if (os != null)
					os.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
