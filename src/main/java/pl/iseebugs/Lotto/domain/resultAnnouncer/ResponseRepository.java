package pl.iseebugs.Lotto.domain.resultAnnouncer;

import java.util.Optional;

public interface ResponseRepository {

    Optional<ResultResponse> findResultResponseById(String id);

    boolean existsById(String id);
}
