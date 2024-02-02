package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.AllArgsConstructor;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;

import java.util.Set;

@AllArgsConstructor
public class NumberReceiverFacade {

    private NumberValidator validator;

    public NumberReceiverFacade() {
        this.validator = new NumberValidator();
    }

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser){
        if (validator.filterAllNumbersInTheRange(numbersFromUser)){
            return new InputNumberResultDto("success");
        }
        return new InputNumberResultDto("failed");
    }
}
