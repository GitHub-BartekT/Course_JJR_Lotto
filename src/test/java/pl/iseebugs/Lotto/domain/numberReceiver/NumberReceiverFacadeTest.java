package pl.iseebugs.Lotto.domain.numberReceiver;

import org.junit.jupiter.api.Test;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;
import pl.lotto.domain.AdjustableClock;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024, 2, 6, 12, 0, 0).toInstant(UTC), ZoneId.systemDefault());

    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock
        );

    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_is_lower_than_1(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, -2, 99, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_is_higher_thane_99(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 100, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_less_then_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_more_then_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 10,12,0,0);
        //when
        LocalDateTime dateBeforeDrawDate = LocalDateTime.of(2024, 2, 5,12,0,0);
        List<TicketDto> ticketDtos = numberReceiverFacade.getTicketsByNextDrawDate(dateBeforeDrawDate);
        //then

        assertThat(ticketDtos).contains(TicketDto.builder()
                .ticketId(result.ticketId())
                .drawDate(drawDate)
                .numbersFromUser(result.numbersFromUser())
                .build());
    }
}