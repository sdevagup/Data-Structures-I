                                                  Pravallika Devaguptapu
                                                     ID:1936201

package csc402;

import stdlib.StdAudio;
import stdlib.StdIn;
import stdlib.StdOut;

public class PlayChordsFromFile {

	public static void main(String[] args) {
		try{
			StdIn.fromFile("data/risingchords.txt");
			String currentLine;
			while ((currentLine = StdIn.readLine()) != null) {
				String[] strFrequencies = currentLine.split("\\s+");
				double[] frequencies = new double[strFrequencies.length];
				for(int i=0; i<strFrequencies.length; i++)
				{
					frequencies[i] = Double.valueOf(strFrequencies[i]);
				}
				playChord(frequencies);
			}
			
		}catch (Error err) {
			StdOut.println("File not found. Change the file path and try again.");
		} catch (Exception pce) {
			StdOut.println("File not found. Change the file path and try again.");
		}
	}

	public static void playChord(double... frequencies) {
		double duration = 0.5;
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			for (double frequency: frequencies) {
				slices[i] += Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
			}
			slices[i] /= frequencies.length;
		}
		StdAudio.play(slices);
	}
}
