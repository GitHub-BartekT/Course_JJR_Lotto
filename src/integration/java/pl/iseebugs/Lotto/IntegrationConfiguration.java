package pl.iseebugs.Lotto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Configuration
@Profile("integration")
public class IntegrationConfiguration {

    @Bean
    @Primary
    pl.lotto.domain.AdjustableClock clock() {
        return pl.lotto.domain.AdjustableClock.ofLocalDateAndLocalTime(LocalDate.of(2024,2,9), LocalTime.of(10,0), ZoneId.systemDefault());
    }
}
