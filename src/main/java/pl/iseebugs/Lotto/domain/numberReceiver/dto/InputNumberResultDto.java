package pl.iseebugs.Lotto.domain.numberReceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record InputNumberResultDto(String message, LocalDateTime drawDate, String ticketId, Set<Integer> numbersFromUser){
}
