package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.Builder;

import java.util.Set;

@Builder
public record SixRandomNumbersDto(Set<Integer> numbers) {
}
