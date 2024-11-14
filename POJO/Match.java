package POJO;

import java.time.Instant;

public class Match {
    public String homeTeam;
    public String awayTeam;

    public int homeScore;
    public int awayScore;

    public Instant startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

        this.homeScore = 0;
        this.awayScore = 0;
        startTime = Instant.now();
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }

    public boolean equalsMatchName(String matchName) {
        return matchName.equals(homeTeam + " - " + awayTeam);
    }

    public void changeScore(String score) {
        int delimiter = score.indexOf(':');
        this.homeScore = Integer.parseInt(score.substring(0, delimiter));
        this.awayScore = Integer.parseInt(score.substring(delimiter+1));
    }
}
