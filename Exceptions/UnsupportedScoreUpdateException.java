package Exceptions;

public class UnsupportedScoreUpdateException extends Exception {
    private final String score;

    public UnsupportedScoreUpdateException(String score, String message) {
        super(message);
        this.score = score;
    }

    public String getScore() {
        return score;
    }
}
