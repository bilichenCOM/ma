package hw1;

public class Recursion {

	public static long factorial(int number) {
		return (number == 1 || number <= 0) ? 1 : number * factorial(number - 1);
	}

	public static int fibonacciOnPos(int position) {
		if (position <= 0) {
			return 0;
		}

		if (position == 1) {
			return 1;
		}
		return fibonacciOnPos(position - 1) + fibonacciOnPos(position - 2);
	}

	public static double taylorSequence(int arg, int index) {
		if (index <= 0) {
			return 1;
		}
		return Math.pow(arg, index) / factorial(index) + taylorSequence(arg, index - 1);
	}
}