package csc402;
import algs13.Stack;
import stdlib.*;

public class BalancedPunctuation {

	public static boolean isBalanced(String inputString) {
		Stack<Character> stack = new Stack<>();
		
		// below line will remove any spaces from the input string
		String input = inputString.replaceAll("\\s","");
		
		if(input.length()>0){
			for (int i = 0; i < input.length(); i++) {
				char c = input.trim().charAt(i);
				if (c == '(' || c == '{' || c == '[') {
					stack.push(c);
				}else if (c == ')' || c == '}' || c == ']' ) {
					if (stack.size() == 0) {
						return false;
					}
					char popChar = stack.pop();
					if ((c == ')' && popChar != '(') ||  (c == '}' && popChar != '{') || (c == ']' && popChar != '[')) {
						return false;
					}
				}
			}
		}
		else{
			return false;
		}
		if (stack.size() != 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		StdOut.println("Enter a punctuation string: ");
		String userInput = StdIn.readLine();
		boolean isBalncd = isBalanced(userInput);
		if(isBalncd) StdOut.println("Balanced string");
		else StdOut.println("Not a balanced string");
		
		//following are the test inputs
		//StdOut.println(isBalanced("([()]{}{[()()]()})"));//true
		//StdOut.println(isBalanced("[(])"));//false
	}

}
