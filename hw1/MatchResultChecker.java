

public class MatchResultChecker {

	public static int checkGoalPredictions(int predict_team1, int predict_team2, int real_team1, int real_team2) {
		return (predict_team1<0||predict_team2<0||real_team1<0||real_team2<0)?0:
			(predict_team1==real_team1&&predict_team2==real_team2)?2:
				((predict_team1==predict_team2&&real_team1==real_team2)||((predict_team1-predict_team2)*(real_team1-real_team2)>0))?1:
					0;
	}
}
