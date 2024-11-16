package Exceptions;

public class MissingMatchException extends Exception {
    private final String matchName;
    public MissingMatchException(String matchName, String message) {
        super(message);
        this.matchName = matchName;
    }

    public String getMatchName() {
        return matchName;
    }
}
