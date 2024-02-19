package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

@AllArgsConstructor
class SecureRandomGenerator_HTTP implements RandomNumbersGenerable {

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBound, int upperBound){
        Set<Integer> winningNumbers = new HashSet<>();
        while (isAmountOfNumbersLowerThenSix(winningNumbers, count)){
            Random random = new SecureRandom();
            Integer randomNumber = random.nextInt((upperBound - lowerBound) + 1);
            winningNumbers.add(randomNumber);
        }
        return SixRandomNumbersDto.builder()
                .numbers(winningNumbers)
                .build();
    }

    private static boolean isAmountOfNumbersLowerThenSix(Set<Integer> winningNumbers, int count) {
        return winningNumbers.size() < count;
    }
}
