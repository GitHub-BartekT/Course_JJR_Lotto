package pl.iseebugs.Lotto.domain.numberReceiver;

import java.util.Set;

class NumberValidator {

    private static final int MAX_NUMBERS_FROM_USERS = 6;
    private static final int MAXIMAL_NUMBER_FROM_USER = 99;
    private static final int MINIMAL_NUMBER_FROM_USER = 1;

    NumberValidator() {}

    boolean filterAllNumbersInTheRange(Set<Integer> numbersFromUser) {
       return numbersFromUser.stream()
               .filter(numbers -> numbers <= MAXIMAL_NUMBER_FROM_USER)
               .filter(numbers -> numbers >= MINIMAL_NUMBER_FROM_USER)
               .count() == MAX_NUMBERS_FROM_USERS;
   }
}

