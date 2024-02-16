package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

class WinningNumbersGeneratorTestImpl implements WinningNumbersGenerable{

    private final Set<Integer> generatedNumbers;

    WinningNumbersGeneratorTestImpl() {
        generatedNumbers = Set.of(1,2,3,4,5,6);
    }

    WinningNumbersGeneratorTestImpl(Set<Integer> generatedNumbers){
        this.generatedNumbers = generatedNumbers;
    }
    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(){
        return SixRandomNumbersDto.builder()
                .numbers(generatedNumbers)
                .build();
    }
}
