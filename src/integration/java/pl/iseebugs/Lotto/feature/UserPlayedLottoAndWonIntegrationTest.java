package pl.iseebugs.Lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.iseebugs.Lotto.BaseIntegrationTest;
import pl.iseebugs.Lotto.domain.numberGenerator.RandomNumbersGenerable;
import pl.iseebugs.Lotto.domain.numberGenerator.SixRandomNumbersDto;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    RandomNumbersGenerable randomNumbersGenerable;

    @Test
    public void should_user_win_and_system_should_generate_winners(){
        //  Step 1: external service returns 6 random numbers (1,2,3,4,5,6)
        // given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                        """.trim()
                                )));
        // when
        SixRandomNumbersDto sixRandomNumbersDto = randomNumbersGenerable.generateSixRandomNumbers();

        // then
        //  Step 2: system fetched winning numbers for draw date: 17.02.2024 12:00
        //  Step 3: user made POST /inputNumbers with 6 numbers (1,2,3,4,5,6) at 15-02-2024 10:00 and system returned OK(200) with message: "success" and Ticket (DrawDate:17.02.2024 12:00(Saturday), TicketId: sampleTicketId)
        //  Step 4: 2 days, 2 hours and 1 minute passed, and it is 1 minute after the draw date (17.02.2024 12:01)
        //  Step 5: system generated result for TicketId: sampleTicketId with draw date 17.02.2024 12:00, and saved it with 6 hits
        //  Step 6: 3 hours passed, and it is 1 minute after announcement time (17.02.2024 15:01)
        //  Step 7: user made GET/results/sampleTicketId and system returned 200(OK)
    }
}
