package pl.iseebugs.Lotto.domain.numberReceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
class NumberReceiverConfiguration {

    @Bean
    Clock clock(){return Clock.systemUTC();}

    @Bean
    IdGenerable idGenerable(){return new IdGenerator();}

    @Bean
    TicketRepository ticketRepository(){
        return new TicketRepository() {
            @Override
            public Ticket save(Ticket ticket) {
                return null;
            }

            @Override
            public List<Ticket> findAllTicketsByDrawDate(LocalDateTime date) {
                return null;
            }

            @Override
            public Optional<Ticket> findTicketByTicketId(String ticketId) {
                return Optional.empty();
            }

            @Override
            public boolean existsByTicketId(String ticketId) {
                return false;
            }
        };
    }

    @Bean
    static NumberReceiverFacade numberReceiverFacade(IdGenerable idGenerator, TicketRepository repository, Clock clock){
        NumberValidator validator = new NumberValidator();
        GenerateDrawDate generateDrawDate = new GenerateDrawDate(clock);
        return new NumberReceiverFacade(validator, repository, idGenerator, generateDrawDate);
    }
}
