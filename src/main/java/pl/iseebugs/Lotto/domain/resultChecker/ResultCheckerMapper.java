package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.util.List;

class ResultCheckerMapper {

    static TicketResultDto toTicketResultDto(TicketResult ticketResult){
        return TicketResultDto
                        .builder()
                        .id(ticketResult.id())
                        .numbers(ticketResult.numbers())
                        .hitNumbers(ticketResult.hitNumbers())
                        .drawDate(ticketResult.drawDate())
                        .isWinner(ticketResult.isWinner())
                        .wonNumbers(ticketResult.wonNumbers())
                        .build();
    }

    static List<Ticket> toTicket(List<TicketDto> ticketDto){
        return ticketDto.stream().map(dto ->
                Ticket.builder()
                        .ticketId(dto.ticketId())
                        .drawDate(dto.drawDate())
                        .numbers(dto.numbersFromUser())
                        .build())
                .toList();
    }
}
