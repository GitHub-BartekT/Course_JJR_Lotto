package pl.iseebugs.Lotto.domain.numberReceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record TicketDto(LocalDateTime drawDate, String ticketId, Set<Integer> numbersFromUser) {
}
