package pl.iseebugs.Lotto.infrastructure.numberGenerator.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.iseebugs.Lotto.domain.numberGenerator.IncorrectSizeException;
import pl.iseebugs.Lotto.domain.numberGenerator.OutOfRangeException;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
@Component
@Log4j2
public class WinningNumberScheduler {

    private final WinningNumbersFacade winningNumbersFacade;


    @Scheduled(cron = "${lotto.number-generator.lotteryRunOccurrence}")
    public void generateWinningNumbers() throws OutOfRangeException, IncorrectSizeException {
        winningNumbersFacade.generateWinningNumbers();
    }
}
