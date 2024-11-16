package UseCases;

import Exceptions.MissingMatchException;
import POJO.Match;
import POJO.ScoreBoard;

import java.util.Stack;

public class ScoreBoardUseCases {
    ScoreBoard sb;
    String scoreBoardString;

    public String getSummary() {
        if (!scoreBoardString.isEmpty()) {
            scoreBoardString = "";
        }
        for (Match match : sb.getMatchesStack()) {
            scoreBoardString = (match.toString() + "\n").concat(scoreBoardString);
        }
        return scoreBoardString;
    }

    public ScoreBoardUseCases() {
        sb = new ScoreBoard();
        scoreBoardString = "";
    }

    public Match startNewMatch(String homeTeam, String awayTeam) {
        Match newMatch = new Match(homeTeam, awayTeam);
        sb.getMatchesStack().push(newMatch);
        return newMatch;
    }

    public String updateScore(String inputMatch, String score) throws MissingMatchException {
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
                MatchUseCases.changeScore(match2Update, score);
                //now looking through the matches that are in the list after the updated match
                //to see where exactly to put the updated match according to the updated score
                while(!laterMatches.isEmpty()) {
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

    public String stopMatch(String s) throws MissingMatchException {
        return  getSummary();
    }
}
