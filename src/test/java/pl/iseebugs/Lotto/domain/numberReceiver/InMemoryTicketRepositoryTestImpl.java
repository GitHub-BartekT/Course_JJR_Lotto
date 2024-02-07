package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTicketRepositoryTestImpl implements TicketRepository {
    Map<String, Ticket> inMemoryDatabase = new HashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        inMemoryDatabase.put(ticket.ticketId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllTicketsByDrawDate(LocalDateTime date) {
        return inMemoryDatabase.values()
                .stream()
                .filter(ticket -> ticket.drawDate().equals(date))
                .toList();
    }

    @Override
    public Optional<Ticket> findTicketByTicketId(String ticketId) {
        return Optional.ofNullable(inMemoryDatabase.get(ticketId));
    }

    @Override
    public boolean existsByTicketId(String ticketId) {
        return inMemoryDatabase.containsKey(ticketId);
    }
}
