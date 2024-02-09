package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.OneRandomNumberResponseDto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

@AllArgsConstructor
class WinningNumbersGenerator_HTTP implements WinningNumbersGenerable{
    private final OneRandomNumberFetcher client;

    @Override
    public Set<Integer> drawWinningNumbers(){

        Set<Integer> winningNumbers = new HashSet<>();
        while (winningNumbers.size() < NUMBER_OF_WINNING_NUMBERS){
            OneRandomNumberResponseDto newNumber = client.retrieveOneRandomNumber(LOWER_BOUND,UPPER_BOUND);
            winningNumbers.add(newNumber.number());
        }
        return winningNumbers;
    }
}
