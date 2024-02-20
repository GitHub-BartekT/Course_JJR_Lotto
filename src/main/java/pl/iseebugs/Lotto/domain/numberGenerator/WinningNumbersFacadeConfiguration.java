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
