package pl.iseebugs.Lotto.domain.resultAnnouncer;

import org.junit.jupiter.api.Test;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.ResultResponseDto;
import pl.iseebugs.Lotto.domain.resultAnnouncer.dto.TicketResultResponseDto;
import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;
import pl.iseebugs.Lotto.domain.resultChecker.TicketResultNotFoundException;
import pl.lotto.domain.AdjustableClock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

class ResultAnnouncerFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024, 2, 6, 7, 23, 0).toInstant(UTC), ZoneId.systemDefault());

    @Test
    void resultResponse_should_return_winning_message_when_ticket_is_winning() throws ResultResponseNotFoundException, TicketResultNotFoundException {
        //given
        InMemoryResponseRepositoryTestImpl inMemoryResponseRepository = new InMemoryResponseRepositoryTestImpl();
        ResultCheckerFacade mockResultCheckerFacade = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade resultAnnouncerFacade = ResultAnnouncerFacadeConfiguration.resultAnnouncerFacade(
                inMemoryResponseRepository,
                mockResultCheckerFacade,
                clock);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 3, 12, 0, 0);
        TicketResponse resultDto = TicketResponse.builder()
                .id("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4,5 ,6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        inMemoryResponseRepository.save(resultDto);
        //when
        ResultResponseDto result = resultAnnouncerFacade.resultResponse("123");
        //then
        assertThat(result.message()).isEqualTo("Congratulation, you won!");
    }

    @Test
    void resultResponse_should_return_lose_message_when_ticket_is_loosing() throws ResultResponseNotFoundException, TicketResultNotFoundException {
        //given
        InMemoryResponseRepositoryTestImpl inMemoryResponseRepository = new InMemoryResponseRepositoryTestImpl();
        ResultCheckerFacade mockResultCheckerFacade = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade resultAnnouncerFacade = ResultAnnouncerFacadeConfiguration.resultAnnouncerFacade(
                inMemoryResponseRepository,
                mockResultCheckerFacade,
                clock);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 3, 12, 0, 0);
        TicketResponse resultDto = TicketResponse.builder()
                .id("123")
                .numbers(Set.of(7,8,9,10,11,12))
                .hitNumbers(Set.of())
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        inMemoryResponseRepository.save(resultDto);
        //when
        ResultResponseDto result = resultAnnouncerFacade.resultResponse("123");
        //then
        assertThat(result.message()).isEqualTo("Unfortunately, you don't won. Try again!");
    }

    @Test
    void resultResponse_should_return_not_bad_message_when_ticket_hit_at_least_three_numbers() throws ResultResponseNotFoundException, TicketResultNotFoundException {
        //given
        InMemoryResponseRepositoryTestImpl inMemoryResponseRepository = new InMemoryResponseRepositoryTestImpl();
        ResultCheckerFacade mockResultCheckerFacade = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade resultAnnouncerFacade = ResultAnnouncerFacadeConfiguration.resultAnnouncerFacade(
                inMemoryResponseRepository,
                mockResultCheckerFacade,
                clock);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 3, 12, 0, 0);
        TicketResponse resultDto = TicketResponse.builder()
                .id("123")
                .numbers(Set.of(1, 2, 3, 7, 8, 9))
                .hitNumbers(Set.of(1, 2,3))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        inMemoryResponseRepository.save(resultDto);
        //when
        ResultResponseDto result = resultAnnouncerFacade.resultResponse("123");
        //then
        assertThat(result.message()).isEqualTo("You hit some of numbers, Congratulation!");
    }

    @Test
    void resultResponse_should_return_bad_id_message_when_ticket_is_not_exists() throws ResultResponseNotFoundException, TicketResultNotFoundException {
        //given
        InMemoryResponseRepositoryTestImpl inMemoryResponseRepository = new InMemoryResponseRepositoryTestImpl();
        ResultCheckerFacade mockResultCheckerFacade = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade resultAnnouncerFacade = ResultAnnouncerFacadeConfiguration.resultAnnouncerFacade(
                inMemoryResponseRepository,
                mockResultCheckerFacade,
                clock);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 3, 12, 0, 0);
        TicketResponse resultDto = TicketResponse.builder()
                .id("123")
                .build();
        inMemoryResponseRepository.save(resultDto);
        //when
        Throwable result = catchThrowable(() -> resultAnnouncerFacade.resultResponse("456"));
        //then
        assertThat(result).isInstanceOf(Exception.class);
    }

    @Test
    void resultResponse_should_return_to_early_message_when_before_draw() throws ResultResponseNotFoundException, TicketResultNotFoundException {
        //given
        InMemoryResponseRepositoryTestImpl inMemoryResponseRepository = new InMemoryResponseRepositoryTestImpl();
        ResultCheckerFacade mockResultCheckerFacade = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade resultAnnouncerFacade = ResultAnnouncerFacadeConfiguration.resultAnnouncerFacade(
                inMemoryResponseRepository,
                mockResultCheckerFacade,
                clock);
        LocalDateTime drawDate = LocalDateTime.of(2024, 2, 24, 12, 0, 0);
        TicketResponse resultDto = TicketResponse.builder()
                .id("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2,3,10,11,12))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        inMemoryResponseRepository.save(resultDto);
        //when
        ResultResponseDto result = resultAnnouncerFacade.resultResponse("123");
        //then
        assertThat(result.message()).isEqualTo("The draw has not taken place yet.");
    }
}