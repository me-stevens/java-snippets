package pbMultithread;

import java.io.*;

public class TwoCommands2 {

	public static void main(String[] args) throws IOException, Exception {

		// What comes out of process1 is our inputStream
		Process process1 = new ProcessBuilder("ls", "-la").start();
		InputStream is1  = process1.getInputStream();

		// What goes into process2 is our outputStream
		Process process2 = new ProcessBuilder("tr", "'[eiou]'", "a").start();
		OutputStream os  = process2.getOutputStream();

		// Send the output of process1 to the input of process2
		PassThread wrapper = new PassThread( is1, os );
		new Thread(wrapper).start();

		// Synchronization
		int finish = process2.waitFor();
		System.out.println(finish);

		// What comes out of process2 is our inputStream			
		InputStream is2    = process2.getInputStream();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));

		String combOutput  = null;
		while ((combOutput = br2.readLine()) != null)
			System.out.println(combOutput);

		os.close();
		is1.close();
		is2.close();
	}
}
