/* ---------------------------------------------
 *			CLASS CUBICEVAL
 * ---------------------------------------------*/

package cubicEval;

import commons.Commons;


class CubicEval {
	public static void main (String[] args) {

		double a, b, c, d, xmin, xmax, deltax;

		System.out.println("\nEvaluation of cubic expresions: Y = aX^3 + bX^2 + cX + d");
		System.out.println("\nINTRODUCE COEFICIENTS:");

		System.out.print("a: ");
		a = Commons.readDouble();

		System.out.print("b: ");
		b = Commons.readDouble();

		System.out.print("c: ");
		c = Commons.readDouble();

		System.out.print("d: ");
		d = Commons.readDouble();

		System.out.println("\nINTRODUCE RANGE OF CALCULATION (Xmin and Xmax):");

		System.out.print("Xmin: ");
		xmin = Commons.readDouble();

		System.out.print("Xmax: ");
		xmax = Commons.readDouble();

		System.out.println("\nINTRODUCE SIZE OF STEP:");

		System.out.print("deltaX: ");
		deltax = Commons.readDouble();

		CCubicEval f = new CCubicEval(a, b, c, d);

		System.out.println("\n\t X \t Y ");
		System.out.println("\t-------------");

		for (double x = xmin; x <= xmax; x += deltax) {
			double y = CCubicEval.evaluateX(f, x);
			System.out.println("\t" + Commons.twoDecimals(x) + "\t" + Commons.twoDecimals(y) );
		}

		System.out.println();
	} 
}
