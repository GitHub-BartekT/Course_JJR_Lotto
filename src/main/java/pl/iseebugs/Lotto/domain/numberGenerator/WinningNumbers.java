package pl.iseebugs.Lotto.domain.numberGenerator;

import java.time.LocalDateTime;
import java.util.Set;

record WinningNumbers(LocalDateTime drawDate, Set<Integer>winningNumbers) {
}
