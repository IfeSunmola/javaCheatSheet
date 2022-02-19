import java.util.Scanner;

@SuppressWarnings("all")
public class midTerm {
	final static String OPEN_BRACKET = "(";
	final static String OPEN_CURLY = "{";
	final static String OPEN_SQUARE = "[";

	final static String CLOSE_BRACKET = ")";
	final static String CLOSE_CURLY = "}";
	final static String CLOSE_SQUARE = "]";

	final static String MULTIPLICATION = "*";
	final static String PLUS = "+";
	final static String SUBTRACTION = "-";

	final static String EXPONENET = "^";
	final static String DIVISION = "/";
	final static String MODULO = "%";

	public static void main (String [] args) {
	    
	    calculatePostFix();
	}

	private static String postfixToInfix (String input) {
		StringStack s = new StringStack();
		// System.out.print("Enter postfix to convert to infix: ");
		// Scanner sc = new Scanner(System.in);
		// String input = sc.nextLine();
		// sc.close();

		String [] tokens = input.split(" ");
		for (String token : tokens) {
			if (isLetterOrNumber(token)) {
				s.push(token);
			}
			if (isAnOperator(token)) {
				if (!s.isEmpty()) {
					String operand1 = "  " + s.pop();
					if (!s.isEmpty()) {
						String operand2 = s.pop() + " ";
						s.push(OPEN_BRACKET + operand2 + token + operand1 + CLOSE_BRACKET);
					}
				}
			}
		}
		return s.pop();
	}

	private static String prefixToPostfix (String input) {
		StringStack s = new StringStack();
		// System.out.print("Enter prefix to convert to postfix: ");// * + A B + C D
		// Scanner sc = new Scanner(System.in);
		// String input = sc.nextLine();
		// sc.close();

		String postfix = "";
		String [] tokens = input.split("");
		for (int i = tokens.length - 1; i >= 0; i--) {// reading from right to left
			String token = tokens[i];
			if (isLetterOrNumber(token)) {
				s.push(token);
			}
			if (isAnOperator(token)) {
				s.push(s.pop() + s.pop() + token);
			}
		}
		return s.pop();
	}

	private static String prefixToInfix (String input) {
		StringStack s = new StringStack();
		// System.out.print("Enter prefix to convert to infix: ");// * + A B + C D
		// Scanner sc = new Scanner(System.in);
		// String input = sc.nextLine();
		// sc.close();
		String infix = "";
		String [] tokens = input.split("");
		for (int i = tokens.length - 1; i >= 0; i--) {// reading from right to left
			String token = tokens[i];
			if (isLetterOrNumber(token)) {// an operand
				s.push(token);// add to the stack
			}
			if (isAnOperator(token)) {
				String operand1 = s.pop();
				String operand2 = s.pop();
				infix = OPEN_BRACKET + operand1 + token + operand2 + CLOSE_BRACKET;
				s.push(infix);
			}
		}
		return s.pop();
	}

	private static String infixToPrefix (String input) {
		StringStack s = new StringStack();

		String reversedInput = reverse(input);

		String [] tokens = reversedInput.split("");// can't use charAt because it can't properly handle double digits
		String prefix = "";
		for (String token : tokens) {
			if (isLetterOrNumber(token)) {
				prefix += token + " ";
			}
			else if (token.equals(OPEN_BRACKET)) {// opening bracket
				s.push(token);// add to stack
			}
			else if (token.equals(CLOSE_BRACKET)) {// closing bracket
				while (!s.isEmpty()) {
					String temp = s.pop();

					if (!temp.equals(OPEN_BRACKET)) {
						prefix += temp + " ";
					}
					if (temp.equals(OPEN_BRACKET)) {
						break;
					}
				}
			}
			else if (isAnOperator(token)) {
				if (s.isEmpty()) {
					s.push(token);
				}
				else {
					while (!s.isEmpty()) {
						String temp = s.pop();
						if (temp.equals(OPEN_BRACKET)) {
							s.push(temp);
							break;
						}
						if (isAnOperator(token) && checkPrecedence(temp) < checkPrecedence(token)) {
							// lower precedence
							s.push(temp);
							break;
						}
						if (isAnOperator(token) && checkPrecedence(temp) >= checkPrecedence(token)) {
							// higher precedence
							prefix += temp + " ";
						}
					}
					s.push(token);
				}
			}
		}
		while (!s.isEmpty()) {
			String temp = s.pop();
			prefix += temp;
		}
		return reverse(prefix);
	}

	private static String infixToPostfix (String input) {
		StringStack s = new StringStack();
		// System.out.print("Enter infix to convert to postfix: ");
		// Scanner sc = new Scanner(System.in);
		// String input = sc.nextLine();
		// sc.close();
		String postFix = "";
		String [] tokens = input.split("");
		for (String token : tokens) {
			if (isLetterOrNumber(token)) {
				postFix += token + " ";
			}
			else if (token.equals(OPEN_BRACKET)) {// opening bracket
				s.push(token);// add to stack
			}
			else if (token.equals(CLOSE_BRACKET)) {// closing bracket
				while (!s.isEmpty()) {
					String temp = s.pop();

					if (!temp.equals(OPEN_BRACKET)) {
						postFix += temp + " ";
					}
					if (temp.equals(OPEN_BRACKET)) {
						break;
					}
				}
			}
			else if (isAnOperator(token)) {
				if (s.isEmpty()) {
					s.push(token);
				}
				else {
					while (!s.isEmpty()) {
						String temp = s.pop();
						if (temp.equals(OPEN_BRACKET)) {
							s.push(temp);
							break;
						}
						if (isAnOperator(token) && checkPrecedence(temp) < checkPrecedence(token)) {
							// lower precedence
							s.push(temp);
							break;
						}
						if (isAnOperator(token) && checkPrecedence(temp) >= checkPrecedence(token)) {
							// higher precedence
							postFix += temp + " ";
						}
					}
					s.push(token);
				}
			}
		}
		while (!s.isEmpty()) {
			String temp = s.pop();
			postFix += temp;
		}
		return postFix;
	}

	private static String postfixToprefix (String input) {
		StringStack s = new StringStack();
		// System.out.print("Enter postfix to convert to infix: ");
		// Scanner sc = new Scanner(System.in);
		// String input = sc.nextLine();
		// sc.close();
		String infix = "";
		String [] tokens = input.split("");
		for (String token : tokens) {// reading from left to right
			if (isLetterOrNumber(token)) {// an operand
				s.push(token);// add to the stack
			}
			if (isAnOperator(token)) {
				String operand1 = s.pop();
				String operand2 = s.pop();
				infix = token + operand2 + operand1;
				s.push(infix);
			}
		}
		return s.pop();
	}

	private static void calculatePostFix () {
		System.out.print("Enter postfix number to calculate: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		IntStack s = new IntStack();
		System.out.println("You entered: " + input);
		String [] tokens = input.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			System.out.println("Token: " + token);
			System.out.println("Stack: " + s + "\n");
			
			if (isANumber(token)) {
				s.push(Integer.parseInt(token));
			}
			if (isAnOperator(token)) {
				if (isAnOperator(token)) {// character is an operator
					int operand1 = s.pop();// get the top two
					int operand2 = s.pop();// operands from the stack
					int result = 0;
					switch (token) {// perform the operation that corresponds
						case MULTIPLICATION -> {
							result = operand2 * operand1;
						}
						case PLUS -> {
							result = operand2 + operand1;
						}
						case DIVISION -> {
							result = operand2 / operand1;
						}
						case SUBTRACTION -> {
							result = operand2 - operand1;
						}
					}
					s.push(result);// push the result to the stack for use on next iteration
				}
			}
		}
		System.out.println("Result: " + s.pop());// last data in the stack is the solution
	}

	private static void matchingBrackets () {
		System.out.print("Enter string to check: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();

		// String test = "{ ( 3 * [ x + y ] –8 ) / [ 8 + ( 9 –7 ) ] }";// all
		// brackets matched
		// String test = "[]";
		// String test = "(a + b + ( c * d) –e)";// all brackets matched
		// String test = "a + (b – [c * d) – e]";// mismatched bracket, [ is not
		// closed first
		// String test = "a + (b –(c * d) –e";// missing brackets
		StringStack s = new StringStack();
		String [] tokens = input.split("");
		for (String token : tokens) {
			if (token.equals(OPEN_BRACKET) || token.equals(OPEN_CURLY) || token.equals(OPEN_SQUARE)) {
				s.push(token);
			}
			if (!s.isEmpty()) {
				if ((token.equals(CLOSE_BRACKET) && s.top().equals(OPEN_BRACKET)) || ((token.equals(CLOSE_CURLY) && s.top().equals(OPEN_CURLY)))
						|| ((token.equals(CLOSE_SQUARE) && s.top().equals(OPEN_SQUARE)))) {
					s.pop();
				}
			}
		}
		if (s.isEmpty()) {
			System.out.println("All brackets match");
		}
		while (!s.isEmpty()) {
			System.out.println("Error, closing of " + s.pop() + " is mismatched");
		}
	}

	private static String reverse (String toReverse) {
		String reversed = "";
		String [] tokens = toReverse.split("");
		for (int i = tokens.length - 1; i >= 0; i--) {
			switch (tokens[i]) {
				case OPEN_BRACKET -> reversed += CLOSE_BRACKET;
				case CLOSE_BRACKET -> reversed += OPEN_BRACKET;
				case OPEN_CURLY -> reversed += CLOSE_CURLY;
				case CLOSE_CURLY -> reversed += OPEN_CURLY;
				case OPEN_SQUARE -> reversed += CLOSE_SQUARE;
				case CLOSE_SQUARE -> reversed += OPEN_SQUARE;
				default -> reversed += tokens[i];// not a bracket
			}
		}
		return reversed;
	}

	private static boolean isLetterOrNumber (String token) {
		boolean result = true;
		for (int i = 0; i < token.length() && result; i++) {
			if (!Character.isLetterOrDigit(token.charAt(i))) {
				result = false;
			}
		}
		return result;
	}

	private static boolean isAnOperator (String token) {
		return (token.equals(MULTIPLICATION) || token.equals(PLUS) || token.equals(SUBTRACTION) || token.equals(EXPONENET)
				|| token.equals(DIVISION) || token.equals(MODULO));
	}

	private static boolean isANumber (String number) {
		boolean result;
		if (number == null || number.isBlank() || number.isEmpty()) {// empty string
			result = false;
		}
		else {
			try {
				Integer.parseInt(number);// test this
				result = true;// no exception thrown, number is an integer
			}
			catch (NumberFormatException nfe) {
				result = false;
			}
		}
		return result;
	}

	private static int checkPrecedence (String operator) {
		// in infix to post fix, + and - have the same precedence
		// * / and % have the same precedence but higher than + and -
		// so : (* == / == %) > (+ == -)
		int precedence = Integer.MIN_VALUE;
		switch (operator) {
			// case OPEN_BRACKET -> precedence = 5;
			case OPEN_BRACKET -> precedence = 5;
			case EXPONENET -> precedence = 4;
			case DIVISION -> precedence = 3;
			case MULTIPLICATION -> precedence = 2;
			case PLUS -> precedence = 1;
			case SUBTRACTION -> precedence = 0;
		}
		return precedence;
	}

}

class IntStack {
	private int [] array;
	private int size;

	public IntStack () {
		array = new int [3];
		size = 0;
	}

	// begin push
	public void push (int bracket) {
		if (size == array.length) {
			resize();
		}
		array[size] = bracket;
		size++;
	}

	private void resize () {
		int [] newArray = new int [array.length + 3];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	// end push
	public int size () {
		return size;
	}

	public int pop () {
		if (isEmpty()) {
			throw new RuntimeException("Empty stack, can't pop");
		}
		size--;
		return array[size];
	}

	public int top () {
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}
		int top = pop();// get the top by removing it
		push(top);// add it back
		return top;// top
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public void clear () {
		while (!isEmpty()) {
			pop();
		}
		size = 0;

	}

	public String toString () {
		String result = "";
		for (int i = size - 1; i >= 0; i--) {
			// result += brackets[i] + " ";
		}

		result += "[ ";
		for (int i = 0; i < size; i++) {
			result += array[i] + " ";
		}
		result += " >";
		return result;
	}

}

class StringStack {
	private String [] array;
	private int size;

	public StringStack () {
		array = new String [3];
		size = 0;
	}

	public void push (String bracket) {
		if (size == array.length) {
			resize();
		}
		array[size] = bracket;
		size++;
	}

	private void resize () {
		String [] newArray = new String [array.length + 3];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	public String pop () {
		if (isEmpty()) {
			throw new RuntimeException("Empty stack, can't pop");
		}
		size--;
		return array[size];
	}

	public String top () {
		if (isEmpty()) {
			throw new RuntimeException("Empty stack, there's no top");
		}
		String top = pop();// get the top by removing it
		push(top);// add it back
		return top;// top
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public int size () {
		return size;
	}

	public void clear () {
		while (!isEmpty()) {
			pop();
		}
		size = 0;

	}

	public String toString () {
		String result = "";
		for (int i = size - 1; i >= 0; i--) {
			// result += brackets[i] + " ";
		}

		result += "[ ";
		for (int i = 0; i < size; i++) {
			result += array[i] + " ";
		}
		result += " >";
		return result;
	}
}
