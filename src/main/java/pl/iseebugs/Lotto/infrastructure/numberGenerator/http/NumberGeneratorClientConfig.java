package pl.iseebugs.Lotto.infrastructure.numberGenerator.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.iseebugs.Lotto.domain.numberGenerator.RandomNumbersGenerable;

import java.time.Duration;

@Configuration
public class NumberGeneratorClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }

    @Bean
    public RandomNumbersGenerable remoteNumberGeneratorClient(RestTemplate restTemplate,
                                                              @Value("${lotto.number-generator.http.client.config.uri}") String uri,
                                                              @Value("${lotto.number-generator.http.client.config.port}") int port){
        return new RandomNumberGeneratorRestTemplate(restTemplate, uri, port);
    }
}
