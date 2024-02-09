package pl.iseebugs.Lotto.domain.resultAnnouncer.dto;

import lombok.Builder;

@Builder
public record ResultResponseDto(TicketResultDto ticketResult,
                                String message) {
}
