package pl.iseebugs.Lotto.domain.numberReceiver;

import java.util.List;
import java.util.Set;

public class NumberReceiverFacade {

    public String inputNumbers(Set<Integer> numbersFromUser){
        if (filterAllNumbersInTheRange(numbersFromUser)){
            return "success";
        }
        return "failed";
    }

    private boolean filterAllNumbersInTheRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .filter(numbers -> numbers <= 99)
                .filter(numbers -> numbers >= 1)
                .count() == 6;
    }
}
