package UseCases;

import POJO.Match;

public class MatchUseCases {

    public static void changeScore(Match match, String score) {
        int delimiter = score.indexOf(':');
        int homeScore = Integer.parseInt(score.substring(0, delimiter));
        int awayScore = Integer.parseInt(score.substring(delimiter+1));
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }
}
