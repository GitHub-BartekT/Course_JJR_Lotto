package pl.iseebugs.Lotto.domain.numberReceiver;

import org.junit.jupiter.api.Test;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.TicketDto;
import pl.lotto.domain.AdjustableClock;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    // 2024.02.06 is Tuesday
    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024, 2, 6, 7, 23, 0).toInstant(UTC), ZoneId.systemDefault());

    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_is_lower_than_1(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, -2, 99, 4, 5, 6);
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_is_higher_thane_99(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 100, 4, 5, 6);
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_less_then_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_more_then_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_correct_draw_date_when_ticket_date_is_not_saturday(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 10,12,0,0);
        //and
        LocalDateTime dateBeforeDrawDate = LocalDateTime.of(2024, 2, 7,12,0,0);
        List<TicketDto> ticketDtos = toTest.getTicketsByNextDrawDate(dateBeforeDrawDate);
        //then

        assertThat(ticketDtos).contains(TicketDto.builder()
                .ticketId(result.ticketId())
                .drawDate(drawDate)
                .numbersFromUser(result.numbersFromUser())
                .build());
    }

    @Test
    public void should_return_correct_draw_date_when_date_is_saturday_before_noon(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        clock.advanceInTimeBy(Duration.ofDays(4));
        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);
        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 10,12,0,0);
        //and
        LocalDateTime dateBeforeDrawDate = LocalDateTime.of(2024, 2, 7,12,0,0);
        List<TicketDto> ticketDtos = toTest.getTicketsByNextDrawDate(dateBeforeDrawDate);
        //then

        assertThat(ticketDtos).contains(TicketDto.builder()
                .ticketId(result.ticketId())
                .drawDate(drawDate)
                .numbersFromUser(result.numbersFromUser())
                .build());
    }

    @Test
    public void should_return_correct_draw_date_when_date_is_saturday_after_noon(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        clock.advanceInTimeBy(Duration.ofDays(4));
        System.out.println(clock);
        clock.advanceInTimeBy(Duration.ofHours(8));
        System.out.println(clock);

        //system under test
        NumberReceiverFacade toTest = NumberReceiverConfiguration.numberReceiverFacade(new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                clock);

        //when
        InputNumberResultDto result = toTest.inputNumbers(numbersFromUser);
        System.out.println(result.drawDate());
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 17,12,0,0);

        //and
        LocalDateTime dateBeforeDrawDate = LocalDateTime.of(2024, 2, 13,12,0,0);
        List<TicketDto> ticketDtos = toTest.getTicketsByNextDrawDate(dateBeforeDrawDate);
        //then

        assertThat(ticketDtos).contains(TicketDto.builder()
                .ticketId(result.ticketId())
                .drawDate(drawDate)
                .numbersFromUser(result.numbersFromUser())
                .build());
    }
}