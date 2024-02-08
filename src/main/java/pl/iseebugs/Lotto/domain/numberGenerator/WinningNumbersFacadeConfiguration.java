package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

class WinningNumbersFacadeConfiguration {

    static WinningNumbersFacade winningNumbersFacade(WinningNumbersRepository winningNumbersRepository,
                                              WinningNumbersGenerable numbersGenerator,
                                              NumberReceiverFacade numberReceiverFacade){
        WinningNumberValidator numberValidator = new WinningNumberValidator();
        return new WinningNumbersFacade(winningNumbersRepository, numbersGenerator,
                numberReceiverFacade, numberValidator);
    }

}
