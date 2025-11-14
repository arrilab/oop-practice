package org.example.calculator.v3_vo.domain.operator;

import org.example.calculator.v3_vo.domain.vo.PositiveNumber;

public interface PositiveNumArtimeticOperator {
    boolean supports(String operator);

    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
