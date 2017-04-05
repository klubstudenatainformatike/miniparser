package parser;

import java.util.Stack;

public class Calculate {

	private double val = 0;

	public Calculate(final String expr) {
		// Postfix i = new Postfix(expr); // TODO: parse infix to postfix
		this.val = parse(expr); // change expr to i.toString() after Postfix has
								// been implemented
	}

	public double getVal() {
		return val;
	}

	private double parse(final String s) {
		Stack<String> args = new Stack<String>();

		String[] st = s.split(" ");
		for (int i = 0; i < st.length; ++i) {
			if (!isOperator(st[i])) {
				args.push(st[i]);
			} else {
				String b = args.pop();
				String a = args.pop();
				double res = calc(a, b, st[i].charAt(0));
				args.push(res + "");
			}

		}

		return Double.parseDouble(args.pop());
	}

	private boolean isOperator(final String op) {
		char oop = op.charAt(0);
		return ('+' == oop || '-' == oop || '/' == oop || '*' == oop) && (1 == op.length());
	}

	private double calc(final String A, final String B, final char op) {
		double a = Double.parseDouble(A);
		double b = Double.parseDouble(B);
		switch (op) {
		case '+':
			return (a + b);
		case '-':
			return (a - b);
		case '*':
			return (a * b);
		case '/':
			try {
				return (a / b);
			} catch (Exception e) {
				e.getMessage();
				System.exit(-1);
			}
		default:
			System.out.println("Invalid operand detected");
			System.exit(-1);
		}

		return 0.0;
	}

}