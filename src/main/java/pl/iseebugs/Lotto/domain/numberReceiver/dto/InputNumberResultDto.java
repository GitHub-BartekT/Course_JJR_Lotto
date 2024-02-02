package pl.iseebugs.Lotto.domain.numberReceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InputNumberResultDto(String message, LocalDateTime drawDate, String ticketId){
}
