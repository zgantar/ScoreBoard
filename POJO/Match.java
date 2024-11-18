package POJO;

import Exceptions.UnsupportedScoreUpdateException;

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

    /**
     * Changes the score of a match.
     *
     * @param homeScore The new score for home team
     * @param awayScore The new score for away team
     * @throws UnsupportedScoreUpdateException if an entered new score is:
     *                                         not in a "X:Y" format
     *                                         either home or away score is a negative number
     *                                         either home or away score is at most one bigger than current score
     */
    public void updateScore(int homeScore, int awayScore) throws UnsupportedScoreUpdateException {
        if (homeScore < 0 || homeScore - this.getHomeScore() > 1) {
            throw new UnsupportedScoreUpdateException(String.valueOf(homeScore), "Entered home score must be a positive number equal or" +
                    " at most one bigger than current score ");
        }
        if (awayScore < 0 || awayScore - this.getAwayScore() > 1) {
            throw new UnsupportedScoreUpdateException(String.valueOf(awayScore), "Entered away score must be a positive number equal or" +
                    " at most one bigger than current score ");
        }
        this.setHomeScore(homeScore);
        this.setAwayScore(awayScore);
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
