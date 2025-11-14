package org.example.calculator.v3_vo.domain.operator;

import org.example.calculator.v3_vo.domain.vo.PositiveNumber;

public class PositiveNumSubtractionOperator implements PositiveNumArtimeticOperator {
    @Override
    public boolean supports(String operator) {
        return "-".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt() - operand2.toInt();
    }
}
