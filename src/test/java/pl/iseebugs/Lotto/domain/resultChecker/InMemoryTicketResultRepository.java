package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTicketResultRepository implements TicketResultRepository {
    Map<String, TicketResult> inMemoryDatabase = new HashMap<>();

    public List<TicketResult> findAllTicketsByDrawDate(LocalDateTime date) {
        return inMemoryDatabase.values()
                .stream()
                .filter(ticketResult -> ticketResult.drawDate().equals(date))
                .toList();
    }

    @Override
    public List<TicketResult> saveAll(List<TicketResult> ticketsResults) {
        ticketsResults.stream().forEach(ticketResult -> inMemoryDatabase.put(ticketResult.Id(), ticketResult));
        return ticketsResults;
    }

    @Override
    public Optional<TicketResult> findById(String id) {
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }
}
