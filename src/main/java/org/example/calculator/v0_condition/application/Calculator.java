package org.example.calculator.v0_condition.application;

import org.example.calculator.v0_condition.domain.ArithmeticCalculate;

public class Calculator {

    public static int calculate(int op1, String or, int op2) {
        return ArithmeticCalculate.calculate(op1, or, op2);
    }
}
