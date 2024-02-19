package pl.iseebugs.Lotto.domain.numberGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
class WinningNumbersFacadeConfiguration {

    @Bean
    WinningNumbersRepository winningNumbersRepository(){
        return new WinningNumbersRepository(){
            @Override
            public WinningNumbers save(WinningNumbers winningNumbers) {
                return null;
            }

            @Override
            public Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate) {
                return Optional.empty();
            }

            @Override
            public List<WinningNumbers> getAllWinningNumbers() {
                return null;
            }
        };
    }

    @Bean
    static WinningNumbersFacade winningNumbersFacade(WinningNumbersRepository winningNumbersRepository,
                                                     RandomNumbersGenerable randomNumbersGenerable,
                                                     NumberReceiverFacade numberReceiverFacade,
                                                     WinningGenerateNumberProperties properties){
        WinningNumberValidator numberValidator = new WinningNumberValidator(properties);
        return new WinningNumbersFacade(winningNumbersRepository, randomNumbersGenerable,
                numberReceiverFacade, numberValidator, properties);
    }


    static WinningNumbersFacade createForTests(WinningNumbersRepository winningNumbersRepository,
                                               RandomNumbersGenerable randomNumbersGenerable,
                                               NumberReceiverFacade numberReceiverFacade){
        WinningGenerateNumberProperties properties = WinningGenerateNumberProperties.builder()
                .upperBound(99)
                .lowerBound(1)
                .count(6)
                .build();
        return winningNumbersFacade(winningNumbersRepository, randomNumbersGenerable,
                numberReceiverFacade, properties);
    }
}
