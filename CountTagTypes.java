package csc402;

import stdlib.StdIn;
import stdlib.StdOut;

public class CountTagTypes {

	public static void main(String argv[]) {

		try {
			StdOut.print("Please enter the path of xml file: "); 
			//prompt the user for the pathname of a text file
			String fileName = StdIn.readString();
			StdIn.fromFile(fileName);
			int closingCount = 0;
			int selfClosingCount = 0;
			int openingCount = 0;
			
			String currentLine;
			//reading the whole text into a single String
			currentLine =  StdIn.readAll();
			String lineText = currentLine.replaceAll("\\s", "");
			for (int i = 0; i < lineText.length(); i++) {
				char c = lineText.trim().charAt(i);
				if (c == '<' && lineText.trim().charAt(i+1)=='/') {
					closingCount++;
					continue;
				}else if (c == '<' && lineText.trim().charAt(i+1)!='/' && lineText.trim().charAt(i+1)!='<') {
					openingCount++;
					continue;
				}else if (c == '/' && lineText.trim().charAt(i+1)=='>') {
					selfClosingCount++;
					openingCount--;
					continue;
				}
			}
			StdOut.println("opening tags count..."+openingCount);
			StdOut.println("closing tags count..."+closingCount);
			StdOut.println("self-closing tags count..."+selfClosingCount);
		} catch (Error err) {
			StdOut.println("File not found. Change the file path and try again.");
		} catch (Exception pce) {
			StdOut.println("File not found. Change the file path and try again.");
		}
	}
}
