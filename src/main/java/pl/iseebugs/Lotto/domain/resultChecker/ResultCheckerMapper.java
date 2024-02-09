package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.ResultDto;

import java.util.List;

class ResultCheckerMapper {

    static List<ResultDto> toResultDto(List<TicketResult> ticketResultList){
        return ticketResultList.stream().map(
                ticketResult -> ResultDto
                        .builder()
                        .Id(ticketResult.Id())
                        .hitNumbers(ticketResult.hitNumbers())
                        .drawDate(ticketResult.drawDate())
                        .isWinner(ticketResult.isWinner())
                        .wonNumbers(ticketResult.wonNumbers())
                        .build())
                .toList();
    }

    static List<Ticket> toTicket(List<TicketDto> ticketDto){
        return ticketDto.stream().map(dto ->
                Ticket.builder()
                        .ticketId(dto.ticketId())
                        .drawDate(dto.drawDate())
                        .numbersFromUser(dto.numbersFromUser())
                        .build())
                .toList();
    }
}
