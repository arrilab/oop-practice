package org.example.calculator.v2_interface.domain;

public class DivisionOperator implements ArtimeticOperator {
    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(int operand1, int operand2) {
        if (operand2 == 0) {
            throw new IllegalArgumentException("0으로는 나눌 수 없음");
        }
        return operand1 / operand2;
    }
}
