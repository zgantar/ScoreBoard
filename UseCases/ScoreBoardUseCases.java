package UseCases;

import Exceptions.MissingMatchException;
import Exceptions.UnsupportedScoreUpdateException;
import POJO.Match;
import POJO.ScoreBoard;

import java.util.Comparator;
import java.util.Stack;

/**
 * Methods for manipulating ScoreBoard POJO class
 *
 * @author ziga
 */
public class ScoreBoardUseCases {
    ScoreBoard sb;

    public ScoreBoardUseCases() {
        sb = new ScoreBoard();
    }

    /**
     * Returns a list of matches currently played sorted by score sum and time of start descending
     *
     * @return The formated and sorted list.
     */
    public String getSummary() {
        String scoreBoardString = "";
        for (Match match : sb.getMatchesStack()) {
            scoreBoardString = (match.toString() + "\n").concat(scoreBoardString);
        }
        return scoreBoardString;
    }

    /**
     * Starts a new match and adds it to the list of matches currently in progress
     *
     * @param homeTeam The name of the home team.
     * @param awayTeam The name of the away team.
     * @return The formated and sorted list.
     */
    public String startNewMatch(String homeTeam, String awayTeam) {
        Match newMatch = new Match(homeTeam, awayTeam);
        sb.getMatchesStack().push(newMatch);
        return getSummary();
    }

    /**
     * Updates a score for a match in progress.
     *
     * @param inputMatch The name of the match formated as "HomeTeamName - AwayTeamName"
     * @param score      The new score formated as "HomeScore - AwayScore" that needs to be entered onto the scoreboard
     * @return The formated and sorted list.
     * @throws MissingMatchException           if a match with an entered name cannot be found
     * @throws UnsupportedScoreUpdateException if either home or away part of the entered score is negative, has lowered
     *                                         or has changed by more than one
     */
    public String updateScore(String inputMatch, String score) throws MissingMatchException, UnsupportedScoreUpdateException {
        Match match2Update;
        boolean matchInserted = false;
        Stack<Match> laterMatches = new Stack<>();
        //loop to find the match to update it's score
        //and to build an array of matches that are currently before the updated one
        //to sort them after according to score change
        while (!sb.getMatchesStack().isEmpty()) {
            Match loopMatch = sb.getMatchesStack().pop();
            if (loopMatch.equalsMatchName(inputMatch)) {
                //found the match updating score
                match2Update = loopMatch;
                MatchUseCases.updateScore(match2Update, score);
                //now looking through the matches that are in the list after the updated match
                //to see where exactly to put the updated match according to the updated score
                while (!laterMatches.isEmpty()) {
                    Match innerLoopMatch = laterMatches.pop();
                    if (innerLoopMatch.getScore() < match2Update.getScore()) {
                        sb.getMatchesStack().push(innerLoopMatch);
                    } else if (innerLoopMatch.getScore() == match2Update.getScore()) {
                        if (innerLoopMatch.getStartTime().isAfter(match2Update.getStartTime())) {
                            sb.getMatchesStack().push(match2Update);
                            matchInserted = true;
                        }
                        sb.getMatchesStack().push(innerLoopMatch);
                    } else {
                        if (!matchInserted) {
                            sb.getMatchesStack().push(match2Update);
                            matchInserted = true;
                        }
                        sb.getMatchesStack().push(innerLoopMatch);
                    }
                }
                //checking to see if the updated match needs to be put at the last place
                if (!matchInserted) {
                    sb.getMatchesStack().push(match2Update);
                }
                break;
            } else {
                laterMatches.push(loopMatch);
            }
        }
        if (sb.getMatchesStack().isEmpty()) {
            sb.setMatchesStack(laterMatches);
            throw new MissingMatchException(inputMatch, "Could not find entered match: ");
        }
        return getSummary();
    }

    /**
     * Updates a score for a match in progress using streams.
     *
     * @param inputMatch The name of the match formated as "HomeTeamName - AwayTeamName"
     * @param score      The new score formated as "HomeScore - AwayScore" that needs to be entered onto the scoreboard
     * @return The formated and sorted list.
     * @throws MissingMatchException           if a match with an entered name cannot be found
     * @throws UnsupportedScoreUpdateException if either home or away part of the entered score is negative, has lowered
     *                                         or has changed by more than one
     */
    public String updateScoreWithStream(String inputMatch, String score) throws MissingMatchException, UnsupportedScoreUpdateException {
        Match match2Update = findMatch(inputMatch);

        MatchUseCases.updateScore(match2Update, score);

        Comparator<Match> compareByScoreName = Comparator
                .comparing(Match::getScore)
                .thenComparing(Match::getStartTime);

        Stack<Match> stack = new Stack<>();
        if (stack.addAll(sb.getMatchesStack().stream()
                .sorted(compareByScoreName)
                .toList())) {
            sb.setMatchesStack(stack);
        }

        return getSummary();
    }

    /**
     * Finds a match in progress
     *
     * @param matchName The name of the match to find.
     * @return The found match.
     * @throws MissingMatchException if a match with an entered name cannot be found
     */
    public Match findMatch(String matchName) throws MissingMatchException {
        Match returnMatch = sb.getMatchesStack().stream()
                .filter(match -> match.equalsMatchName(matchName))
                .findFirst()
                .orElse(null);
        if (returnMatch == null) {
            throw new MissingMatchException(matchName, "Could not find entered match: ");
        }
        return returnMatch;
    }

    /**
     * Finish a match in progress
     *
     * @param inputMatch The name of the match formated as "HomeTeamName - AwayTeamName"
     * @return The formated and sorted list.
     * @throws MissingMatchException if a match with an entered name cannot be found
     */
    public String finishMatch(String inputMatch) throws MissingMatchException {
        Match match2Remove = null;
        for (Match loopMatch : sb.getMatchesStack()) {
            if (loopMatch.equalsMatchName(inputMatch)) {
                match2Remove = loopMatch;
                sb.getMatchesStack().remove(match2Remove);
                break;
            }
        }
        if (match2Remove == null) {
            throw new MissingMatchException(inputMatch, "Could not find entered match: ");
        }
        return getSummary();
    }

    /**
     * Reorders match stack after a change of score
     * @return The formated and sorted list.
     */
    public String reorderScoreBoard() {
        Comparator<Match> compareByScoreName = Comparator
                .comparing(Match::getScore)
                .thenComparing(Match::getStartTime);

        Stack<Match> stack = new Stack<>();
        if (stack.addAll(sb.getMatchesStack().stream()
                .sorted(compareByScoreName)
                .toList())) {
            sb.setMatchesStack(stack);
        }

        return getSummary();
    }
}
