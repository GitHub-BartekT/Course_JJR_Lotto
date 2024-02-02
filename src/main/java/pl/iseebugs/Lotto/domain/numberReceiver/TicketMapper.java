package pl.iseebugs.Lotto.domain.numberReceiver;

import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

public class TicketMapper {
    public static TicketDto toTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .numbersFromUser(ticket.numbersFromUser())
                .ticketId(ticket.ticketId())
                .drawDate(ticket.drawDate())
                .build();
    }
}
