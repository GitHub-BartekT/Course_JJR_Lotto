package pl.iseebugs.Lotto.domain.numberGenerator;
import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

import java.util.Set;

class WinningNumberValidator {

    void validateWinningNumber(Set<Integer> winningNumbers) throws OutOfRangeException, IncorrectSizeException {
        isInRange(winningNumbers);
        hasCorrectSize(winningNumbers);
    }

    void isInRange(Set<Integer> winningNumbers) throws OutOfRangeException{
        for (Integer number : winningNumbers) {
            if (number < LOWER_BOUND || number > UPPER_BOUND) {
                throw new OutOfRangeException(String.format("At least one number is out of range. Number: %s", number.toString()));
            }
        }
    }

    void hasCorrectSize(Set<Integer> winningNumbers) throws IncorrectSizeException {
        if(winningNumbers.size() != NUMBER_OF_WINNING_NUMBERS){
            throw  new IncorrectSizeException(
                    String.format("Number of winning numbers is incorrect, Number of winning numbers: %d", winningNumbers.size()));
        }
    }
}
