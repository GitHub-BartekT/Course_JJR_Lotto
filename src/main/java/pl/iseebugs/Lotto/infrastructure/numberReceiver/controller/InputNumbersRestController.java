package pl.iseebugs.Lotto.infrastructure.numberReceiver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Log4j2
@AllArgsConstructor
public class InputNumbersRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<InputNumberResultDto> inputNumbers(@RequestBody InputNumbersRequestDto requestDto){
        log.info(requestDto.inputNumbers());
        Set<Integer> request = new HashSet<>(requestDto.inputNumbers());
        InputNumberResultDto response = numberReceiverFacade.inputNumbers(request);
        return ResponseEntity.ok(response);
    }
}
