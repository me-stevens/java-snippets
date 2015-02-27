/*********************************************************
 *  EXAMPLE OF MULTITHREAD SERVER USING STREAM SOCKETS
 *  The order in which messages are sent and received
 *  is fixed beforehand.
 *********************************************************/
import java.io.*;
import java.net.*;

public class CalcServer extends Thread {

	private Socket clientSocket;

	/*********************************************************
	 *  CONSTRUCTOR
	 *********************************************************/
	public CalcServer(Socket socket) {
		clientSocket = socket;
	}

	/*********************************************************
	 *  RUN() IMPLEMENTATION
	 *********************************************************/
	public void run() {
		try {
			System.out.println("Starting thread...");

			DataInputStream is  = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("\nEnter operation message...");

			byte[] buffer    = new byte[1];
			is.read(buffer);
			String operation = new String(buffer);
			System.out.println("Operation received: " + operation);

			if(operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {

				System.out.println("\nEnter first operand: ");
				double op1 = is.readDouble();
				System.out.println("First operand: " + op1);

				System.out.println("\nEnter second operand: ");
				double op2 = is.readDouble();
				System.out.println("Second operand: " + op2);

				System.out.println("\nComputing result");
				double result = 0;
				switch (operation) {
					case "+": result = op1 + op2; break;
					case "-": result = op1 - op2; break;
					case "*": result = op1 * op2; break;
					case "/":
						if (op2 != 0)
							result = op1 / op2; 
						else
							System.out.println("Sorry, I can't divide by zero");
						break;
				}

				System.out.println("\nSending result...");
				os.writeDouble(result);

			} else {
				System.out.println("Wrong operation. Please select +, -, * or /.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		System.out.println("Thread finished.\n");
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
				System.out.println("\nConection established.");
				CalcServer thread = new CalcServer(newSocket);
				thread.start();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Finished.\n");
	}
}
