package pl.iseebugs.Lotto.domain.numberGenerator;

import org.junit.jupiter.api.Test;
import pl.iseebugs.Lotto.domain.numberGenerator.dto.WinningNumbersDto;
import pl.iseebugs.Lotto.domain.numberReceiver.NumberReceiverFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WinningNumbersFacadeTest {

    static Clock myClock = Clock.fixed(LocalDateTime.of(2024, 2, 6, 12, 0, 0).toInstant(UTC), ZoneId.systemDefault());

    @Test
    void generateWinningNumbers_should_return_six_numbers() throws OutOfRangeException, IncorrectSizeException {
        //given
        WinningNumbersDto result = getWinningNumbersDTO();
        //then
        assertThat(result.winningNumbers().size()).isEqualTo(6);
    }

    @Test
    void generateWinningNumbers_should_return_six_numbers_with_require_range() throws OutOfRangeException, IncorrectSizeException {
        //given
        WinningNumbersDto result = getWinningNumbersDTO();
        //then
        boolean isInRange = result.winningNumbers().stream().allMatch(number -> 1 <= number && number <=99 );
        assertThat(isInRange).isTrue();
    }

    @Test
    void generateWinningNumbers_should_return_six_unique_numbers() throws OutOfRangeException, IncorrectSizeException {
        //given
        WinningNumbersDto result = getWinningNumbersDTO();
        //then
        Set<Integer> uniqueNumbers = new HashSet<>(result.winningNumbers());
        assertThat(uniqueNumbers.size()).isEqualTo(6);
    }

    @Test
    void generateWinningNumbers_should_throw_exception_when_at_least_one_number_is_out_of_range() throws OutOfRangeException {
        //when
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));

        RandomNumbersGenerable winningNumberGenerator = new RandomNumbersGeneratorTestImpl(Set.of(1,5,100,2,3,4));
        //system under test
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                winningNumberGenerator,
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
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));

        RandomNumbersGenerable winningNumberGenerator = new RandomNumbersGeneratorTestImpl(Set.of(1,2,3,4,5,6,7));
        //system under test
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                winningNumberGenerator,
                mockNumberReceiverFacade);
        //when
        var exception = catchThrowable(toTest::generateWinningNumbers);

        //then
        assertThat(exception).isInstanceOf(IncorrectSizeException.class);
    }

    @Test
    void getWinningNumbersByDate_should_throw_exception_when_no_data() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException {
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                new RandomNumbersGenerator(),
                mockNumberReceiverFacade);
        //when
        var exception = catchThrowable(() ->toTest.getWinningNumbersByDate(LocalDateTime.now(myClock)));
        //then
        assertThat(exception).isInstanceOf(WinningNumbersNotFoundException.class);
    }

    @Test
    void getWinningNumbersByDate_should_return_data() throws OutOfRangeException, IncorrectSizeException, WinningNumbersNotFoundException {
        //given
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                new RandomNumbersGenerator(),
                mockNumberReceiverFacade);
        //when
        toTest.generateWinningNumbers();
        WinningNumbersDto result = toTest.getWinningNumbersByDate(LocalDateTime.now(myClock));
        //then
        assertThat(result).isNotNull();
        assertThat(result.drawDate()).isEqualTo(LocalDateTime.now(myClock));
    }

    @Test
    void getAllWinningNumbers_should_return_empty_list_when_no_data() {
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                new RandomNumbersGenerator(),
                mockNumberReceiverFacade);
        //when
       List<WinningNumbersDto> result = toTest.getAllWinningNumbers();
        //then
        assertThat(result.size()).isEqualTo(0);
        assertThat(result).isNotNull();
    }

    @Test
    void getAllWinningNumbers_should_return_list_when_data_ok() throws OutOfRangeException, IncorrectSizeException {
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                new RandomNumbersGenerator(),
                mockNumberReceiverFacade);
        //when
        toTest.generateWinningNumbers();
        List<WinningNumbersDto> result = toTest.getAllWinningNumbers();
        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).drawDate()).isEqualTo(LocalDateTime.now(myClock));
    }

    private static WinningNumbersDto getWinningNumbersDTO() throws OutOfRangeException, IncorrectSizeException {
        NumberReceiverFacade mockNumberReceiverFacade = mock(NumberReceiverFacade.class);
        when(mockNumberReceiverFacade.generateNextDrawDate()).thenReturn(LocalDateTime.now(myClock));
        //system under test
        WinningNumbersFacade toTest = WinningNumbersFacadeConfiguration.createForTests(new InMemoryWinningNumbersRepositoryTestImpl(),
                new RandomNumbersGeneratorTestImpl(),
                mockNumberReceiverFacade);
        //when
        WinningNumbersDto result = toTest.generateWinningNumbers();
        return result;
    }
}