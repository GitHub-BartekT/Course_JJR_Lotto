package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberGenerator.dto.OneRandomNumberResponseDto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties.*;

class SecureOneRandomNumberFetcher implements OneRandomNumberFetcher{
    @Override
    public OneRandomNumberResponseDto retrieveOneRandomNumber(int lowerBound, int upperBound) {

        Random generator = new Random();
        int newNumber = LOWER_BOUND + generator.nextInt(UPPER_BOUND);
        return OneRandomNumberResponseDto.builder()
                .number(newNumber)
                .build();
    }
}
