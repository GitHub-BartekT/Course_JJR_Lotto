package pl.iseebugs.Lotto.domain.resultAnnouncer;

import java.util.Optional;

public interface ResponseRepository {

    TicketResponse save(TicketResponse ticketResponse);

    Optional<TicketResponse> findById(String id);

    boolean existsById(String id);
}
