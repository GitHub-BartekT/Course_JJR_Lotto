package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.LocalDateTime;
import java.util.Set;

record Ticket(String ticketId, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
