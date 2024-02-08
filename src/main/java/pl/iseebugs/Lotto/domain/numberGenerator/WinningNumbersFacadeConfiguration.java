package pl.iseebugs.Lotto.domain.numberGenerator;

class WinningNumbersFacadeConfiguration {
    WinningNumbersFacade winningNumbersFacade(WinningNumbersRepository winningNumbersRepository){
        return new WinningNumbersFacade(winningNumbersRepository);
    }

}
