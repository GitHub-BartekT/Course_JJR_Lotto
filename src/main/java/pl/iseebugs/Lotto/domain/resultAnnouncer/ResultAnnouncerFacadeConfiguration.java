package pl.iseebugs.Lotto.domain.resultAnnouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;

import java.time.Clock;
@Configuration
class ResultAnnouncerFacadeConfiguration {

    @Bean
    static ResultAnnouncerFacade resultAnnouncerFacade(
        ResponseRepository responseRepository,
        ResultCheckerFacade resultCheckerFacade,
        Clock clock){
        return new ResultAnnouncerFacade(responseRepository, resultCheckerFacade, clock);
    }
}
