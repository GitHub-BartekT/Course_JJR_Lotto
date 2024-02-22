package pl.iseebugs.Lotto.infrastructure.resultAnnouncer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.iseebugs.Lotto.domain.resultAnnouncer.ResultAnnouncerFacade;
import pl.iseebugs.Lotto.domain.resultAnnouncer.ResultResponseNotFoundException;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;
import pl.iseebugs.Lotto.domain.resultChecker.TicketResultNotFoundException;

@RestController
@RequestMapping("/results")
@AllArgsConstructor
public class ResultAnnouncerRestController {

    ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping("/{id}")
    ResponseEntity<ResultResponseDto> getTicketResult(@PathVariable String id) throws ResultResponseNotFoundException, TicketResultNotFoundException {
        ResultResponseDto result = resultAnnouncerFacade.resultResponse(id);
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(TicketResultNotFoundException.class)
    ResponseEntity<String> handlerIllegalArgument(TicketResultNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);    }

}
