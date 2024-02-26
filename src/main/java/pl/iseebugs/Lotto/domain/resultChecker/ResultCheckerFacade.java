package pl.iseebugs.Lotto.domain.resultChecker;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.iseebugs.Lotto.domain.numberGenerator.IncorrectSizeException;
import pl.iseebugs.Lotto.domain.numberGenerator.OutOfRangeException;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersNotFoundException;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDto;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.WinningTicketsDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Log4j2
public class ResultCheckerFacade {

    NumberReceiverFacade numberReceiverFacade;
    WinningNumbersFacade winningNumbersFacade;
    TicketResultRepository ticketResultRepository;
    WinnersRetriever winnersRetriever;

    public WinningTicketsDto  generateResults() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException {
        log.info("generated Results()");
        List<TicketDto> allTicketsByDate = numberReceiverFacade.getTicketsByNextDrawDate();
        log.info("generated Results(), after taking tickets");
        List<Ticket> resultDtoList = ResultCheckerMapper.toTicket(allTicketsByDate);
        WinningNumbersDto winningNumbersDto = winningNumbersFacade.generateWinningNumbers();
        Set<Integer> winningNumbers = winningNumbersDto.winningNumbers();

        if (winningNumbers == null || winningNumbers.isEmpty()){
            return WinningTicketsDto.builder()
                    .message("Winners failed to retrieve.")
            .build();
        }

        List<TicketResult> ticketsResult = winnersRetriever.retrieveWinners(resultDtoList, winningNumbers);
        ticketResultRepository.saveAll(ticketsResult);
        List<TicketResultDto> result = ticketsResult.stream()
                .map(ResultCheckerMapper::toTicketResultDto).toList();
        log.info("Saved tickets: {}", result.size());
        return WinningTicketsDto.builder()
                .results(result)
                .message("Winners succeeded to retrieve")
                .build();
    }

    public TicketResultDto findTicketById(String id) throws TicketResultNotFoundException {
        TicketResult resultDto =
                ticketResultRepository.findById(id).orElseThrow(() -> new TicketResultNotFoundException(id));
        return ResultCheckerMapper.toTicketResultDto(resultDto);
    }
}
