/* ---------------------------------------------
 *  CLASS CPERSON
 * ---------------------------------------------*/

package ageCalc;

import commons.Commons;
import java.io.*;

public class CPerson {

	private String name;
	private Date birth;

	public CPerson() {
		name  = new String();
		birth = new Date();
	}

	public CPerson(String name, Date birth) {
		this.name  = name;
		this.birth = birth;
	}

	public void setName() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		name = br.readLine();
	}

	public void setBirth() {
		System.out.println("Date of birth:");
		System.out.print("Year: ");
		int y = Commons.readInt();

		System.out.print("Month: ");
		int m = Commons.readInt();

		System.out.print("Day: ");
		int d = Commons.readInt();

		birth.setDate(d, m, y);
	}

	public String getName() { return name;  }
	public Date getBirth()  { return birth; }

	public void print() {
		System.out.println("Name: " + name);
		System.out.println("Date of birth: " + birth);
	}
}
