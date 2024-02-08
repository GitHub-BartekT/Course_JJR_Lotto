package pl.iseebugs.Lotto.domain.numberGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WinningNumbersGenerator implements WinningNumbersGenerable{
    @Override
    public Set<Integer> drawWinningNumbers() {
        Random generator = new Random();
        Set<Integer> winningNumbers = new HashSet<>();
        while (winningNumbers.size() < 6){
            Integer newNumber = 1 + generator.nextInt(99);
            winningNumbers.add(newNumber);
        }
        return winningNumbers;
    }
}
