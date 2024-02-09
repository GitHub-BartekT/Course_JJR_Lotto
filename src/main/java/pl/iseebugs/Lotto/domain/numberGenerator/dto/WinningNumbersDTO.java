package pl.iseebugs.Lotto.domain.numberGenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record WinningNumbersDTO(LocalDateTime drawDate, Set<Integer> winningNumbers) {
}
