package pl.iseebugs.Lotto.domain.resultChecker;

import java.util.List;
import java.util.Optional;

public interface TicketResultRepository {
    List<TicketResult> saveAll(List<TicketResult> players);

    Optional<TicketResult> findById(String hash);
}
