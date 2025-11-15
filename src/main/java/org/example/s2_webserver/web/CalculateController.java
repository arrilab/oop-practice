package org.example.s2_webserver.web;

import org.example.s1_oop.s0_calculator.v3_valueobject.application.Calculator;
import org.example.s1_oop.s0_calculator.v3_valueobject.domain.vo.PositiveNumber;
import org.example.s2_webserver.http.request.HttpRequest;
import org.example.s2_webserver.http.response.HttpResponse;

public class CalculateController implements Controller {

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        // 수신
        var queryParams = req.getQueryParams();

        // 연산 입력
        int operand1 = Integer.parseInt(queryParams.getValue("operand1"));
        String operator = queryParams.getValue("operator");
        int operand2 = Integer.parseInt(queryParams.getValue("operand2"));

        // 연산 결과
        int result = Calculator.calculate(
                new PositiveNumber(operand1),
                operator,
                new PositiveNumber(operand2)
        );

        // 전송
        byte[] body = String.valueOf(result).getBytes();
        res.response200Header("application/json", body.length);
        res.responseBody(body);
    }
}
