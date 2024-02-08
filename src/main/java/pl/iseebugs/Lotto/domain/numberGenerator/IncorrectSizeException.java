package pl.iseebugs.Lotto.domain.numberGenerator;

public class IncorrectSizeException extends Exception {
        public IncorrectSizeException() {
            super("Number of winning numbers is incorrect");
        }
        public IncorrectSizeException(String message) {
            super(message);
        }
}
