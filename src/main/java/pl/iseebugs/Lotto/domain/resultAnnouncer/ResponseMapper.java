package pl.iseebugs.Lotto.domain.resultAnnouncer;


import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.TicketResultResponseDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;

class ResponseMapper {
    static TicketResultResponseDto toTicketResultDtoFromTicketResponse(TicketResponse ticketResponse){
        return TicketResultResponseDto.builder()
                .Id(ticketResponse.Id())
                .numbers(ticketResponse.numbers())
                .hitNumbers(ticketResponse.hitNumbers())
                .drawDate(ticketResponse.drawDate())
                .isWinner(ticketResponse.isWinner())
                .wonNumbers(ticketResponse.wonNumbers())
                .build();
    }

    static TicketResultResponseDto toTicketResultDto(TicketResultDto ticketResponse){
        return TicketResultResponseDto.builder()
                .Id(ticketResponse.Id())
                .numbers(ticketResponse.numbers())
                .hitNumbers(ticketResponse.hitNumbers())
                .drawDate(ticketResponse.drawDate())
                .isWinner(ticketResponse.isWinner())
                .wonNumbers(ticketResponse.wonNumbers())
                .build();
    }

    static ResultResponseDto resultAnnouncerResponseDto(TicketResultResponseDto ticketResultResponseDto,
                                                        ResponseMessage responseMessage){
        return ResultResponseDto.builder()
                .ticketResult(ticketResultResponseDto)
                .message(responseMessage.message)
                .build();
    }
}
