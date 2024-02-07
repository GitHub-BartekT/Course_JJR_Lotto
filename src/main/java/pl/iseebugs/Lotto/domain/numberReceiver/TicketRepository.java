package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.LocalDateTime;
import java.util.List;

interface TicketRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);
}
