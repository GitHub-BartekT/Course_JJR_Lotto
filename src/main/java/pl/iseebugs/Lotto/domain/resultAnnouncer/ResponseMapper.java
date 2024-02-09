package pl.iseebugs.Lotto.domain.resultAnnouncer;


import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;

class ResponseMapper {
    static ResultResponseDto toResultResponseDto(ResultResponse resultResponse){
        return ResultResponseDto.builder()
                .Id(resultResponse.Id())
                .numbers(resultResponse.numbers())
                .hitNumbers(resultResponse.hitNumbers())
                .drawDate(resultResponse.drawDate())
                .isWinner(resultResponse.isWinner())
                .wonNumbers(resultResponse.wonNumbers())
                .build();
    }
}
