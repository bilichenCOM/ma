package hw10;

public class RobotConnectionException extends RuntimeException {
	private static final long serialVersionUID = 8306483190337201237L;

	public RobotConnectionException(String message) {
		super(message);
	}

	public RobotConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
