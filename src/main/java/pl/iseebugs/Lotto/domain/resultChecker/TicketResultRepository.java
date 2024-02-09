package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.util.List;
import java.util.Optional;

public interface TicketResultRepository {
    List<TicketResultDto> saveAll(List<TicketResultDto> players);

    Optional<TicketResultDto> findById(String hash);
}
