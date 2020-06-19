package Csc402;

import com.corpository.aws.SITTones;

public class GNotes {
	public static void main(String[] args) {
		try{
			// scale increments
			int[] steps = { 4, 5, 6};
			for (int i = 0; i < steps.length; i++) {
				double hz = 311.5 * Math.pow(2, steps[i] / 12.0);
				SITTones.playTone(hz, 1.5);
				System.out.println("G"+steps[i]+" : "+hz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
