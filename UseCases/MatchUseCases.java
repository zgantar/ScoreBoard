package UseCases;

import Exceptions.UnsupportedScoreUpdateException;
import POJO.Match;

/**
 * Methods for manipulating Match POJO class
 *
 * @author ziga
 */
public class MatchUseCases {

    /**
     * Changes the score of a match.
     *
     * @param match The match for which the score is to be changed.
     * @param score The new score of the match.
     * @throws UnsupportedScoreUpdateException if an entered new score is:
     *                                         not in a "X:Y" format
     *                                         either home or away score is a negative number
     *                                         either home or away score is at most one bigger than current score
     */
    public static void updateScore(Match match, String score) throws UnsupportedScoreUpdateException {
        int delimiter = score.indexOf(':');
        if (delimiter < 0) {
            throw new UnsupportedScoreUpdateException(score, "Entered score with no semicolon delimiter is not supported! ");
        }
        int homeScore = Integer.parseInt(score.substring(0, delimiter));
        if (homeScore < 0 || homeScore - match.getHomeScore() > 1) {
            throw new UnsupportedScoreUpdateException(score, "Entered home score must be a positive number equal or" +
                    " at most one bigger than current score ");
        }
        int awayScore = Integer.parseInt(score.substring(delimiter + 1));
        if (awayScore < 0 || awayScore - match.getAwayScore() > 1) {
            throw new UnsupportedScoreUpdateException(score, "Entered away score must be a positive number equal or" +
                    " at most one bigger than current score ");
        }
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }
}
