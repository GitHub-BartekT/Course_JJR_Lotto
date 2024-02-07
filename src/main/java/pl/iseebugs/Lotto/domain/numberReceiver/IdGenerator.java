package pl.iseebugs.Lotto.domain.numberReceiver;

import java.util.UUID;

class IdGenerator implements IdGenerable{
    static String id;

    IdGenerator(){
        id = UUID.randomUUID().toString();
    }

    public String getRandomId() {
        return id;
    }
}
