package pl.iseebugs.Lotto.domain.numberReceiver;

import lombok.Getter;

import java.util.UUID;

class idGenerator {
    static String id;

    idGenerator(){
        id = UUID.randomUUID().toString();
    }

    static String getRandomId() {
        return id;
    }
}
