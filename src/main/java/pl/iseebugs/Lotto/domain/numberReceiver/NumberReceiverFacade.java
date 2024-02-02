package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser){
        if (validator.filterAllNumbersInTheRange(numbersFromUser)){
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawDate = LocalDateTime.now();
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return InputNumberResultDto.builder()
                    .drawDate(savedTicket.drawDate())
                    .ticketId(savedTicket.ticketId())
                    .message("success")
                    .build();
        }
        return InputNumberResultDto.builder()
                .message("failed")
                .build();
    }

    public List<TicketDto> userNumbers(LocalDateTime date){
return List.of(
        TicketDto.builder()
                .ticketId("1")
                .drawDate(LocalDateTime.now())
                .numbersFromUser(Set.of(1,2,3,4,5,6))
                .build());
    }
}
