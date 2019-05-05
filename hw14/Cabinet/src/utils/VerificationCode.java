package utils;

import org.apache.log4j.Logger;

public class VerificationCode {
	private static final int MIN_VALUE = 1000;
	private static final int MAX_VALUE = 10000;

	private static final Logger logger = Logger.getLogger(VerificationCode.class);

	public static int generate() {
		int verCode = (int) (Math.random() * (MAX_VALUE - MIN_VALUE)) + MIN_VALUE;
		logger.debug("new verification code has been generated: " + verCode);
		return verCode;
	}
}