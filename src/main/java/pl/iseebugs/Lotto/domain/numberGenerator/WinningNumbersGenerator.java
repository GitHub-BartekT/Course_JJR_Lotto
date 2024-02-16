package pl.iseebugs.Lotto.domain.numberGenerator;
import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class WinningNumbersGenerator implements WinningNumbersGenerable{
    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(){
        Random generator = new Random();
        Set<Integer> winningNumbers = new HashSet<>();
        while (winningNumbers.size() < NUMBER_OF_WINNING_NUMBERS){
            Integer newNumber = LOWER_BOUND + generator.nextInt(UPPER_BOUND);
            winningNumbers.add(newNumber);
        }
        return SixRandomNumbersDto.builder()
                .numbers(winningNumbers)
                .build();
    }
}
