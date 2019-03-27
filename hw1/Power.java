package hw1;

public class Power {

	public static double numberToPower(double number, int power) {
		if (power == 0) {
			return 1;
		}

		if (power == 1) {
			return number;
		}

		power--;
		return number * numberToPower(number, power);
	}
}