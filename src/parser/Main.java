package parser;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		InfixToPostfix converter = new InfixToPostfix();
		String test = "2 + 3 * 6";
		test  =converter.convert(test);
		//Expression ex = new Expression("3 0 /");
		//Parser p = new Parser("3 9 / -4 3 + *");
		Parser p = new Parser(test);
		System.out.println(p);
		//System.out.println(ex);
		sc.close();
	}

}
