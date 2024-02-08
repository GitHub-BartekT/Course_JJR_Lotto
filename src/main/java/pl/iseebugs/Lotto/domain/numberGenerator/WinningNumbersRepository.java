package pl.iseebugs.Lotto.domain.numberGenerator;

import java.time.LocalDateTime;
import java.util.List;

interface WinningNumbersRepository {
    WinningNumbers save(WinningNumbers winningNumbers);

    WinningNumbers getWinningNumbersByDrawDate(LocalDateTime drawDate);

    List<WinningNumbers> getAllWinningNumbers();
}
