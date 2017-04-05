package parser;

import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {
	private static int operatorPriority(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return -1;
		}
	}

	public static String convert(String infix) {
		StringBuilder postfix = new StringBuilder();
		Stack<Character> operators = new Stack<Character>();
		Scanner sc = new Scanner(infix);
		while (sc.hasNext()) {
			if (sc.hasNextDouble())
				postfix.append(sc.next() + " ");
			else {
				char c = sc.next().charAt(0);
				if (c == '(')
					operators.push(c);
				else if (c == ')') {
					while (!operators.isEmpty() && operators.peek() != '(')
						postfix.append(operators.pop() + " ");
					if (!operators.isEmpty())
						operators.pop();
				} else {
					if (!operators.isEmpty() && operatorPriority(c) <= operatorPriority(operators.peek()))
						postfix.append(operators.pop() + " ");
					operators.push(c);
				}
			}
		}
		while (!operators.isEmpty())
			postfix.append(operators.pop() + " ");
		return String.valueOf(postfix).trim();
	}
}