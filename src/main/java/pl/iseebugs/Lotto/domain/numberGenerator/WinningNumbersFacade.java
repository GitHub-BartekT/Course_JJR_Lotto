package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDTO;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class WinningNumbersFacade {

    private final WinningNumbersRepository repository;
    private final WinningNumbersGenerable numbersGenerator;
    private final NumberReceiverFacade receiverFacade;

    //TODO: generate Winning Numbers
    public WinningNumbersDTO generateWinningNumbers(){
        LocalDateTime drawDate = receiverFacade.generateNextDrawDate(LocalDateTime.now());
        Set<Integer> winningNumbers = numbersGenerator.drawWinningNumbers();
        WinningNumbersDTO result = WinningNumbersMapper.toWinningNumbersDTO(new WinningNumbers(drawDate, winningNumbers));
        return result;
    }

    //TODO: get winning Numbers by Date
    public WinningNumbersDTO getWinningNumbersByDate(LocalDateTime dateTime){
        WinningNumbersDTO WinningNumbersDTO= null;
        return WinningNumbersDTO;
    }

    //TODO: get all winning numbers
    public List<WinningNumbersDTO> getAllWinningNumbers(){
        return null;
    }


}
