package POJO;

import java.time.Instant;
import java.util.Objects;

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

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getScore() {
        return this.homeScore + this.awayScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }

    public boolean equalsMatchName(String matchName) {
        return matchName.equals(homeTeam + " - " + awayTeam);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Match match)) return false;
        return Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam);
    }
}
