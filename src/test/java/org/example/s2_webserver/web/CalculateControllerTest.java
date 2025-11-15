package org.example.s2_webserver.web;

import org.example.s2_webserver.http.request.HttpRequest;
import org.example.s2_webserver.http.request.QueryParams;
import org.example.s2_webserver.http.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateControllerTest {

    @Mock
    HttpRequest request;

    @Mock
    HttpResponse response;

    @InjectMocks
    CalculateController controller;

    @Test
    @DisplayName("쿼리 파라미터로 계산하고 200 응답을 쓴다")
    void handle_shouldCalculateAndWriteResponse() {
        // given
        // operand1=11, operator=*, operand2=55 → 11 * 55 = 605
        var queryParams = new QueryParams("operand1=11&operator=*&operand2=55");
        when(request.getQueryParams()).thenReturn(queryParams);

        // when
        controller.handle(request, response);

        // then
        byte[] expectedBody = "605".getBytes(); // Calculator 결과

        // 헤더와 바디가 올바르게 쓰였는지 검증
        verify(response).response200Header("application/json", expectedBody.length);
        verify(response).responseBody(expectedBody);
    }
}
