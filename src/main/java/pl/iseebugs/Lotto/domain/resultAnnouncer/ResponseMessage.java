package pl.iseebugs.Lotto.domain.resultAnnouncer;

enum ResponseMessage {

    WIN("Congratulation, you won!"),
    LOOSE("Unfortunately, you don't won. Try again!"),
    NOT_BAD("You hit some of numbers, Congratulation!"),
    TO_EARLY("The draw has not taken place yet."),
    BAD_ID("There is no ticket with your id.");

    final String message;

    ResponseMessage(String status) {
        message = status;
    }
}
