package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.Clock;

class NumberReceiverConfiguration {

    static NumberReceiverFacade numberReceiverFacade(IdGenerable idGenerator, TicketRepository repository,Clock clock, GenerateDrawDate generateDrawDate){
        NumberValidator validator = new NumberValidator();
        return new NumberReceiverFacade(validator, repository, clock, idGenerator, generateDrawDate);
    }
}
