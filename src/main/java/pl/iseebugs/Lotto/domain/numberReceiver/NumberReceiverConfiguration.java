package pl.iseebugs.Lotto.domain.numberReceiver;

import java.time.Clock;

class NumberReceiverConfiguration {

    static NumberReceiverFacade numberReceiverFacade(IdGenerable idGenerator, TicketRepository repository, Clock clock){
        NumberValidator validator = new NumberValidator();
        GenerateDrawDate generateDrawDate = new GenerateDrawDate(clock);
        return new NumberReceiverFacade(validator, repository, idGenerator, generateDrawDate);
    }
}
