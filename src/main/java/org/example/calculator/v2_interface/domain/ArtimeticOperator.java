package org.example.calculator.v2_interface.domain;

public interface ArtimeticOperator {
    boolean supports(String operator);

    int calculate(int operand1, int operand2);
}
