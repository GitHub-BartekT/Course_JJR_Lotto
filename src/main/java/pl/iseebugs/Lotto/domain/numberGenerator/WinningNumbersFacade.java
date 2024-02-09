package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDTO;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WinningNumbersFacade {

    private final WinningNumbersRepository repository;
    private final WinningNumbersGenerable numbersGenerator;
    private final NumberReceiverFacade receiverFacade;
    private final WinningNumberValidator numberValidator;

    public WinningNumbersDTO generateWinningNumbers() throws OutOfRangeException, IncorrectSizeException {
        LocalDateTime drawDate = receiverFacade.generateNextDrawDate();
        Set<Integer> winningNumbers = numbersGenerator.drawWinningNumbers();
        numberValidator.validateWinningNumber(winningNumbers);
        WinningNumbers toSave = new WinningNumbers(drawDate, winningNumbers);
        repository.save(toSave);
        return WinningNumbersMapper.toWinningNumbersDTO(toSave);
    }

    public WinningNumbersDTO getWinningNumbersByDate(LocalDateTime dateTime) throws WinningNumbersNotFoundException {
        WinningNumbersDTO result = WinningNumbersMapper.toWinningNumbersDTO(repository.findWinningNumbersByDrawDate(dateTime).orElseThrow(WinningNumbersNotFoundException::new));
        return result;
    }

    public List<WinningNumbersDTO> getAllWinningNumbers(){
        return repository.getAllWinningNumbers().stream()
                .map(WinningNumbersMapper::toWinningNumbersDTO)
                .collect(Collectors.toList());
    }
}
