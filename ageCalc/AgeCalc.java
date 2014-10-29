/* ---------------------------------------------
 *   CLASS AGECALC
 * ---------------------------------------------*/

package ageCalc;

import commons.Commons;
import java.io.*;
   
public class AgeCalc {

	public static void main(String[] args) throws IOException {

		System.out.println("\nCreating a new person.\n");
		CPerson p = new CPerson();
		p.setName();
		p.setBirth();

		System.out.println("\nIntroduce a date and I'll tell you how old that person will be:");
		System.out.print("Day: ");
		int d = Commons.readInt();

		System.out.print("Month: ");
		int m = Commons.readInt();

		System.out.print("Year: ");
		int y = Commons.readInt();

		Date date = new Date();
		date.setDate(d, m, y);

		int age = (int)( date.substractDate( p.getBirth() ) / 365.0 );
		System.out.println("\n" + p.getName() + " will be " + age + " years old in " + date.getYear() + ".\n");
	}
}
