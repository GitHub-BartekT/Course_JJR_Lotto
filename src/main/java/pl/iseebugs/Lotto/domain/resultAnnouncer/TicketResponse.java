package pl.iseebugs.Lotto.domain.resultAnnouncer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record TicketResponse(
        String Id,
        Set<Integer> numbers,
        Set<Integer> hitNumbers,
        LocalDateTime drawDate,
        boolean isWinner,
        Set<Integer> wonNumbers){
}
