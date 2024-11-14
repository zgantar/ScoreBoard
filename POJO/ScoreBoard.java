package POJO;

import java.util.LinkedHashMap;

public class ScoreBoard {

    public LinkedHashMap<Long, Match> scoreBoardMap;

    public ScoreBoard() {
        scoreBoardMap = new LinkedHashMap<Long, Match>();
    }

    public LinkedHashMap<Long, Match> getScoreBoard() {
        if (scoreBoardMap == null) {
            scoreBoardMap = new LinkedHashMap<Long, Match>();
        }
        return scoreBoardMap;
    }

    public Long startNewMatch(String homeTeam, String awayTeam) {
        Match newMatch = new Match(homeTeam, awayTeam);
        scoreBoardMap.putLast(newMatch.startTime.getEpochSecond(), newMatch);
        return newMatch.startTime.getEpochSecond();
    }
}
