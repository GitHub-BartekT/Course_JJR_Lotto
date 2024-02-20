package pl.iseebugs.Lotto.domain.resultChecker;

import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.util.List;
import java.util.Optional;
@Repository
public interface TicketResultRepository extends MongoRepository<TicketResult, String> {
}
