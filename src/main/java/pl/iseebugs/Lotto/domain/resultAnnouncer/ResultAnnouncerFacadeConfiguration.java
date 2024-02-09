package pl.iseebugs.Lotto.domain.resultAnnouncer;

import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;

import java.time.Clock;

class ResultAnnouncerFacadeConfiguration {
    static ResultAnnouncerFacade resultAnnouncerFacade(
        ResponseRepository responseRepository,
        ResultCheckerFacade resultCheckerFacade,
        Clock clock){
        return new ResultAnnouncerFacade(responseRepository, resultCheckerFacade, clock);
    }
}
