package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDTO;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class WinningNumbersFacade {

    private final WinningNumbersRepository repository;
    private final WinningNumbersGenerable numbersGenerator;

    //TODO: generate Winning Numbers
    public WinningNumbersDTO generateWinningNumbers(){
        WinningNumbersDTO WinningNumbersDTO= null;
        return WinningNumbersDTO;
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
