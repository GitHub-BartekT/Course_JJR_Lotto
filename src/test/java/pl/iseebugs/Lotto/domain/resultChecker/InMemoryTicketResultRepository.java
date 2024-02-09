package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTicketResultRepository implements TicketResultRepository {
    Map<String, TicketResultDto> inMemoryDatabase = new HashMap<>();

    public List<TicketResultDto> findAllTicketsByDrawDate(LocalDateTime date) {
        return inMemoryDatabase.values()
                .stream()
                .filter(player -> player.drawDate().equals(date))
                .toList();
    }

    @Override
    public List<TicketResultDto> saveAll(List<TicketResultDto> players) {
        players.stream().forEach(player -> inMemoryDatabase.put(player.Id(), player));
        return players;
    }

    @Override
    public Optional<TicketResultDto> findById(String hash) {
        return Optional.ofNullable(inMemoryDatabase.get(hash));
    }
}
