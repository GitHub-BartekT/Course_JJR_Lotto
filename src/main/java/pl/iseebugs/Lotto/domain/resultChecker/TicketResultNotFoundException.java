package pl.iseebugs.Lotto.domain.resultChecker;

public class TicketResultNotFoundException extends RuntimeException{
        public TicketResultNotFoundException() { super("Ticket Not Found");}
        public TicketResultNotFoundException(String message) { super(
                "Not found for id: " + message);
        }
}
