import java.io.IOException;
import java.util.Arrays;

public class RunProcess {
	
	public static void main(String[] args) throws IOException {
	
		if (args.length <= 0) {
			System.err.println("\nI need a command to run!\n");
			System.exit(-1);
		}

		ProcessBuilder pb   = new ProcessBuilder(args);

		try {
			Process process = pb.start();
			int procretrn   = process.waitFor();
			System.out.println("\nRunning " + Arrays.toString(args) + " returns " + procretrn + "\n");

		} catch (IOException ex){
			System.err.println("\nE/S exception!\n");
			System.exit(-1);

		} catch (InterruptedException ex){
			System.err.println("\nThe child process crashed.\n");
			System.exit(-1);
		}
	}
}
