package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDto;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
public class WinningNumbersFacade {

    private final WinningNumbersRepository repository;
    private final RandomNumbersGenerable numbersGenerator;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumberValidator numberValidator;
    private final WinningGenerateNumberProperties properties;

    public WinningNumbersDto generateWinningNumbers() throws OutOfRangeException, IncorrectSizeException {
        LocalDateTime drawDate = numberReceiverFacade.generateNextDrawDate();
        log.info("Draw date: {}", drawDate);
        SixRandomNumbersDto dto = numbersGenerator.generateSixRandomNumbers(properties.count(), properties.lowerBound(), properties.upperBound());
        Set<Integer> winningNumbers = dto.numbers();
        numberValidator.validateWinningNumber(winningNumbers);
        WinningNumbers toSave = WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .drawDate(drawDate)
        .build();
        repository.save(toSave);
        return WinningNumbersMapper.toWinningNumbersDTO(toSave);
    }

    public WinningNumbersDto getWinningNumbersByDate(LocalDateTime dateTime) throws WinningNumbersNotFoundException {
        WinningNumbersDto result = WinningNumbersMapper.toWinningNumbersDTO(repository.findWinningNumbersByDrawDate(dateTime).orElseThrow(WinningNumbersNotFoundException::new));
        log.info("Get Winning numbers by Date: {}, {}", dateTime, result.winningNumbers());
        return result;
    }

    public List<WinningNumbersDto> getAllWinningNumbers(){
        return Optional.of(repository.findAll().stream()
                .map(WinningNumbersMapper::toWinningNumbersDTO)
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public boolean areWinningNumbersGeneratedByDate(){
        LocalDateTime nextDrawDate = numberReceiverFacade.generateNextDrawDate();
        return repository.existsByDrawDate(nextDrawDate);
    }
}
