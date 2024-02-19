package pl.iseebugs.Lotto.domain.numberGenerator;

public interface RandomNumbersGenerable {
    SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBound, int upperBound);
}
