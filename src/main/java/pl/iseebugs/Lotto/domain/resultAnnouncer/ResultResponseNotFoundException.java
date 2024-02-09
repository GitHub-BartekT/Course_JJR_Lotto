package pl.iseebugs.Lotto.domain.resultAnnouncer;

public class ResultResponseNotFoundException extends Exception{
    public ResultResponseNotFoundException() {
        super("Result response not found.");
    }
    public ResultResponseNotFoundException(String message) {
        super(message);
    }
}
