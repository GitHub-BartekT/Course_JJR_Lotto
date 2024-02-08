package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

class WinningNumbersFacadeConfiguration {
    WinningNumbersFacade winningNumbersFacade(WinningNumbersRepository winningNumbersRepository,
                                              WinningNumbersGenerable numbersGenerator,
                                              NumberReceiverFacade numberReceiverFacade){
        return new WinningNumbersFacade(winningNumbersRepository, numbersGenerator, numberReceiverFacade);
    }

}
