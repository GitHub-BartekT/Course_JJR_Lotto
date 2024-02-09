package pl.iseebugs.Lotto.domain.resultAnnouncer.dto;

import lombok.Builder;

@Builder
public record ResultAnnouncerResponseDto(ResultResponseDto ticketResult,
                                         String message) {
}
