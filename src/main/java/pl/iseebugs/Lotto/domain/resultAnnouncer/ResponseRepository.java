package pl.iseebugs.Lotto.domain.resultAnnouncer;

import java.util.Optional;

public interface ResponseRepository {

    Optional<TicketResponse> findResultResponseById(String id);

    boolean existsById(String id);
}
