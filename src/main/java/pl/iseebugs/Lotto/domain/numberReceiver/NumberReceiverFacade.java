package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {

    private static final Logger logger = LoggerFactory.getLogger(NumberReceiverFacade.class);

    private final NumberValidator validator;
    private final TicketRepository repository;
    private final Clock clock;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        if (validator.filterAllNumbersInTheRange(numbersFromUser)) {
            //TODO: create class ticketId to generate and validate iD
            String ticketId = UUID.randomUUID().toString();
            //TODO: generate draw time TO FIX
            LocalDateTime drawDate = GenerateDrawDate.generateNextDrawDate(LocalDateTime.now());
            logger.info("draw Date: {}", drawDate.toString());
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return InputNumberResultDto.builder()
                    .drawDate(savedTicket.drawDate())
                    .ticketId(savedTicket.ticketId())
                    .numbersFromUser(numbersFromUser)
                    .message("success")
                    .build();
        }
        return InputNumberResultDto.builder()
                .message("failed")
                .build();
    }


    public List<TicketDto> getTicketsByNextDrawDate(LocalDateTime date){
        LocalDateTime drawDate = GenerateDrawDate.generateNextDrawDate(date);

        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(drawDate);
        return allTicketsByDrawDate.stream()
                .map(TicketMapper::toTicketDto)
                .toList();
    }
}
