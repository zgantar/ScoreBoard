package POJO;

import java.util.LinkedHashSet;

public class ScoreBoard {

    public LinkedHashSet<Match> scoreBoardSet;
    String scoreBoardString = "";

    public ScoreBoard() {
        scoreBoardSet = new LinkedHashSet<Match>();
    }

    public String getScoreBoard() {
        if (scoreBoardString == null || !scoreBoardString.isEmpty()) {
            scoreBoardString = "";
        }
        for (Match match : scoreBoardSet) {
            scoreBoardString = scoreBoardString.concat(match.toString() + "\n");
        }
        return scoreBoardString;
    }

    public Match startNewMatch(String homeTeam, String awayTeam) {
        Match newMatch = new Match(homeTeam, awayTeam);
        scoreBoardSet.addLast(newMatch);
        return newMatch;
    }

    public String updateScore(String inputMatch, String score) {

        for (Match loopMatch : scoreBoardSet) {
            if (loopMatch.equalsMatchName(inputMatch)) {
                loopMatch.changeScore(score);
            }
        }
        return getScoreBoard();
    }
}
