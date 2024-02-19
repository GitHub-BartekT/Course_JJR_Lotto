package pl.iseebugs.Lotto.domain.numberGenerator;
import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomNumbersGenerator implements RandomNumbersGenerable {

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBound, int upperBound){
        Random generator = new Random();
        Set<Integer> winningNumbers = new HashSet<>();
        while (winningNumbers.size() < count){
            Integer newNumber = lowerBound + generator.nextInt(upperBound);
            winningNumbers.add(newNumber);
        }
        return SixRandomNumbersDto.builder()
                .numbers(winningNumbers)
                .build();
    }
}
