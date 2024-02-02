package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);
}
