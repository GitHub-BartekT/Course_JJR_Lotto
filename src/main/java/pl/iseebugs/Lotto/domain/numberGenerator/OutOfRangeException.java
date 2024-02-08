package pl.iseebugs.Lotto.domain.numberGenerator;

public class OutOfRangeException extends Exception {
        public OutOfRangeException() {
            super("At least one number is out of range.");
        }
        public OutOfRangeException(String message) {
            super(message);
        }
}
