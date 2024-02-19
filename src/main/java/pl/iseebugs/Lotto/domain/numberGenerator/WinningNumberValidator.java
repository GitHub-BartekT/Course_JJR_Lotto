package pl.iseebugs.Lotto.domain.numberGenerator;
import lombok.AllArgsConstructor;

import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

import java.util.Set;

@AllArgsConstructor
class WinningNumberValidator {

    WinningGenerateNumberProperties winningGenerateNumberProperties;

    void validateWinningNumber(Set<Integer> winningNumbers) throws OutOfRangeException, IncorrectSizeException {
        isInRange(winningNumbers, winningGenerateNumberProperties.lowerBound(), winningGenerateNumberProperties.upperBound());
        hasCorrectSize(winningNumbers, 6);
    }

    private void isInRange(Set<Integer> winningNumbers, int lowerBound, int upperBound) throws OutOfRangeException{
        for (Integer number : winningNumbers) {
            if (number < lowerBound || number > upperBound) {
                throw new OutOfRangeException(String.format("At least one number is out of range. Number: %s", number.toString()));
            }
        }
    }

    private void hasCorrectSize(Set<Integer> winningNumbers, int count) throws IncorrectSizeException {
        if(winningNumbers.size() != count){
            throw  new IncorrectSizeException(
                    String.format("Number of winning numbers is incorrect, Number of winning numbers: %d", winningNumbers.size()));
        }
    }
}
