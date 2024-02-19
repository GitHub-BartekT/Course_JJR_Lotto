package pl.iseebugs.Lotto.domain.numberGenerator;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lotto.number-generator.facade")
@Builder
public record WinningGenerateNumberProperties (int count, int lowerBound, int upperBound){}
