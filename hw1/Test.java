import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(MatchResultChecker.checkGoalPredictions(5, 0, (int)(7*Math.random()), (int)(7*Math.random())));
		
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
		System.out.println(Recursion.taylorSequenceOf(3));
		
		double[][] matrix = new double[][] {{1,33,55},{3.3,54.4,53},{-2,3.2,-10},{2,0.2,11}};
		System.out.println(Matrix.maxIn(matrix));
		System.out.println(Matrix.minIn(matrix));
		System.out.println(Arrays.deepToString(Matrix.revert(matrix)));
		
		System.out.println(new OMethodsRealization("Example",5).clone().equals(new OMethodsRealization("Example", 6)));
		
	}

}
