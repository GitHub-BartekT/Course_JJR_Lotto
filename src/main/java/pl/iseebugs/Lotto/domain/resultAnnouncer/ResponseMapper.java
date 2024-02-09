package pl.iseebugs.Lotto.domain.resultAnnouncer;


import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.TicketResultDto;

class ResponseMapper {
    static TicketResultDto toTicketResultDto(TicketResponse ticketResponse){
        return TicketResultDto.builder()
                .Id(ticketResponse.Id())
                .numbers(ticketResponse.numbers())
                .hitNumbers(ticketResponse.hitNumbers())
                .drawDate(ticketResponse.drawDate())
                .isWinner(ticketResponse.isWinner())
                .wonNumbers(ticketResponse.wonNumbers())
                .build();
    }

    static ResultResponseDto resultAnnouncerResponseDto(TicketResultDto ticketResultDto,
                                                        ResponseMessage responseMessage){
        return ResultResponseDto.builder()
                .ticketResult(ticketResultDto)
                .message(responseMessage.message)
                .build();
    }
}
