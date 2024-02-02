package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class NumberReceiverFacade {

    private NumberValidator validator;

    public NumberReceiverFacade() {
        this.validator = new NumberValidator();
    }

    public String inputNumbers(Set<Integer> numbersFromUser){
        if (validator.filterAllNumbersInTheRange(numbersFromUser)){
            return "success";
        }
        return "failed";
    }
}
