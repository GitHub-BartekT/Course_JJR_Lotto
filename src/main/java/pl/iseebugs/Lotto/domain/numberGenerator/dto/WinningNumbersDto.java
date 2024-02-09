package pl.iseebugs.Lotto.domain.numberGenerator.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record WinningNumbersDto(LocalDateTime drawDate, Set<Integer> winningNumbers) {
}
