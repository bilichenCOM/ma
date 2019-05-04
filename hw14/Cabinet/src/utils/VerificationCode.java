package utils;

public class VerificationCode {
	private static final int MIN_VALUE = 1000;
	private static final int MAX_VALUE = 10000;
	
	public static int generate() {
		return (int) (Math.random() * (MAX_VALUE - MIN_VALUE)) + MIN_VALUE;
	}

}
