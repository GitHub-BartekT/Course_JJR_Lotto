package pl.iseebugs.Lotto.domain.resultChecker;

public class TicketResultNotFoundException extends Exception{
        public TicketResultNotFoundException() { super("Ticket Not Found");}
        public TicketResultNotFoundException(String message) { super(message);
        }
}
