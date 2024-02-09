package pl.iseebugs.Lotto.domain.resultChecker;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
class ResultCheckerFacade {

    NumberReceiverFacade numberReceiverFacade;

    public void generateWinners(LocalDateTime drawDate){
    }

    public void generateResult(LocalDateTime drawDate){

    }
}
