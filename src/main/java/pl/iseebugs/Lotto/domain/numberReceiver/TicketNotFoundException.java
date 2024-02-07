package pl.iseebugs.Lotto.domain.numberReceiver;

public class TicketNotFoundException extends Exception {
    public TicketNotFoundException() {
        super("Ticket not found.");
    }
    public TicketNotFoundException(String message) {
        super(message);
    }
}
