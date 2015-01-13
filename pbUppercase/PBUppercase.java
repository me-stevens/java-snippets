import java.util.Scanner;
import java.io.IOException; 

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class PBUppercase {
	
	public static void main(String[] command) {
		String textIn, textOut;
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("\nWrite something in lowercase and I'll convert it into uppercase: ");
			textIn = sc.nextLine();

			Process process   = new ProcessBuilder(command).start();
			OutputStream os   = process.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

			bw.write(textIn);
			bw.close();

		 	InputStream is    = process.getInputStream();
			BufferedReader br = new BufferedReader (new InputStreamReader(is));

			while ((textOut = br.readLine()) != null) {
				System.out.println(textOut + "\n");
			}

			is.close();

		} catch (IOException e) {
			System.out.println("\nThere was an error running your command. Description:" + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nThere was a general error:" + e.getMessage() + "\n");
		}
	}
}
