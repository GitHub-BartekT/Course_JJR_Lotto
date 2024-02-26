package pl.iseebugs.Lotto.domain.numberGenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface WinningNumbersRepository extends MongoRepository<WinningNumbers, String> {
    Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate);
    boolean existsByDrawDate(LocalDateTime dateTime);
}
