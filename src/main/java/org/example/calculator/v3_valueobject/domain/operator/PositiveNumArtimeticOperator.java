package org.example.calculator.v3_valueobject.domain.operator;

import org.example.calculator.v3_valueobject.domain.vo.PositiveNumber;

public interface PositiveNumArtimeticOperator {
    boolean supports(String operator);

    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
