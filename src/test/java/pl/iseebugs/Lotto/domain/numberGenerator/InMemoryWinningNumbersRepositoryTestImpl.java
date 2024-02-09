package pl.iseebugs.Lotto.domain.numberGenerator;

import java.time.LocalDateTime;
import java.util.*;


class InMemoryWinningNumbersRepositoryTestImpl implements WinningNumbersRepository{

    Map<LocalDateTime, WinningNumbers> inMemoryDatabase = new HashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
        inMemoryDatabase.put(winningNumbers.drawDate(), winningNumbers);
        return winningNumbers;
    }

    @Override
    public Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate) {
        return Optional.ofNullable(inMemoryDatabase.get(drawDate));
    }

    @Override
    public List<WinningNumbers> getAllWinningNumbers() {
        return new ArrayList<>(inMemoryDatabase.values());
    }
}
