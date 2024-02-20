package pl.iseebugs.Lotto.domain.numberReceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);

    Optional<Ticket> findTicketByTicketId(String ticketId);

    boolean existsByTicketId(String ticketId);
}
