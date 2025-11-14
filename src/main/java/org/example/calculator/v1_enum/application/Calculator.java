package org.example.calculator.v1_enum.application;

import org.example.calculator.v1_enum.domain.ArithmeticCalculateV0;

public class Calculator {
    public static int calculate(int operand1, String operator, int operand2) {
        return ArithmeticCalculateV0.calculate(operand1, operator, operand2);
    }
}
