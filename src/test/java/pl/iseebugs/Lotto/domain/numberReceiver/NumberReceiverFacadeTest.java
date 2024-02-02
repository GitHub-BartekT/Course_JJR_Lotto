package pl.iseebugs.Lotto.domain.numberReceiver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberReceiverFacadeTest {
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();

    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        String result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result).isEqualTo("success");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_is_lower_than_1(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, -2, 99, 4, 5, 6);
        //when
        String result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_is_higher_thane_99(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 100, 4, 5, 6);
        //when
        String result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_less_then_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        //when
        String result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_more_then_six_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        String result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result).isEqualTo("failed");
    }
}