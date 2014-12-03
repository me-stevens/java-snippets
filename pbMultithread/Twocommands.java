import java.io.*; 
import java.lang.ProcessBuilder;

public class Twocommands {
	
	public static void main (String [] args) {

		try {
			// What comes out of process1 is our inputStream
			Process process1   = new ProcessBuilder("ls", "-la").start();
		 	InputStream is1    = process1.getInputStream();
			BufferedReader br1 = new BufferedReader (new InputStreamReader(is1));

			// What goes into process2 is our outputStream
			Process process2  = new ProcessBuilder("tr", "'[eiou]'", "a").start();
			OutputStream os   = process2.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

			// Send the output of process1 to the input of process2
			String p1Output  = null;
			while ((p1Output = br1.readLine()) != null)
				bw.write(p1Output + "\n");

			bw.close();

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

		} catch (IOException e) {
			System.out.println("Command runtime error: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("General error: " + e.getMessage());
		}
	}
}
