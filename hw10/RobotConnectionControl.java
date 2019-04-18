package hw10;

public class RobotConnectionControl {
	public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
		moveRobotAttempts(robotConnectionManager, toX, toY, 3);
	}

	public static void moveRobotAttempts(RobotConnectionManager rcm, int toX, int toY, int attempts) {
		boolean moved = false;
		if (attempts == 0) {
			throw new RobotConnectionException("failed connection!");
		}

		try (RobotConnection rc = rcm.getConnection()) {
			rc.moveRobotTo(toX, toY);
			moved = true;
		} catch (RobotConnectionException ex) {
			if (!moved) {
				moveRobotAttempts(rcm, toX, toY, --attempts);
			}
		}
	}
}
