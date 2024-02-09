package pl.iseebugs.Lotto.domain.resultChecker;

import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

class ResultCheckerFacadeConfiguration {

    static ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                                   WinningNumbersFacade winningNumbersFacade,
                                                   TicketResultRepository ticketResultRepository){
        WinnersRetriever winnersRetriever = new WinnersRetriever();
        return new ResultCheckerFacade(numberReceiverFacade, winningNumbersFacade,
                ticketResultRepository, winnersRetriever);
    }
}
