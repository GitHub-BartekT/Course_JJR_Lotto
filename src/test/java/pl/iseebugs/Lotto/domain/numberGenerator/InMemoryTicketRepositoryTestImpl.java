package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberReceiver.Ticket;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryWinningNumbersRepositoryTestImpl implements WinningNumbersRepository{
    private final AtomicInteger index = new AtomicInteger(1);

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
