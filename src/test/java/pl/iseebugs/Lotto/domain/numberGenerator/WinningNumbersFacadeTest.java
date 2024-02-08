package pl.iseebugs.Lotto.domain.numberGenerator;

import org.junit.jupiter.api.Test;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDTO;
import pl.iseebugs.Lotto.domain.numberReceiver.InMemoryTicketRepositoryTestImpl;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WinningNumbersFacadeTest {

    Clock myClock = Clock.fixed(LocalDateTime.of(2024, 2, 6, 12, 0, 0).toInstant(UTC), ZoneId.systemDefault());

    @Test
    void generateWinningNumbers_should_return_six_numbers() throws OutOfRangeException, IncorrectSizeException {
        //given
        WinningNumbersDTO result = getWinningNumbersDTO();
        //then
        assertThat(result.winningNumbers().size()).isEqualTo(6);
    }

    @Test
    void generateWinningNumbers_should_return_six_numbers_with_require_range() throws OutOfRangeException, IncorrectSizeException {
        //given
        WinningNumbersDTO result = getWinningNumbersDTO();
        //then
        boolean isInRange = result.winningNumbers().stream().allMatch(number -> 1 <= number && number <=99 );
        assertThat(isInRange).isTrue();
    }

    @Test
    void generateWinningNumbers_should_return_six_unique_numbers() throws OutOfRangeException, IncorrectSizeException {
        //given
        WinningNumbersDTO result = getWinningNumbersDTO();
        //then
        Set<Integer> uniqueNumbers = new HashSet<>(result.winningNumbers());
        assertThat(uniqueNumbers.size()).isEqualTo(6);
    }

    @Test
    void generateWinningNumbers_should_throw_exception_when_at_least_one_number_is_out_of_range() throws OutOfRangeException {
        //when
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate(any())).thenReturn(LocalDateTime.of(2024, 2, 6, 7, 23, 0));

        WinningNumbersGenerator mockWinningNumberGenerator = mock(WinningNumbersGenerator.class);
        Set<Integer> set = new HashSet<>(Set.of(1,5,100,2,3,4));
        when(mockWinningNumberGenerator.drawWinningNumbers()).thenReturn(set);
        //system under test
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.winningNumbersFacade(new InMemoryWinningNumbersRepositoryTestImpl(),
                mockWinningNumberGenerator,
                mockNumberReceiverFacade);
        //when
        var exception = catchThrowable(toTest::generateWinningNumbers);

        //then
        assertThat(exception).isInstanceOf(OutOfRangeException.class);
    }

    @Test
    void generateWinningNumbers_should_throw_exception_when_incorrect_number_of_numbers() throws OutOfRangeException {
        //when
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate(any())).thenReturn(LocalDateTime.of(2024, 2, 6, 7, 23, 0));

        WinningNumbersGenerator mockWinningNumberGenerator = mock(WinningNumbersGenerator.class);
        Set<Integer> set = new HashSet<>(Set.of(1,2,3,4,5,6,7));
        when(mockWinningNumberGenerator.drawWinningNumbers()).thenReturn(set);
        //system under test
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.winningNumbersFacade(new InMemoryWinningNumbersRepositoryTestImpl(),
                mockWinningNumberGenerator,
                mockNumberReceiverFacade);
        //when
        var exception = catchThrowable(toTest::generateWinningNumbers);

        //then
        assertThat(exception).isInstanceOf(IncorrectSizeException.class);
    }

    @Test
    void getWinningNumbersByDate() {
    }

    @Test
    void getAllWinningNumbers() {
    }

    private static WinningNumbersDTO getWinningNumbersDTO() throws OutOfRangeException, IncorrectSizeException {
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        when(numberReceiverFacade.generateNextDrawDate(any())).thenReturn(any(LocalDateTime.class));
        //system under test
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.winningNumbersFacade(new InMemoryWinningNumbersRepositoryTestImpl(),
                new WinningNumbersGenerator(),
                numberReceiverFacade);
        //when
        WinningNumbersDTO result = toTest.generateWinningNumbers();
        return result;
    }
}