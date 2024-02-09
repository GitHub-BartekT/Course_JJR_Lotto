package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

import java.util.List;

class ResultCheckerMapper {
    List<Ticket> toTicket(List<TicketDto> ticketDto){
        return ticketDto.stream().map(dto ->
                Ticket.builder()
                        .ticketId(dto.ticketId())
                        .drawDate(dto.drawDate())
                        .numbersFromUser(dto.numbersFromUser())
                        .build())
                .toList();
    }
}
