package pl.iseebugs.Lotto.infrastructure.numberGenerator.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.iseebugs.Lotto.domain.numberGenerator.IncorrectSizeException;
import pl.iseebugs.Lotto.domain.numberGenerator.OutOfRangeException;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;

@AllArgsConstructor
@Component
public class WinningNumberScheduler {

    private final WinningNumbersFacade winningNumbersFacade;

    @Scheduled(cron = "*/3 * * * * *")
    public void generateWinningNumbers() throws OutOfRangeException, IncorrectSizeException {
        winningNumbersFacade.generateWinningNumbers();
    }
}
