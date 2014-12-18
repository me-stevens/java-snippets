import java.io.IOException;

public class RuntimeProcess {

	public static void main(String[] args) throws IOException {

		if (args.length <= 0) {
			System.err.println("\nI need a command to run!\n");
			System.exit(-1);
		}

		Runtime runtime = Runtime.getRuntime();

		try {
			Process process = runtime.exec(args);
			System.out.println("Press Enter to continue.");
			System.in.read();
			process.destroy();

		}catch(IOException ex){
			System.err.println("\nE/S exception!\n");
			System.exit(-1);
		}
	}
}
