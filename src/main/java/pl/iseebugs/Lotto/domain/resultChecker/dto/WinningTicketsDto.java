package pl.iseebugs.Lotto.domain.resultChecker.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WinningTicketsDto(List<ResultDto> results,
                                String message
) {

}
