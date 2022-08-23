import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyser {

	public static List<Token> analyse(String input) throws NumberException, ExpressionException {
		List<Token> tokens = new ArrayList<Token>(); // List of tokens to be return
		// Token token = new Token(); // Initialise token to be added to tokens list
		int state = 0; // Initial state
		double value; // Value of token
		String str = "";
		int temp = 0; // Temporary value for storing index of input

		// Loop through input string for example given input is 1+2*3
		for(int i = 0; i < input.length(); i++) {
			switch (state) {
				case 0: // Initially accept numbers and whitespace
					// Check the character
					switch (input.charAt(i)) {
						case ' ': // If the character is whitespace, set state to 0
							state = 0;
							break;
						case '0': // If the character is 0, go to state 3
							str = str + input.charAt(i); // Add character to string
							tokens.add(new Token(Integer.parseInt("" + input.charAt(i)))); // Add token to tokens list
							state = 3;
							temp = i;
							break;
						case '1': // If the character is 1-9, go to state 1
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							str = str + input.charAt(i); // Add character to string
							tokens.add(new Token(Integer.parseInt("" + input.charAt(i)))); // Add token to tokens list
							state = 1;
							temp = i;
							break;
						case '.': // This state is not accepting '.' as initial character
							throw new NumberException();
						case '+': // This state is not accepting operators
						case '-':
						case '*':
						case '/':
							throw new ExpressionException();
						default:
							break;
					}
					break;
				case 1: // Number in front, accept whitespace and operators
					// Check the character
					switch (input.charAt(i)) {
						case '+': // If the character is an operator, go to state 2
                    	case '-':
                    	case '*':
                    	case '/':
							tokens.add(new Token(Token.typeOf(input.charAt(i)))); // Add token to tokens list
							state = 2;
							temp = tokens.size();
							break;
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							str = str + input.charAt(i); // Add character to string
							value = Double.parseDouble(str);
							tokens.set(temp, new Token(Double.parseDouble("" + value))); // Update token in tokens list
							break;
						case ' ':
							state = 5;
							break;
						case '.': // This state is not accepting '.' after non 0 digit
							throw new NumberException();
						default:
							throw new ExpressionException();
					}
					break;
				case 2: // Operator in front, accept numbers and whitespace
					// Check the character
					switch (input.charAt(i)) {
						case '0': // If the character is 0, go to state 3
							tokens.add(new Token(Integer.parseInt("" + input.charAt(i)))); // Add token to tokens list
							state = 3;
							break;
						case '1': // If the character is 1-9, go to state 1
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							str = "" + input.charAt(i); // Reset string for new number
							tokens.add(new Token(Integer.parseInt("" + input.charAt(i)))); // Add token to tokens list
							state = 1;
							break;
						case ' ':
							state = 6;
							break;
						case '+': // This state is not accepting operators
						case '-':
						case '*':
						case '/':
							throw new ExpressionException();
						case '.': // This state is not accepting '.' after a non 0 digit
							throw new NumberException();
						default:
							throw new ExpressionException();
					}
					break;
				case 3: // O in front, accept operators, decimals ('.') and whitespace
					// Check the character
					switch (input.charAt(i)) {
						case '+': // If the character is an operator, go to state 2
                    	case '-':
                    	case '*':
                    	case '/':
							tokens.add(new Token(Token.typeOf(input.charAt(i)))); // Add token to tokens list
							state = 2;
							break;
						case '0': // This state is not accepting non 0 digits after 0
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							throw new NumberException();
						case '.': // If the character is a decimal, go to state 4
							state = 4;
							break;
						default:
							throw new ExpressionException();
					}
					break;
			case 4: // '.' in front, accept numbers after a 0
				switch (input.charAt(i)) {
					case '0': // If the character is 0-9, go to state 1
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						str = str + "." + input.charAt(i); // Add character to string
						value = Double.parseDouble(str);
						tokens.set(temp, new Token(Double.parseDouble(str))); // Update token in tokens list
						state = 1;
						break;
					default:
						throw new ExpressionException();
				}
				break;			
			}
		}
		if (state == 4) throw new NumberException(); // If the input is 0. without a digit after '.', throw an exception
		if (state == 2) throw new ExpressionException(); // If the input is invalid (502+, ...), throw an exception
		return tokens;
	}
}