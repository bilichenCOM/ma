package hw10;

@SuppressWarnings("serial")
public class RobotConnectionException extends RuntimeException {
	public RobotConnectionException(String message) {
		super(message);
	}

	public RobotConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}