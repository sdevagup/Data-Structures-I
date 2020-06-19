package csc402;

import java.io.File;
import java.net.URL;

public class Distribution
{
	public static void main(String[] args) {
		try {
			double[] data;

			URL url = WordStatistics.class.getResource("data/normaldistribution.txt");
			File file = new File(url.getPath());

			StdIn.readFile(file);
			data = StdIn.readAllDoubles();

			double mean = calculateMean(data);
			double[] sd = calculateSD(data, mean);
			double finalSD = calculateMean(sd);
			double firstSD = mean + finalSD;
			double secondSD = mean + finalSD*2;
			double thirstSD = mean + finalSD*3;
			System.out.println("Statistics on the file data/normaldistribution.txt");
			System.out.println("The mean is "+mean );
			System.out.println("The standard deviation is "+Math.sqrt(finalSD));
			System.out.println("Percentage of values 1 SD away from mean: "+firstSD);
			System.out.println("Percentage of values 2 SD away from mean: "+secondSD);
			System.out.println("Percentage of values 3 SD away from mean: "+thirstSD);

			System.out.println("######################################################");
			url = WordStatistics.class.getResource("data/uniformdistribution.txt");
			file = new File(url.getPath());

			StdIn.readFile(file);
			data = StdIn.readAllDoubles();

			mean = calculateMean(data);
			sd = calculateSD(data, mean);
			finalSD = calculateMean(sd);
			firstSD = mean + finalSD;
			secondSD = mean + finalSD*2;
			thirstSD = mean + finalSD*3;
			System.out.println("Statistics on the file data/normaldistribution.txt");
			System.out.println("The mean is "+mean );
			System.out.println("The standard deviation is "+Math.sqrt(finalSD));
			System.out.println("Percentage of values 1 SD away from mean: "+firstSD);
			System.out.println("Percentage of values 2 SD away from mean: "+secondSD);
			System.out.println("Percentage of values 3 SD away from mean: "+thirstSD);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private static double calculateMean(double[] values) {
		double mean = 0;
		double sum = 0;
		for(double value : values) {
			sum += value;
		}
		mean = sum / values.length;
		return mean;
	}

	private static double[] calculateSD(double[] values, double mean)
	{
		double[] sd = new double[values.length];
		for(int i=0; i<values.length ;i++) {
			double newValue = values[i] - mean;
			sd[i] = Math.pow(newValue, 2);
		}
		return sd;
	}
}
