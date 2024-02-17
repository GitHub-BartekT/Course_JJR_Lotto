package pl.iseebugs.Lotto.domain.numberGenerator;

import java.util.Set;

class RandomNumbersGeneratorTestImpl implements RandomNumbersGenerable {

    private final Set<Integer> generatedNumbers;

    RandomNumbersGeneratorTestImpl() {
        generatedNumbers = Set.of(1,2,3,4,5,6);
    }

    RandomNumbersGeneratorTestImpl(Set<Integer> generatedNumbers){
        this.generatedNumbers = generatedNumbers;
    }
    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(){
        return SixRandomNumbersDto.builder()
                .numbers(generatedNumbers)
                .build();
    }
}
