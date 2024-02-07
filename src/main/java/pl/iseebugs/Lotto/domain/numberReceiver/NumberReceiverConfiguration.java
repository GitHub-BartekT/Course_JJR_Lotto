package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.Clock;

class NumberReceiverConfiguration {

    static NumberReceiverFacade numberReceiverFacade(NumberValidator validator, TicketRepository repository,Clock clock){
        return new NumberReceiverFacade(validator, repository, clock);
    }
}
