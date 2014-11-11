/* ---------------------------------------------
 *			CLASS ASCII
 * ---------------------------------------------*/

package ascii;

import java.io.*;

class Ascii {

	public static void main (String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nIntroduce some text: ");
		String s = br.readLine();

		for (int i = 0; i < s.length(); i++) {
			System.out.println("Character '" + s.charAt(i) + "', ASCII code = " + (int)s.charAt(i) +"\n");
		}
	}
}
