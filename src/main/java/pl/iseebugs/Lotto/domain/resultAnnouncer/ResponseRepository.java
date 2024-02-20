package pl.iseebugs.Lotto.domain.resultAnnouncer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends MongoRepository<TicketResponse, String> {
}
