package pl.iseebugs.Lotto.domain.resultAnnouncer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.TicketResultResponseDto;
import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;
import pl.iseebugs.Lotto.domain.resultChecker.TicketResultNotFoundException;

import java.time.Clock;
import java.time.LocalDateTime;

import static pl.iseebugs.Lotto.domain.resultAnnouncer.ResponseMessage.BAD_ID;

@AllArgsConstructor
@Log4j2
public class ResultAnnouncerFacade {

    private final ResponseRepository responseRepository;
    private final ResultCheckerFacade resultCheckerFacade;
    private final Clock clock;

    public ResultResponseDto resultResponse(String ticketId) throws TicketResultNotFoundException, ResultResponseNotFoundException {
        TicketResultResponseDto resultResponseDto;
        if(responseRepository.existsById(ticketId)){
            log.info("You found ticket in ResponseRepository with id: {}", ticketId);
            resultResponseDto = ResponseMapper.toTicketResultDtoFromTicketResponse(
                    responseRepository.findById(ticketId)
                            .orElseThrow(ResultResponseNotFoundException::new));
        } else {
            log.info("You are looking for ticket in ResultChecker with id: {}", ticketId);
            resultResponseDto = ResponseMapper.toTicketResultDto(
                            resultCheckerFacade.findTicketById(ticketId));
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
        if (!resultDto.isWinner()){
            return ResponseMessage.LOOSE;
        } else if (resultDto.hitNumbers().size() == 6){
            return ResponseMessage.WIN;
        }
        return ResponseMessage.NOT_BAD;
    }

    private boolean isAfterDraw(TicketResultResponseDto ticketResultResponseDto){
        return LocalDateTime.now(clock).isAfter(ticketResultResponseDto.drawDate());
    }
}
