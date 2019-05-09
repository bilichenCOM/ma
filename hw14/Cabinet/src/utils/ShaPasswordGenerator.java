package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.apache.log4j.Logger;

public class ShaPasswordGenerator {

	public static final String ALGORITHM = "SHA-256";
	public static final Integer DEFAULT_SALT_LENGTH = 6;

	private static final Logger logger = Logger.getLogger(ShaPasswordGenerator.class);

	public static String getShaPassword(String password, String salt) {
		String hashedPass = password;
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update(salt.getBytes());
			return Base64.getEncoder().encodeToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.debug("no such algorithm", e);
		}
		return hashedPass;
	}

	public static String generateSalt() {
		return generateSalt(DEFAULT_SALT_LENGTH);
	}

	public static String generateSalt(Integer length) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[length];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
}