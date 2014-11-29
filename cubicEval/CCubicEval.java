/* ---------------------------------------------
 *   CLASS CCUBICEVAL
 * ---------------------------------------------*/

package cubicEval;

import commons.Commons;


class CCubicEval {
	private double a, b, c, d;

	public CCubicEval(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public static double evaluateX(CCubicEval f, double x) {
		double y = f.a*Math.pow(x,3) + f.b*Math.pow(x,2) + f.c*x +  f.d;
		return y;
	}

}

