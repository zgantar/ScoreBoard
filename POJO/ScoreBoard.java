package POJO;

import java.util.Stack;

public class ScoreBoard {

    public Stack<Match> matchesStack;

    public ScoreBoard() {
        matchesStack = new Stack<>();
    }

    public Stack<Match> getMatchesStack() {
        return matchesStack;
    }

    public void setMatchesStack(Stack<Match> matchesStack) {
        this.matchesStack = matchesStack;
    }
}
