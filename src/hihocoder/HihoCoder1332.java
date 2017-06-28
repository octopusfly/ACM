package hihocoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * This is the ACM problem solving program for hihoCoder 1332.
 * 
 * @version 2017-06-12
 * @author Zhang Yufei.
 */
public class HihoCoder1332 {
	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String string = scan.next().trim();
		scan.close();

		char[] array = string.toCharArray();

		Stack<Integer> numberStack = new Stack<>();
		Stack<Character> operatorStack = new Stack<>();

		int number = 0;
		boolean tag = false; // Mark if the number has been pushed into number
								// stack.
		for (char ch : array) {
			if (ch >= '0' && ch <= '9') {
				tag = true;
				number *= 10;
				number += ch - '0';
			} else {
				if (tag) {
					numberStack.push(number);
					tag = false;
				}
				number = 0;
				if (ch == ')') {
					while (!operatorStack.isEmpty()
							&& operatorStack.peek() != '(') {
						int b = numberStack.pop();
						int a = numberStack.pop();
						char op = operatorStack.pop();
						numberStack.push(compute(a, b, op));
					}
					operatorStack.pop();
				} else {
					while (!operatorStack.isEmpty()
							&& !compare(ch,
									operatorStack.peek())) {
						int b = numberStack.pop();
						int a = numberStack.pop();
						char op = operatorStack.pop();

						numberStack.push(compute(a, b, op));
					}

					operatorStack.push(ch);
				}
			}
		}

		if (tag) {
			numberStack.push(number);
			tag = false;
		}

		while (!operatorStack.isEmpty()) {
			int b = numberStack.pop();
			int a = numberStack.pop();
			char op = operatorStack.pop();

			numberStack.push(compute(a, b, op));
		}

		System.out.println(numberStack.pop());
	}

	/**
	 * Compute the value of expression.
	 * 
	 * @param a
	 *            A number.
	 * @param b
	 *            Another number.
	 * @param op
	 *            The operator.
	 * @return The value of expression.
	 */
	private static int compute(int a, int b, int op) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		}

		return -1;
	}

	/**
	 * Compare the priority of 2 operators.
	 * 
	 * @param op1
	 *            One operator.
	 * @param op2
	 *            Another operator.
	 * @return If <code>op1</code> has higher priority than <code>op2</code>,
	 *         returns <code>true</code>, or returns <code>false</code>.
	 */
	private static boolean compare(char op1, char op2) {
		if (op2 == '(' || op1 == '(') {
			return true;
		} else if (op1 == '+') {
			if (op2 == '+') {
				return true;
			} else {
				return false;
			}
		} else if (op1 == '-') {
			if (op2 == '*' || op2 == '/') {
				return false;
			} else {
				return true;
			}
		} else if (op1 == '*') {
			if (op2 == '/') {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
}

