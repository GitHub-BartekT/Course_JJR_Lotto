package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Log4j2
public class NumberReceiverFacade {

    private static final Logger logger = LoggerFactory.getLogger(NumberReceiverFacade.class);

    private final NumberValidator validator;
    private final TicketRepository repository;
    private final IdGenerable idGenerator;
    private final GenerateDrawDate generateDrawDate;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        if (validator.filterAllNumbersInTheRange(numbersFromUser)) {
            String ticketId = idGenerator.getRandomId();
            LocalDateTime drawDate = generateDrawDate.generateNextDrawDate();
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            logger.info("Saved ticket with, draw Date: {}, id: {}", savedTicket.drawDate(), savedTicket.ticketId());

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

    public List<TicketDto> getTicketsByNextDrawDate(){
       LocalDateTime nextDrawDate = generateDrawDate.generateNextDrawDate();
        log.info("generated nexDrawDate: {}", nextDrawDate);
        return getTicketsByNextDrawDate(nextDrawDate);
    }

    public List<TicketDto> getTicketsByNextDrawDate(LocalDateTime dateBeforeDraw){
        LocalDateTime drawDate = generateDrawDate.generateNextDrawDateByDate(dateBeforeDraw);
        log.info("generated nexDrawDate: {}", drawDate);
        log.info("generated dateBeforeDraw: {}", dateBeforeDraw);
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(drawDate);
        return allTicketsByDrawDate.stream()
                .map(TicketMapper::toTicketDto)
                .toList();
    }

    public TicketDto findTicketById(String ticketId) throws TicketNotFoundException {
        Ticket result = repository.findTicketByTicketId(ticketId).orElseThrow(TicketNotFoundException::new);
        return TicketMapper.toTicketDto(result);
    }

    public LocalDateTime generateNextDrawDate(){
        return generateDrawDate.generateNextDrawDate();
    }

    public LocalDateTime generateNextDrawDateByDate(LocalDateTime dateBeforeDraw){
        return generateDrawDate.generateNextDrawDateByDate(dateBeforeDraw);
    }
}
