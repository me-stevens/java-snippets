package pbMultithread;

import java.io.*; 

public class PassThread extends Thread {

	private InputStream  input;
	private OutputStream output;

	public PassThread(InputStream input, OutputStream output) {
		this.input  = input;
		this.output = output;
	}

	public void run() {
		try {
			byte[] buffer = new byte[1024];
			int read = 1;

			while  ( ( read = input.read(buffer, 0, buffer.length) ) != -1)
				output.write(buffer, 0, read);

			input.close();
			output.close();
		
		} catch (IOException e) {
			System.out.println("Command execution error: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("General error: " + e.getMessage());
		}
	}
}
