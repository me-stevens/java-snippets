/* ---------------------------------------------
 *	CLASS CqRoots
 * 
 * Test Cases:
 * x < 0: (a, b, c) = (1, 1, 1)
 * x = 0: (a, b, c) = (1, 2, 1)
 * x > 0: (a, b, c) = (1, 3, 1)
 * ---------------------------------------------*/

package qRoots;

import commons.Commons;
import java.util.Scanner;

public class CqRoots {

	/* ---------------------------------------------
	 *     Discriminant calculation
	 * ---------------------------------------------*/
	public static double discriminant2n(double a, double b, double c) {
		double d =  b*b - 4*a*c ;
		return d;
	}

	/* ---------------------------------------------
	 *     Roots calculation
	 * ---------------------------------------------*/
	public static void calculateRoots(double a, double b, double c) {

		double d = 0, r0 = 0, r = 0;

		d = discriminant2n(a, b, c);

		/* ----------------------------- Check division by zero ----------------------------- */
		if (a != 0) {

			/* ------------------- Calculate the common part to all three cases ------------------ */
			r0 = - b / (2*a);

			/* ---------------- If the discriminant is zero there is only one solution ----------- */
			if (d == 0) {
				System.out.println("\nThe discriminant is zero. The only root of the quadratic equation is r = " + Commons.twoDecimals(r0) + ".\n");
			}
			/* ------------- If the discriminant is positive there are two solutions ------------- */
			else if (d > 0) {
				r = r0 + ( Math.sqrt(d) / (2*a) );
				System.out.print("\nThe discriminant is positive. The roots of the quadratic equation are r1 = " + Commons.twoDecimals(r) );

				r = r0 - ( Math.sqrt(d) / (2*a) );
				System.out.println(" and r2 = " + Commons.twoDecimals(r) + ".\n");
			}
			/* ----------- If the discriminant is negative the solutions are imaginary ----------- */
			else if (d < 0) {
				r = Math.sqrt( Math.abs(d) ) / (2*a);
				System.out.println("\nThe discriminant is negative. The roots of the quadratic equation are complex: r1 = " + Commons.twoDecimals(r0) + " + " + Commons.twoDecimals(r) + "i and r2 = " + Commons.twoDecimals(r0) + " - " + Commons.twoDecimals(r) + "i.\n" );
			}
		}

		/* -------------------------------------- If a = 0 --------------------------------------- */
		else {
			System.out.println("\nCan not calculate a division by zero.");	
		}
	}

	 
	/* ---------------------------------------------
	 *     MAIN
	 * ---------------------------------------------*/
	public static void main (String[] args) {

		System.out.println("\nIntroduce the coefficients of the quadratic equation");

		System.out.print("\na: ");
		double a = Commons.readDouble();

		System.out.print("\nb: ");
		double b = Commons.readDouble();

		System.out.print("\nc: ");
		double c = Commons.readDouble();

		System.out.println("\nCalculation of the roots:");

		calculateRoots(a, b, c);
	}
}
