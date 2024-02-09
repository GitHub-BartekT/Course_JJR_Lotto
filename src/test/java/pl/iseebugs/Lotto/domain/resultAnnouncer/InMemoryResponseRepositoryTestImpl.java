package pl.iseebugs.Lotto.domain.resultAnnouncer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryResponseRepositoryTestImpl implements ResponseRepository{
    Map<String, TicketResponse> inMemoryResponseRepository = new HashMap<>();

    @Override
    public TicketResponse save(TicketResponse ticketResponse) {
        inMemoryResponseRepository.put(ticketResponse.Id(), ticketResponse);
        return inMemoryResponseRepository.get(ticketResponse.Id());
    }

    @Override
    public Optional<TicketResponse> findById(String id) {
        return Optional.ofNullable(inMemoryResponseRepository.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return inMemoryResponseRepository.containsKey(id);
    }
}
