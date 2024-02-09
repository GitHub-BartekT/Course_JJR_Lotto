package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.util.List;
import java.util.Optional;

public interface TicketResultRepository {
    List<TicketResult> saveAll(List<TicketResult> players);

    Optional<TicketResult> findById(String id);
}
