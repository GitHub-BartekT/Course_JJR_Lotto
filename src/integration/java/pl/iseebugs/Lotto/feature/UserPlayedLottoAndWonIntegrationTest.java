package pl.iseebugs.Lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.iseebugs.Lotto.BaseIntegrationTest;
import pl.iseebugs.Lotto.domain.numberGenerator.*;
import pl.iseebugs.Lotto.domain.numberReceiver.dto.InputNumberResultDto;
import pl.iseebugs.Lotto.domain.resultChecker.ResultCheckerFacade;
import pl.iseebugs.Lotto.domain.resultChecker.TicketResultNotFoundException;
import pl.iseebugs.Lotto.domain.resultChecker.dto.TicketResultDto;
import pl.iseebugs.Lotto.domain.resultChecker.dto.WinningTicketsDto;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    WinningNumbersFacade winningNumbersFacade;

    @Autowired
    ResultCheckerFacade resultCheckerFacade;

    @Test
    public void should_user_win_and_system_should_generate_winners() throws Exception {
        //  Step 1: external service returns 6 random numbers (1,2,3,4,5,6)


        //  given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                        """.trim()
                                )));


        //  Step 2: system generated winning numbers for draw date: 19.11.2022 12:00
        //  given
        LocalDateTime drawDate = LocalDateTime.of(2022,11, 19, 12, 0, 0);
        //  when && then
        await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> {
                    try {
                        log.info("check option");
                        return !winningNumbersFacade.getWinningNumbersByDate(drawDate).winningNumbers().isEmpty();
                    } catch (Exception e) {
                        log.info("false");
                        return false;
                    }
                }
        );


        //  Step 3: user made POST /inputNumbers with 6 numbers (1,2,3,4,5,6) at 16-11-2022 10:00
        //          and system returned OK(200) with
        //          message: "success" and
        //          Ticket (DrawDate:19.11.2022 12:00(Saturday), TicketId: sampleTicketId)
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                                {   
                                "inputNumbers":[1,2,3,4,5,6]
                                }
                                """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        //then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        InputNumberResultDto inputNumbersRequestDto = objectMapper.readValue(json, InputNumberResultDto.class);
        String ticketId = inputNumbersRequestDto.ticketId();
        assertAll(
                () -> assertThat(inputNumbersRequestDto.drawDate()).isEqualTo(drawDate),
                () -> assertThat(ticketId).isNotNull(),
                () -> assertThat(inputNumbersRequestDto.message()).isEqualTo("success")
        );


        //  Step 4: user made GET /result/notExistingId and system returned 404(NOT_FOUND)
        //          and body with ("message":"Not found for id: notExistingId" and "status":"NOT_FOUND"
        // when
        ResultActions performGetResultsWithNotExistingId = mockMvc.perform(get("/results/notExistingId"));
        // then
        performGetResultsWithNotExistingId.andExpect(status().isNotFound())
                .andExpect(content().json(
                        """
                                {"message": "Not found for id: notExistingId",
                                "status": "NOT_FOUND"}
                                """.trim()
                ));


        //  Step 5: 3 days and 55 minutes passed, and it is 5 minute before draw (19.11.2022 11:55)
        // given && when && then
        clock.plusDaysAndMinutes(3,55);


        //  Step 6: system generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00,
        //        and saved it with 6 hits
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(Duration.ofSeconds(1))
                        .until(
                                () -> {
                                    try {
                                        TicketResultDto result = resultCheckerFacade.findTicketById(ticketId);
                                        return !result.numbers().isEmpty();
                                    } catch (TicketResultNotFoundException e){
                                        return false;
                                    }
                                }
                        );


        //  Step 7: 6 minutes passed and it is 1 minute after the draw (19.11.2022 12:01)
        // given && when && then

        clock.plusMinutes(6);


        //  Step 8: user made GET/results/sampleTicketId and system returned 200(OK)
    }
}
