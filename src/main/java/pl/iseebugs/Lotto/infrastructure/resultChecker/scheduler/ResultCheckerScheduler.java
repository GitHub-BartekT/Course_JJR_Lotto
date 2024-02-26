package pl.iseebugs.Lotto.infrastructure.resultChecker.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.iseebugs.Lotto.domain.numberGenerator.IncorrectSizeException;
import pl.iseebugs.Lotto.domain.numberGenerator.OutOfRangeException;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersNotFoundException;
import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.WinningTicketsDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
@Log4j2
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;
    private final WinningNumbersFacade winningNumbersFacade;

    @Scheduled(cron = "${lotto.result-checker.lotteryRunOccurrence}")
    public WinningTicketsDto generateResults() throws WinningNumbersNotFoundException, OutOfRangeException, IncorrectSizeException {
        log.info("ResultCheckerScheduler started...");
        if (!winningNumbersFacade.areWinningNumbersGeneratedByDate()) {
            log.error("Winning numbers are not generated");
            throw new RuntimeException("Winning numbers are not generated");
        }
        log.info("Winning numbers has been fetched");
        return resultCheckerFacade.generateResults();
    }
}
