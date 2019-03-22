
public class Recursion {
	private static int x = 0;
	private static int y = 1;
	private static int z = 1;
	private static boolean flag = true;

	public static long factorial(int number) {
		return (number==1||number<=0)?1:number*factorial(number-1);
	}
	
	public static int fibonacciOnPos(int position) {
		if(position<=1) {
			reset();
			return z;
		}
		z=x+y;
		x=y;
		y=z;
		return fibonacciOnPos(position-1);
	}
	
	public static double taylorSequenceOf(int arg) {
		if(flag) {
			z=arg;
			flag=false;
		}
		if(arg==0) {
			reset();
			return z;
		}
		return Math.pow(z, arg)/factorial(arg)+taylorSequenceOf(arg-1);
	}
	
	private static void reset() {
		x=0;
		y=1;
		flag=true;
	}
	
}
