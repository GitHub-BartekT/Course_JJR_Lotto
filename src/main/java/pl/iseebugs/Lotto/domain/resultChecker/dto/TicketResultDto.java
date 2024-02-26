package pl.iseebugs.Lotto.domain.resultChecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record TicketResultDto(String id,
                              Set<Integer> numbers,
                              Set<Integer> hitNumbers,
                              LocalDateTime drawDate,
                              boolean isWinner,
                              Set<Integer> wonNumbers) {
}
