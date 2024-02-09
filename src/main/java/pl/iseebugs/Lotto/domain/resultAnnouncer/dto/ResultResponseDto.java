package pl.iseebugs.Lotto.domain.resultAnnouncer.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResultResponseDto (String Id,
                                 Set<Integer> numbers,
                                 Set<Integer> hitNumbers,
                                 LocalDateTime drawDate,
                                 boolean isWinner,
                                 Set<Integer> wonNumbers){
}
