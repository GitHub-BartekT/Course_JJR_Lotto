package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document
record Ticket(String ticketId, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
