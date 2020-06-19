package csc402;

import java.io.File;
import java.net.URL;

public class WordStatistics
{
	public static void main(String[] args) {
		try
		{
			int[] wordLength;
			double sumOfWordLength = 0;
			int totalWord = 0;
			URL url = WordStatistics.class.getResource("data/tale.txt");
			File file = new File(url.getPath());

			StdIn.readFile(file);
			String[] wordArray = StdIn.readAllStrings();
			StdIn.close();
			totalWord = wordArray.length;
			wordLength = new int[totalWord];
			for(int i=0;i<totalWord;i++) {
				System.out.println(wordArray[i]);
				String word = wordArray[i];
				int length = word.length();
				wordLength[i] = length;
				sumOfWordLength += length;
			}
			for(int j :wordLength) {
				System.out.println(j);
			}

			double average = sumOfWordLength / totalWord;
			System.out.println("Average : "+average);

			double median;
			if(totalWord % 2 == 0) {
				median = wordLength[totalWord / 2];
			}else {
				double totalMedian =  wordLength[totalWord / 2] + wordLength[totalWord / 2+1];
				median = totalMedian / 2;
			}
			System.out.println("Median : "+median);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
