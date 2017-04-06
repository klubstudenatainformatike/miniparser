package parsing-infix;

import java.util.Scanner;

public class Izracunaj {
	
	static double parse(double[] numbers, StringBuilder sb, int begin, int end){
		if(sb.length() == 1){ // if sb is a number we return that number
			return numbers[begin];
		}
		else if(sb.indexOf("+") < 0 && sb.indexOf("-") < 0){ // if there is no "+" or "-" we do operations without priority
			double res = numbers[begin];
			int k = begin + 1;
			for(int i = 1; i < sb.length(); i++){
				if(sb.charAt(i) == '*')
					res *= numbers[k++];
				else if(sb.charAt(i) == '/')
					res /= numbers[k++];
			}
			return res;
		}
		else{ // if there is "+" or "-"
			int k = sb.indexOf("+");      //
			int d = sb.indexOf("-");      //
			int min = 0;                  //
			int numX = begin;             //
			char operation = '+';         //
			if(k != -1 && d != -1)        //    FINDING FIRST OCCURRENCE OF "+" or "-"
				min = k < d ? d : k;      //
			else if(k == -1)              //
				min = d;                  //
			else                          // 
				min = k;                  //
			StringBuilder left = new StringBuilder("");
			StringBuilder right = new StringBuilder("");
			for(int i = 0; i < sb.length(); i++){  // Parsing sb left and right of first "+" or "-"
				if(i < min){
					if(sb.charAt(i) == 'X') // Keeping track of number of "X"-es we went through
						numX++;
					left.append(sb.charAt(i));
				}
				else if (i > min)
					right.append(sb.charAt(i));
				else
					operation = sb.charAt(i);
			}
			double leftRet = parse(numbers, left, begin, numX); // Calling same function for left string
			double rightRet = parse(numbers, right, numX, end);  // Calling same function for right string
			if(operation == '+') // Doing the right operation
				return leftRet + rightRet;
			else
				return leftRet-rightRet;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.next();
		int k = 0;
		StringBuilder finalString = new StringBuilder("");  // Final string should look like X+X*X/X+X+X/X...
		double[] numbers = new double[100];  // Numbers in string
		for(int i = 0; i < string.length(); i++){ // Going through all characters
			char p = 'a'; // Saving binary operation that should be done
			StringBuilder num = new StringBuilder("");  // Making current number
			while(i < string.length() && (Character.isDigit(string.charAt(i)) || string.charAt(i) == '.')){ 
				num.append(string.charAt(i));
				i++;
				if(i < string.length())
					p = string.charAt(i);
			}
			if(i != 0){ // If first number is negative this doesn't execute
				numbers[k++] = Double.parseDouble(num.toString()); // Saving number in numbers[]
				finalString.append('X');
				if(i < string.length()) // Adding the operation except after the last number
					finalString.append(p);
			}
		}
		int l = 0;
		if(string.charAt(0) == '-') // Checking if first number is negative
			numbers[0] = -(numbers[0]);
		for(int i = 0; i < finalString.length(); i++){ // Changing all "-"-es to "+"-es and inverting those numbers in numbers[]
			if(finalString.charAt(i) == '-'){
				numbers[l] = -(numbers[l]);
				finalString.delete(i, i+1);
				finalString.insert(i, '+');
			}
			if(finalString.charAt(i) == 'X') // finding number of numbers
				l++;
		}
		System.out.println(parse(numbers, finalString, 0, l)); // Calling function 
		sc.close();
	}

}
