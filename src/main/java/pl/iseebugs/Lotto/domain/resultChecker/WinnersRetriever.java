package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerProperties.NUMBERS_WHEN_PLAYER_WON;

class WinnersRetriever {

    List<TicketResultDto> retrieveWinners(List<Ticket> ticketsList, Set<Integer> wonNumbers){
        List<TicketResult> resultList = new ArrayList<>();

        for (Ticket ticket : ticketsList) {
            Set<Integer> hitNumbers = new HashSet<>();
            for (Integer number : ticket.numbersFromUser()) {
                if (wonNumbers.contains(number)){
                    hitNumbers.add(number);
                }
            }
            boolean isWinner = hitNumbers.size() >= NUMBERS_WHEN_PLAYER_WON;
            if(!isWinner){
                continue;
            }
            resultList.add(TicketResult.builder()
                            .Id(ticket.ticketId())
                            .drawDate(ticket.drawDate())
                            .numbers(ticket.numbersFromUser())
                            .hitNumbers(hitNumbers)
                            .isWinner(true)
                            .wonNumbers(wonNumbers)
                    .build());
        }
        return ResultCheckerMapper.toTicketResultDto(resultList);
    }
}
