package csc402;

import java.util.Arrays;
import java.util.Comparator;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Date.java
 *  Execution:    java Date
 *
 *  An immutable data type for dates.
 *
 *************************************************************************/

public class A3Date implements Comparable<A3Date> {
	private static int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private int month;   // month (between 1 and 12)
	private int day;     // day   (between 1 and DAYS[month]
	private int year;    // year

	// do bounds-checking to ensure object represents a valid date
	public A3Date(int month, int day, int year) {
		if (!isValid(month, day, year)) throw new Error("Invalid date");
		this.month = month;
		this.day   = day;
		this.year  = year;
	}

	// create new data by parsing from string of the form mm/dd/yy
	public A3Date(String date) {
		String[] fields = date.split("/");
		if (fields.length != 3) {
			throw new Error("Date parse error");
		}
		month = Integer.parseInt(fields[0]);
		day   = Integer.parseInt(fields[1]);
		year  = Integer.parseInt(fields[2]);
		if (!isValid(month, day, year)) throw new Error("Invalid date");
	}

	public int month() { return month; }
	public int day()   { return day;   }
	public int year()  { return year;  }


	// is the given date valid?
	private static boolean isValid(int m, int d, int y) {
		if (m < 1 || m > 12)      return false;
		if (d < 1 || d > DAYS[m]) return false;
		if (m == 2 && d == 29 && !isLeapYear(y)) return false;
		return true;
	}

	// is y a leap year?
	private static boolean isLeapYear(int y) {
		if (y % 400 == 0) return true;
		if (y % 100 == 0) return false;
		return y % 4 == 0;
	}

	// return the next Date
	public A3Date next() {
		if (isValid(month, day + 1, year))    return new A3Date(month, day + 1, year);
		else if (isValid(month + 1, 1, year)) return new A3Date(month + 1, 1, year);
		else                                  return new A3Date(1, 1, year + 1);
	}

	// is this Date after b?
	public boolean isAfter(A3Date b) {
		return compareTo(b) > 0;
	}

	// is this Date a before b?
	public boolean isBefore(A3Date b) {
		return compareTo(b) < 0;
	}

	// compare this Date to that one
	public int compareTo(A3Date that) {
		if (this.year  < that.year)  return -1;
		if (this.year  > that.year)  return +1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		if (this.day   < that.day)   return -1;
		if (this.day   > that.day)   return +1;
		return 0;
	}

	// return a string representation of this date
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	// is this Date equal to x?
	public boolean equals(Object x) {
		if (x == this) return true;
		if (x == null) return false;
		if (x.getClass() != this.getClass()) return false;
		A3Date that = (A3Date) x;
		return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
	}

	public int hashCode() {
		int hash = 17;
		hash = 31*hash + month;
		hash = 31*hash + day;
		hash = 31*hash + year;
		return hash;
	}
	
	public  int julianDate(){
		int count = 0;				
		count = this.day;
		if(this.month >2 && !isLeapYear(this.year)){
			count = this.day-1;
		}			
		for(int i =1; i<this.month; i++){
			count += this.DAYS[i];				
		}
		return count;
	}
}

