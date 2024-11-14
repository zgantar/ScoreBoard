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
}
