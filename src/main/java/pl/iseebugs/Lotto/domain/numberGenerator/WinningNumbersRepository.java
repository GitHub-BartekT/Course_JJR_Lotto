package pl.iseebugs.Lotto.domain.numberGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface WinningNumbersRepository {
    WinningNumbers save(WinningNumbers winningNumbers);

    Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate);

    List<WinningNumbers> getAllWinningNumbers();

    boolean existsBy(LocalDateTime dateTime);
}
