package pl.iseebugs.Lotto.domain.numberGenerator;

public class WinningNumbersNotFoundException extends Exception {
        public WinningNumbersNotFoundException() { super("Winning Numbers Not Found");}
        public WinningNumbersNotFoundException(String message) {
            super(message);
        }
}
