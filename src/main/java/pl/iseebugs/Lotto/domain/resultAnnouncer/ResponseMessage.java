package pl.iseebugs.Lotto.domain.resultAnnouncer;

enum ResponseMessage {

    WIN("Congratulation, you won!"),
    LOOSE("Unfortunately, you don't won. Try again!"),
    NOT_BAD("You hit some of numbers, Congratulation!");

    final String message;

    ResponseMessage(String status) {
        message = status;
    }
}
