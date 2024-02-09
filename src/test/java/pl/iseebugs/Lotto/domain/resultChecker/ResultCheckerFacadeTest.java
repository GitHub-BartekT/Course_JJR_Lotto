package pl.iseebugs.Lotto.domain.resultChecker;

import org.junit.jupiter.api.Test;
import pl.iseebugs.Lotto.domain.numberGenerator.IncorrectSizeException;
import pl.iseebugs.Lotto.domain.numberGenerator.OutOfRangeException;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersNotFoundException;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDto;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.WinningTicketsDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {

    private final TicketResultRepository inMemoryTicketResultRepository = new InMemoryTicketResultRepository();
    private final WinningNumbersFacade mockWinningNumbersFacade = mock(WinningNumbersFacade.class);
    private final NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);

    @Test
    public void should_generate_all_players_with_correct_message() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException {//given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        when(mockWinningNumbersFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(mockNumberReceiverFacade.getTicketsByNextDrawDate()).thenReturn(
                List.of(TicketDto.builder()
                                .ticketId("001")
                                .numbersFromUser(Set.of(1, 2, 3, 4, 5, 6))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("002")
                                .numbersFromUser(Set.of(1, 2, 7, 8, 9, 10))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("003")
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build())
        );
        ResultCheckerFacade resultCheckerFacade =
                ResultCheckerFacadeConfiguration.resultCheckerFacade(mockNumberReceiverFacade,
                        mockWinningNumbersFacade,
                        inMemoryTicketResultRepository);
        //when
        WinningTicketsDto playersDto = resultCheckerFacade.generateResults();
        //then
        List<TicketResultDto> results = playersDto.results();
        TicketResultDto resultDto = TicketResultDto.builder()
                .Id("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build();
        TicketResultDto resultDto1 = TicketResultDto.builder()
                .Id("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        TicketResultDto resultDto2 = TicketResultDto.builder()
                .Id("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        assertThat(results).contains(resultDto, resultDto1, resultDto2);
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners succeeded to retrieve");
    }

    @Test
    public void should_generate_fail_message_when_winningNumbers_equals_null() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException {
        //given
        when(mockWinningNumbersFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(null)
                .build());
        ResultCheckerFacade resultCheckerFacade = ResultCheckerFacadeConfiguration.resultCheckerFacade(
                mockNumberReceiverFacade,
                mockWinningNumbersFacade,
                inMemoryTicketResultRepository);
        //when
        WinningTicketsDto playersDto = resultCheckerFacade.generateResults();
        //then
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners failed to retrieve.");
    }

    @Test
    public void should_generate_fail_message_when_winningNumbers_is_empty() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException {
        //given
        when(mockWinningNumbersFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of())
                .build());
        ResultCheckerFacade resultCheckerFacade = ResultCheckerFacadeConfiguration.resultCheckerFacade(
                mockNumberReceiverFacade,
                mockWinningNumbersFacade,
                inMemoryTicketResultRepository);
        //when
        WinningTicketsDto playersDto = resultCheckerFacade.generateResults();
        //then
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners failed to retrieve.");
    }

    @Test
    public void should_generate_result_with_correct_credentials() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException, TicketResultNotFoundException {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        when(mockWinningNumbersFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build());
        String hash = "001";
        when(mockNumberReceiverFacade.getTicketsByNextDrawDate()).thenReturn(
                List.of(TicketDto.builder()
                                .ticketId(hash)
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("002")
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 13))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .ticketId("003")
                                .numbersFromUser(Set.of(7, 8, 9, 10, 11, 14))
                                .drawDate(drawDate)
                                .build())
        );
        ResultCheckerFacade resultCheckerFacade = ResultCheckerFacadeConfiguration.resultCheckerFacade(
                mockNumberReceiverFacade,
                mockWinningNumbersFacade,
                inMemoryTicketResultRepository);
        resultCheckerFacade.generateResults();
        //when

        TicketResultDto resultDto = resultCheckerFacade.findTicketById(hash);
        //then
        TicketResultDto expectedResult = TicketResultDto.builder()
                .Id(hash)
                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .wonNumbers(winningNumbers)
                .build();
        assertThat(resultDto).isEqualTo(expectedResult);
    }
}