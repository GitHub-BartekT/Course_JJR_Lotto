package pl.iseebugs.Lotto.domain.numberGenerator;

import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDto;

class WinningNumbersMapper {

    static WinningNumbersDto toWinningNumbersDTO(WinningNumbers winningNumbersDelivered){
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbersDelivered.winningNumbers())
                .drawDate(winningNumbersDelivered.drawDate())
                .build();
    }
}
