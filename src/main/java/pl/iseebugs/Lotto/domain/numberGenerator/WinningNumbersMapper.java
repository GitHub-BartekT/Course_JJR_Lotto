package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDTO;

import java.util.Set;
import java.util.stream.Collectors;

class WinningNumbersMapper {

    static WinningNumbersDTO toWinningNumbersDTO(WinningNumbers winningNumbersDelivered){
        return WinningNumbersDTO.builder()
                .winningNumbers(winningNumbersDelivered.winningNumbers())
                .drawDate(winningNumbersDelivered.drawDate())
                .build();
    }
}
