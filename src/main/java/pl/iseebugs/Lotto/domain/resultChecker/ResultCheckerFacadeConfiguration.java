package pl.iseebugs.Lotto.domain.resultChecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

@Configuration
class ResultCheckerFacadeConfiguration {

    @Bean
    static ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                   WinningNumbersFacade winningNumbersFacade,
                                                   TicketResultRepository ticketResultRepository){
        WinnersRetriever winnersRetriever = new WinnersRetriever();
        return new ResultCheckerFacade(numberReceiverFacade, winningNumbersFacade,
                ticketResultRepository, winnersRetriever);
    }
}
