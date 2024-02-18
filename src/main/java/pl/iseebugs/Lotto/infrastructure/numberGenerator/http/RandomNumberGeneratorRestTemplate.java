package pl.iseebugs.Lotto.infrastructure.numberGenerator.http;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.iseebugs.Lotto.domain.numberGenerator.RandomNumbersGenerable;
import pl.iseebugs.Lotto.domain.numberGenerator.SixRandomNumbersDto;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RandomNumberGeneratorRestTemplate implements RandomNumbersGenerable {

    private final RestTemplate restTemplate;

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers() {
        //String urlForService = getUrlForService("/api/v1.0/random");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        final String url = UriComponentsBuilder.fromHttpUrl("http://www.randomnumberapi.com" + ":" + "80" + "/api/v1.0/random")
                .queryParam("min", 1)
                .queryParam("max", 99)
                .queryParam("count", 6)
                .toUriString();
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        List<Integer> numbers = response.getBody();
        System.out.println(numbers);
        return SixRandomNumbersDto.builder()
                .numbers(numbers.stream().collect(Collectors.toSet()))
                .build();
    }

    private String getUrlForService(String service) {
        return "http://www.randomnumberapi.com" + ":" + "80" + "/api/v1.0/random";
    }
}
