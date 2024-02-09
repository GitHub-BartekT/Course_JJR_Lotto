package pl.iseebugs.Lotto.domain.resultChecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record Player(String Id, 
                     Set<Integer> numbers,
                     Set<Integer> hitNumbers,
                     LocalDateTime drawDate,
                     boolean isWinner) {
}
