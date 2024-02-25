package pl.iseebugs.Lotto.domain.numberReceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
class NumberReceiverConfiguration {


    @Profile("!integration")
    @Bean
    Clock clock(){return Clock.systemUTC();}

    @Bean
    IdGenerable idGenerable(){return new IdGenerator();}

    @Bean
    static NumberReceiverFacade numberReceiverFacade(IdGenerable idGenerator, TicketRepository repository, Clock clock){
        NumberValidator validator = new NumberValidator();
        GenerateDrawDate generateDrawDate = new GenerateDrawDate(clock);
        return new NumberReceiverFacade(validator, repository, idGenerator, generateDrawDate);
    }
}
