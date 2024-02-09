package pl.iseebugs.Lotto.domain.resultChecker;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPlayerRepository implements TicketResultRepository {
    Map<String, TicketResult> inMemoryDatabase = new HashMap<>();

    public List<TicketResult> findAllTicketsByDrawDate(LocalDateTime date) {
        return inMemoryDatabase.values()
                .stream()
                .filter(player -> player.drawDate().equals(date))
                .toList();
    }

    @Override
    public List<TicketResult> saveAll(List<TicketResult> players) {
        players.stream().forEach(player -> inMemoryDatabase.put(player.Id(), player));
        return players;
    }

    @Override
    public Optional<TicketResult> findById(String hash) {
        return Optional.ofNullable(inMemoryDatabase.get(hash));
    }
}
