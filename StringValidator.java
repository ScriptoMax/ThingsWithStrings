import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class StringValidator {
	
	public static final String VALIDATION_FAILED_OUTPUT = "Validation failed\nMake sure your input" 
									 + " isn\'t empty and includes characters except forbidden ones, then retry it";
	public static final String VALIDATION_SUCCESS_OUTPUT = "Validation is complete with success";
	
	public static void main(String [] args) {
		
		Scanner scan = new Scanner(System.in);
		String collapsedInput = scan.next();
		scan.close();
		
		if (collapsedInput.length() == 0 || collapsedInput.charAt(0) == ']') {
			System.out.print(VALIDATION_FAILED_OUTPUT);
			return;
		}
		
		runValidationProcess(collapsedInput);
		
	}
	
	public static void runValidationProcess(String str) {		 
        
		Deque<Character> stack = new ArrayDeque<>();
		char charAtPoint;
				
		for (int i = 0; i < str.length(); i++) {
			charAtPoint = str.charAt(i);
			if (charAtPoint < 48 || charAtPoint > 122 || (charAtPoint >= 58 && charAtPoint <= 64)) {
				System.out.print(VALIDATION_FAILED_OUTPUT);
				return;
			}
			
			if (charAtPoint >= 91 && charAtPoint <= 96) {
				if (charAtPoint != '[' && charAtPoint != ']') {
					System.out.print(VALIDATION_FAILED_OUTPUT);
					return;
				}
			}

			if ((charAtPoint == '[' && i == 0) || (charAtPoint == '[' && !Character.isDigit(str.charAt(i-1)))) {
				System.out.print(VALIDATION_FAILED_OUTPUT);
				return;
			} else if (charAtPoint == '[')
				stack.push(charAtPoint);
			else if (charAtPoint == ']' && (stack.isEmpty() || stack.peek() != '[')) {
				System.out.print(VALIDATION_FAILED_OUTPUT);
				return;			
			} else if (charAtPoint == ']')
				stack.pop();
			else if ((Character.isDigit(charAtPoint) && i == str.length() - 1) || (Character.isDigit(charAtPoint) && str.charAt(i+1) != '[')) {
				System.out.print(VALIDATION_FAILED_OUTPUT);
				return;
			} else
				continue;
			
		}
		
		if (!stack.isEmpty()) {
			System.out.print(VALIDATION_FAILED_OUTPUT);				
		} else 
			System.out.print(VALIDATION_SUCCESS_OUTPUT);
	}
}