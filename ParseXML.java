package csc402;

import algs13.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

public class ParseXML {

	public static void main(String[] args) {
		ParseXML parseObj = new ParseXML();
		try{
			String input[];
			String token = "";
			Stack<String> tagNameStack = new Stack<>();

			StdOut.print("Please enter the path of xml file: "); 
			//prompt the user for the pathname of a text file
			String fileName = StdIn.readString();
			StdIn.fromFile(fileName);
			input =  StdIn.readAllStrings();

			for(int i=0; i<input.length; i++){
				token = input[i];
				String tagName = parseObj.tagName(token);
				//StdOut.println("tag name: "+token);
				if (parseObj.isOpeningTag(token)) {
					StdOut.println("token: "+token);
					//String tagName = parseObj.tagName(token);
					tagNameStack.push(tagName);
				}
				else if (parseObj.isClosingTag(token)) {
					StdOut.println("token: "+token);
					//String tagName = parseObj.tagName(token);
					if (tagNameStack.isEmpty()) {
						StdOut.println("Closing tag " + token +" doesn't match the most recent opening tag");
						System.exit(0);
					}
					String popedTagName = tagNameStack.pop();
					if (!tagName.equals(popedTagName) ) {
						StdOut.println("Closing tag " + token +" doesn't match the most recent opening tag ");
						System.exit(0);
					}
				}
				else {
					StdOut.println("token: "+token);
				}
			}

			if (!tagNameStack.isEmpty()) {
				StdOut.println("stack size::"+tagNameStack.size());
				StdOut.println("There are opening tags with no matching closing tags, they are:");
				while(tagNameStack.size() >0){
					StdOut.println(tagNameStack.pop());
				};
			}else{
				StdOut.println("XML is properly structured"); 
			}

		} catch (Error err) {
			StdOut.println(err);
		} catch (Exception pce) {
			StdOut.println("File not found. Change the file path and try again.");
		}
		//StdOut.println(parseObj.isClosingTag("</first-name>"));
		//StdOut.println(parseObj.tagName("<<no-middle-name>>"));
	}

	public boolean isTag(String token){
		if(token!=null){
			if(token.startsWith("<") && token.endsWith(">")){
				return true;
			}else{
				return false;
			}
		}
		return false;	
	}

	public boolean isWord(String token){
		if(token!=""){
			for(int i=0;i<token.length();i++){
				char c = token.charAt(i);
				if(c=='-' || Character.isLetterOrDigit(c)){

				}else{
					return false;
				}
			}
			return true;		
		}
		return false;
	}

	public boolean isClosingTag(String token){
		if(token!=null && isTag(token) && token.length()>3){
			if(token.startsWith("<") && token.charAt(1)=='/' && isWord(token.substring(2, token.length()-1)) && token.endsWith(">") ){
				return true;
			}
		}
		return false;		
	}

	public boolean isOpeningTag(String token){
		if(token!=null && isTag(token) && token.length()>2){
			if(token.trim().startsWith("<") && isWord(token.substring(1, token.length()-1)) && token.trim().endsWith(">")){
				return true;
			}
		}
		return false;		
	}

	public boolean isSelfClosingTag(String token){
		if(token!=null && isTag(token) && token.length()>3){
			if(token.trim().startsWith("<") && isWord(token.substring(1, token.length()-2)) && token.charAt(token.length()-2)=='/' && token.trim().endsWith(">") ){
				return true;
			}
		}
		return false;		
	}

	public String tagName(String token){
		if(token!=null){
			String tagName = "";
			if(isOpeningTag(token))
				tagName = token.substring(1, token.length()-1);
			else if(isClosingTag(token))
				tagName = token.substring(2, token.length()-1);
			else if(isSelfClosingTag(token))
				tagName = token.substring(1, token.length()-2);
			return tagName;
		}
		return "";		
	}
}
