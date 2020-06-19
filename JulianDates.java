package csc402;

import stdlib.StdIn;

import stdlib.StdOut;

public class JulianDates {	

	public static void main(String[] args) {
		StdOut.println("Enter a date in mm/dd/yyyy format: ");
		String userInput = StdIn.readLine();
		try{
		A3Date today = new A3Date(userInput);
		StdOut.println("Julian date : "+today.julianDate());
		}catch(Error e){
			System.out.println("Please enter a valid date in mm/dd/yyyy format");
		}
		
	}

}
