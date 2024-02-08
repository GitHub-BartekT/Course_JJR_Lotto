package pl.iseebugs.Lotto.domain.numberGenerator;

class WinningNumbersFacadeConfiguration {
    WinningNumbersFacade winningNumbersFacade(WinningNumbersRepository winningNumbersRepository, WinningNumbersGenerable numbersGenerator){
        return new WinningNumbersFacade(winningNumbersRepository, numbersGenerator);
    }

}
