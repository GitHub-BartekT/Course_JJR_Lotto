package pl.iseebugs.Lotto.infrastructure.numberReceiver.controller;

import java.util.List;

public record InputNumbersRequestDto(List<Integer> inputNumbers) {
}
