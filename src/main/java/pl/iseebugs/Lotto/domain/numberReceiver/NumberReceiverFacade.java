package pl.iseebugs.Lotto.domain.numberReceiver;

import java.util.List;
import java.util.Set;

public class NumberReceiverFacade {

    public String inputNumbers(Set<Integer> numbersFromUser){
        List<Integer> numbersInTheRange = filterAllNumbersInTheRange(numbersFromUser);

        if (hasCorrectNumberOfNumbers(numbersInTheRange)){
            return "failed";
        }
        return "success";
    }

    private List<Integer> filterAllNumbersInTheRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .filter(numbers -> numbers <= 99)
                .filter(numbers -> numbers >= 1)
                .toList();
    }

    private boolean hasCorrectNumberOfNumbers(List<Integer> numbersInTheRange) {
        return numbersInTheRange.size() != 6;
    }
}
