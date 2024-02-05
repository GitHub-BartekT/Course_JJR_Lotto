package pl.iseebugs.Lotto.domain.numberGenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class WinningNumbersDTO {

    private LocalDateTime drawDate;
    private Set<Integer> winningNumbers;
}
