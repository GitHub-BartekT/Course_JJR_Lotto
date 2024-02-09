package pl.iseebugs.Lotto.domain.resultAnnouncer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record ResultResponse(
        String Id,
        Set<Integer> numbers,
        Set<Integer> hitNumbers,
        LocalDateTime drawDate,
        boolean isWinner,
        Set<Integer> wonNumbers){
}
