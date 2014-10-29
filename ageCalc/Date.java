/* ---------------------------------------------
 *   CLASS DATE
 * ---------------------------------------------*/

package ageCalc;


public class Date {

	private int dd;
	private int mm;
	private int yy;
	 
	public Date() {
		dd = 1;
		mm = 1;
		yy = 1;
	}
	 
	/* ---------------------------------------------
	 *   SETTERS
	 * ---------------------------------------------*/
	public void setDay (int d) {
		if (d > 0 && d <= daysOfTheMonth( mm, yy) ) {
			dd = d;
		}
		else {
			dd = 1;
			System.out.println("Wrong day. Day set to 1.");
		}
	}
	
	public void setMonth(int m) {
		if (m > 0 && m <= 12) {
			mm = m;
		}
		else {
			mm = 1;
			System.out.println("Wrong month. Month set to 1.");
		}
	}
	public void setYear(int y) {
		if (y > 0) {
			yy = y;
		}
		else {
			System.out.println("Wrong year. Month set to 1.");
		}
	}

	public void setDate(int d, int m, int y) {
		setYear(y);
		setMonth(m);
		setDay(d);
	}

	/* ---------------------------------------------
	 *   GETTERS
	 * ---------------------------------------------*/
	public int getDay()   {return dd;}	
	public int getMonth() {return mm;}
	public int getYear()  {return yy;}


	/* ---------------------------------------------
	 *   int daysOfTheMonth(m, y) 
	 * ---------------------------------------------*/
	public int daysOfTheMonth(int m, int y) {
		
		if ( m == 4 || m == 6 || m == 9 || m == 11 ) {
			return 30;
		}

		else if ( m == 2 ) {
			if ( (y % 4.0 == 0) && ( (y % 100.0 != 0) || (y % 400.0 == 0) ) ) {
				return 28;
			}
			return 29;
		}
		return 31;
	}

	/* ---------------------------------------------
	 *   int convertToDays() 
	 * ---------------------------------------------*/
	public int convertToDays() {

		// Reference date: 1/1/1
		int days = (yy - 1)*365 + dd;

		for (int m = 1; m < mm; m++) {
			days += daysOfTheMonth(mm, yy);
		}

		return days;
	}

	/* ---------------------------------------------
	 *   int substractDate() 
	 * ---------------------------------------------*/
	public int substractDate(Date d) {
		return Math.abs(this.convertToDays() - d.convertToDays());
	}


	/* ---------------------------------------------
	 *   Printing functions 
	 * ---------------------------------------------*/
	public void printDate() {
		System.out.println(this);			
	}

    @Override
    public String toString() {
        return "\n" + dd + "/" + mm + "/" + yy;
    }

}
