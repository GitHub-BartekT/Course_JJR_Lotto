package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberGenerator.dto.OneRandomNumberResponseDto;

public interface OneRandomNumberFetcher {
    OneRandomNumberResponseDto retrieveOneRandomNumber(int lowerBound, int upperBound);
}
