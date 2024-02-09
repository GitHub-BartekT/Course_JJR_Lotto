package pl.iseebugs.Lotto.domain.resultChecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResultDto(String Id,
                        Set<Integer> numbers,
                        Set<Integer> hitNumbers,
                        LocalDateTime drawDate,
                        boolean isWinner,
                        Set<Integer> wonNumbers
) {
}