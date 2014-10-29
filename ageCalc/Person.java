/* ---------------------------------------------
 *   CLASS PERSON
 * ---------------------------------------------*/

package ageCalc;

import java.io.*;

public class Person {

	public static void main(String[] args) {
	
		CPerson p = new CPerson();

		System.out.println();
		System.out.println(p.getName());
		System.out.println("Date of birth");
		System.out.println("Day:   " + p.getBirth().getDay());
		System.out.println("Month: " + p.getBirth().getMonth());
		System.out.println("Year:  " + p.getBirth().getYear());
	}
}
