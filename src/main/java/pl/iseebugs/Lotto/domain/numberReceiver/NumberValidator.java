package pl.iseebugs.Lotto.domain.numberReceiver;

import java.util.Set;

class NumberValidator {

    NumberValidator() {}

    boolean filterAllNumbersInTheRange(Set<Integer> numbersFromUser) {
       return numbersFromUser.stream()
               .filter(numbers -> numbers <= 99)
               .filter(numbers -> numbers >= 1)
               .count() == 6;
   }
}

