package pl.iseebugs.Lotto.infrastructure.numberGenerator.http;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
public class RandomNumberGeneratorRestTemplate implements RandomNumbersGenerable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBound, int upperBound) {
        String urlForService = getUrlForService("/api/v1.0/random");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        final String url = UriComponentsBuilder.fromHttpUrl(urlForService)
                .queryParam("min", lowerBound)
                .queryParam("max", upperBound)
                .queryParam("count", count)
                .toUriString();
        log.info("Http: {}", url);
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        Set<Integer> numbers = new HashSet<>(response.getBody());
        Set<Integer> result = numbers.stream().limit(6).collect(Collectors.toSet());
        return SixRandomNumbersDto.builder()
                .numbers(result)
                .build();
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
