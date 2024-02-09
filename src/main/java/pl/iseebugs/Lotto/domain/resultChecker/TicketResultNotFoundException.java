package pl.iseebugs.Lotto.domain.resultChecker;

public class TicketResultNotFoundException extends Exception{
        public TicketResultNotFoundException() { super("Winning Numbers Not Found");}
        public TicketResultNotFoundException(String message) { super(message);
        }
}
