package csc402;

import java.io.File;
import java.net.URL;

import completed.StdAudio;

public class PlaySong
{
	public static void main(String[] args) {
		try
		{
			double[] frequency;
			URL url = WordStatistics.class.getResource("data/a2song.txt");
			File file = new File(url.getPath());

			StdIn.readFile(file);
			frequency = StdIn.readAllDoubles();
			for(double freq : frequency) {
				playTone(freq, 0.25);
			}
			StdIn.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void playTone(double frequency, double duration) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			slices[i] = Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
		}
		StdAudio.play(slices);
	}
}
