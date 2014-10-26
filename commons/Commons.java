/* ---------------------------------------------
 *              CLASS COMMONS
 * ---------------------------------------------*/

package commons;

import java.util.Scanner;
import java.io.*;
import java.util.Locale;


public final class Commons {

	/* ---------------------------------------------
	 *     Two decimals and decimal point
	 * ---------------------------------------------*/
	public static String twoDecimals(double r) {
		String sr = String.format(Locale.US, "%.2f", r);
		return sr;
	}

	/* ---------------------------------------------
	 *  int readInt()
	 * ---------------------------------------------*/
	public static int readInt() {
		Scanner reader = new Scanner(System.in);
		boolean b = true;
		int d = 0;

		do {
			String s = reader.nextLine();
			Scanner sc = new Scanner(s);

			if ( sc.hasNextInt() ) {
				b = false;
				d = Integer.parseInt(s);
			}
			else {
				b = true;
				System.out.print("ERROR - You must introduce a number: ");
			}

		} while (b);

		return d;
	}

	/* ---------------------------------------------
	 *   double readDouble()
	 * ---------------------------------------------*/
	public static double readDouble() {
		Scanner reader = new Scanner(System.in);
		boolean b = true;
		double  d = 0;

		do {
			String s = reader.nextLine();
			Scanner sc = new Scanner(s);

			if ( sc.hasNextDouble() ) {
				b = false;
				d = Double.parseDouble(s);
			}
			else {
				b = true;
				System.out.print("ERROR - You must introduce a number: ");
			}

		} while (b);

		return d;
	}
}
