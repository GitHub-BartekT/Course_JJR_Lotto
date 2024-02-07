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

@AllArgsConstructor
public class NumberReceiverFacade {

    private static final Logger logger = LoggerFactory.getLogger(NumberReceiverFacade.class);

    private final NumberValidator validator;
    private final TicketRepository repository;
    private final Clock clock;
    private final IdGenerable idGenerator;
    private final GenerateDrawDate generateDrawDate;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        if (validator.filterAllNumbersInTheRange(numbersFromUser)) {
            String ticketId = idGenerator.getRandomId();
            LocalDateTime drawDate = generateDrawDate.generateNextDrawDate(LocalDateTime.now(clock));
            logger.info("draw Date: {}", drawDate);
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
        LocalDateTime drawDate = generateDrawDate.generateNextDrawDate(date);

        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(drawDate);
        return allTicketsByDrawDate.stream()
                .map(TicketMapper::toTicketDto)
                .toList();
    }

    public TicketDto getTicketById(String ticketId) throws TicketNotFoundException {
        Ticket result = repository.findTicketByTicketId(ticketId).orElseThrow(TicketNotFoundException::new);
        return TicketMapper.toTicketDto(result);
    }
}
