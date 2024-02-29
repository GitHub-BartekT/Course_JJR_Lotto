package pl.iseebugs.Lotto.http.numberGenerator;

import org.springframework.web.client.RestTemplate;
import pl.iseebugs.Lotto.domain.numberGenerator.RandomNumbersGenerable;
import pl.iseebugs.Lotto.infrastructure.numberGenerator.http.NumberGeneratorClientConfig;

public class RandomNumberGeneratorRestTemplateIntegrationTestConfig extends NumberGeneratorClientConfig{

    public RandomNumbersGenerable remoteNumberGeneratorClient(int port, int connectionTimeout, int readTimeout){
        RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteNumberGeneratorClient(restTemplate, "http://localhost", port);
    }
}
