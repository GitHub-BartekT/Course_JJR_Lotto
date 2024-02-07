package pl.iseebugs.Lotto.domain.numberReceiver;

import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

class TicketMapper {
    static TicketDto toTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .numbersFromUser(ticket.numbersFromUser())
                .ticketId(ticket.ticketId())
                .drawDate(ticket.drawDate())
                .build();
    }
}
