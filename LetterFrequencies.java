package csc402;

import java.util.HashMap;
import java.util.Map;
import stdlib.*;

public class LetterFrequencies {

	public static void computeLetterFrequencies(String fileName){
		try {
			Map<String,Integer> map = new HashMap<String,Integer>();
			String currentLine;						

			//reading the whole text into a single String
			while ((currentLine = StdIn.readLine()) != null) {
				String lineText = currentLine.replaceAll("\\s", "");

				//Storing the letter frequencies in Map
				int count=1;
				for (int i = 0; i < lineText.length(); i++) {	        	
					if(map.get(String.valueOf(lineText.charAt(i))) == null){
						map.put(String.valueOf(lineText.charAt(i)),Integer.valueOf(count));
					}
					else{
						Integer counter = map.get(String.valueOf(lineText.charAt(i)));
						map.put(String.valueOf(lineText.charAt(i)),++counter);
					}
				}
			}		    

			//Printing the letter frequencies from Map
			StdOut.println("Letter frequencies in "+fileName);
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				String key = entry.getKey().toString();
				Integer value = entry.getValue();
				StdOut.println( key + "        " + value);
			}			
		}catch(Error error){
			StdOut.println("File not found. Change the file path and try again.");
		}catch(Exception e){
			StdOut.println("File not found. Change the file path and try again.");
		}
	}

	public static void main(String[] args) {
		try{
			StdOut.print("Please enter the pathname or URL of the book file: "); 
			//prompt the user for the pathname of a text file
			String fileName = StdIn.readString();
			StdIn.fromFile(fileName);
			computeLetterFrequencies(fileName);
		}catch(Error error){
			StdOut.println("File not found. Change the file path and try again.");
		}
	}

}
