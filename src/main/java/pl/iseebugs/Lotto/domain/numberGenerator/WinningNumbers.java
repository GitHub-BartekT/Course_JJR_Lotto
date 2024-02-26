package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document
record WinningNumbers(String id, LocalDateTime drawDate, Set<Integer>winningNumbers) {
}
