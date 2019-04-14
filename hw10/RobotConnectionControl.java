package hw10;

public class RobotConnectionControl {
	public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
		moveRobotAttempts(robotConnectionManager, toX, toY, 3);
	}

	public static void moveRobotAttempts(RobotConnectionManager rcm, int toX, int toY, int attempts) {
		if (attempts == 0) {
			throw new RobotConnectionException("failed connection!");
		}

		RobotConnection rc = null;
		try {
			rc = rcm.getConnection();
			rc.moveRobotTo(toX, toY);
		} catch (RobotConnectionException ex) {
			moveRobotAttempts(rcm, toX, toY, --attempts);
		} finally {
			try {
				rc.close();
			} catch (Exception ex) {
				System.err.println("error by closing connection!");
			}
		}
	}
}
