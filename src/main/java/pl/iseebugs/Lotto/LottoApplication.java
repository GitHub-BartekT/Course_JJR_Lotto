package pl.iseebugs.Lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningGenerateNumberProperties;
import pl.iseebugs.Lotto.domain.numberGenerator.WinningNumbersFacade;

@SpringBootApplication
@EnableConfigurationProperties({WinningGenerateNumberProperties.class})
@EnableScheduling
@EnableMongoRepositories
public class LottoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);

    }
}

