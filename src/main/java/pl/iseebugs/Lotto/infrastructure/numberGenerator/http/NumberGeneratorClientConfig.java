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
    public RestTemplate restTemplate(@Value("${lotto.number-generator.http.client.config.connectionTimeout}") int connectionTimeout,
                                     @Value("${lotto.number-generator.http.client.config.readTimeout}") int readTimeout,
                                     RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public RandomNumbersGenerable remoteNumberGeneratorClient(RestTemplate restTemplate,
                                                              @Value("${lotto.number-generator.http.client.config.uri}") String uri,
                                                              @Value("${lotto.number-generator.http.client.config.port}") int port){
        return new RandomNumberGeneratorRestTemplate(restTemplate, uri, port);
    }
}
