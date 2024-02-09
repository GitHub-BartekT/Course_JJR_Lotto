package pl.iseebugs.Lotto.domain.resultAnnouncer;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.TicketResultResponseDto;
import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;
import pl.iseebugs.Lotto.domain.resultChecker.TicketResultNotFoundException;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
public class ResultAnnouncerFacade {

    ResponseRepository responseRepository;
    ResultCheckerFacade resultCheckerFacade;
    Clock clock;

    public ResultResponseDto resultResponse(String ticketId) throws TicketResultNotFoundException, ResultResponseNotFoundException {
        TicketResultResponseDto resultResponseDto;
        if(responseRepository.existsById(ticketId)){
            resultResponseDto = ResponseMapper.toTicketResultDtoFromTicketResponse(
                    responseRepository.findById(ticketId)
                            .orElseThrow(ResultResponseNotFoundException::new));
        } else if (responseRepository.existsById(ticketId)){
            resultResponseDto = ResponseMapper.toTicketResultDto(
                            resultCheckerFacade.findTicketById(ticketId));
        } else {
            return ResponseMapper.resultAnnouncerResponseDto(
                    null, ResponseMessage.BAD_ID);
        }


        if(!isAfterDraw(resultResponseDto)){
            return ResponseMapper.resultAnnouncerResponseDto(
                    null, ResponseMessage.TO_EARLY);
        }

        ResponseMessage responseMessage = validateResponse(resultResponseDto);

        ResultResponseDto responseDto =
                ResponseMapper.resultAnnouncerResponseDto(resultResponseDto, responseMessage);
        return responseDto;
    }

    private ResponseMessage validateResponse(TicketResultResponseDto resultDto){
        if (resultDto.isWinner()){
            return ResponseMessage.WIN;
        } else if (resultDto.hitNumbers().size() >= 3){
            return ResponseMessage.NOT_BAD;
        }
        return ResponseMessage.LOOSE;
    }

    private boolean isAfterDraw(TicketResultResponseDto ticketResultResponseDto){
        return LocalDateTime.now(clock).isAfter(ticketResultResponseDto.drawDate());
    }
}
