package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.Clock;

class NumberReceiverConfiguration {

    static NumberReceiverFacade numberReceiverFacade(IdGenerable generator, TicketRepository repository,Clock clock){
        NumberValidator validator = new NumberValidator();

        return new NumberReceiverFacade(validator, repository, clock, generator);
    }
}
