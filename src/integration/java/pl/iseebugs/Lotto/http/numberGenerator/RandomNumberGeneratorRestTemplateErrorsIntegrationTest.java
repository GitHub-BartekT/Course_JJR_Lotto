package pl.iseebugs.Lotto.http.numberGenerator;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.iseebugs.Lotto.domain.numberGenerator.RandomNumbersGenerable;
import pl.iseebugs.Lotto.domain.numberGenerator.SixRandomNumbersDto;
import wiremock.org.apache.hc.core5.http.HttpStatus;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class RandomNumberGeneratorRestTemplateErrorsIntegrationTest {

    public static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    public static final String APPLICATION_JSON_CONTENT_TYPE_VALUE = "application/json";


    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    RandomNumbersGenerable randomNumbersGenerable = new RandomNumberGeneratorRestTemplateIntegrationTestConfig().remoteNumberGeneratorClient(
            wireMockServer.getPort(),
            1000,
            1000);

    @Test
    void should_return_200_ok_and_six_numbers(){
        //given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(CONTENT_TYPE_HEADER_KEY, APPLICATION_JSON_CONTENT_TYPE_VALUE)
                        .withBody("""
                                        [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                        """.trim()
                        )));
        //when
        SixRandomNumbersDto response = randomNumbersGenerable.generateSixRandomNumbers(25,1,99);

        //then
        assertThat(response.numbers()).containsExactlyInAnyOrderElementsOf(List.of(1,2,3,4,5,6));
    }

    @Test
    void should_return_null_numbers_when_fault_connection_reset_by_peer(){
        //given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(CONTENT_TYPE_HEADER_KEY, APPLICATION_JSON_CONTENT_TYPE_VALUE)
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));
        //when
       Throwable exception = catchThrowable(() -> randomNumbersGenerable.generateSixRandomNumbers(25,1,99));

        //then
        assertThat(exception).isInstanceOf(ResponseStatusException.class);
        assertThat(exception.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");

    }
}
