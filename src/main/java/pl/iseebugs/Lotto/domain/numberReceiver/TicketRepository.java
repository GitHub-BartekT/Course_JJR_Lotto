package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface TicketRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);

    Optional<Ticket> findTicketByTicketId(String ticketId);

    boolean existsByTicketId(String ticketId);
}
