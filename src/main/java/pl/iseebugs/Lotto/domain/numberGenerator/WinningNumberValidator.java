package pl.iseebugs.Lotto.domain.numberGenerator;

import java.util.Set;

class WinningNumberValidator {

    void validateWinningNumber(Set<Integer> winningNumbers) throws OutOfRangeException, IncorrectSizeException {
        isInRange(winningNumbers);
        hasCorrectSize(winningNumbers);
    }

    void isInRange(Set<Integer> winningNumbers) throws OutOfRangeException{
        for (Integer number : winningNumbers) {
            if (number < 1 || number > 99) {
                throw new OutOfRangeException(String.format("At least one number is out of range. Number: %s", number.toString()));
            }
        }
    }

    void hasCorrectSize(Set<Integer> winningNumbers) throws IncorrectSizeException {
        if(winningNumbers.size() != 6){
            throw  new IncorrectSizeException(
                    String.format("Number of winning numbers is incorrect, Number of winning numbers: %d", winningNumbers.size()));
        }
    }
}
