package hw1;

import hw1.AsteriskDiamond;
import hw1.MatchResultChecker;
import hw1.Matrix;
import hw1.ChildOfFather;
import hw1.Power;
import hw1.Recursion;

public class Test {

	public static void main(String[] args) throws CloneNotSupportedException {

		System.out.println(
				MatchResultChecker.checkGoalPredictions(5, 0, (int) (7 * Math.random()), (int) (7 * Math.random())));

		AsteriskDiamond.printDiamond(30);
		AsteriskDiamond.printDiamond(29);
		AsteriskDiamond.printDiamond(10);
		AsteriskDiamond.printDiamond(9);
		AsteriskDiamond.printDiamond(3);
		AsteriskDiamond.printDiamond(2);
		AsteriskDiamond.printDiamond(1);

		System.out.println(Power.numberToPower(10, 3));
		System.out.println(Power.numberToPower(3.14, 2));

		System.out.println(Recursion.factorial(4));
		System.out.println(Recursion.fibonacciOnPos(5));
		System.out.println(Recursion.taylorSequence(10, 10));

		double[][] matrix = new double[][] { { 1, 33, 55 }, { 3.3, 54.4, 53 }, { -2, 3.2, -10 }, { 2, 0.2, 11 } };
		System.out.println(Matrix.maxIn(matrix));
		System.out.println(Matrix.minIn(matrix));

		System.out.println(new ChildOfFather("Example").clone().equals(new ChildOfFather("Example")));
	}
}