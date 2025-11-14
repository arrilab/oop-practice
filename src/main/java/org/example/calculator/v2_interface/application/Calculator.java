package org.example.calculator.v2_interface.application;

import org.example.calculator.v2_interface.domain.*;

import java.util.List;

public class Calculator {
    private static final List<ArtimeticOperator> operators = List.of(
            new AddtionOperator(),
            new SubtractionOperator(),
            new MultiplicationOperator(),
            new DivisionOperator()
    );

    public static int calculate(int operand1, String operator, int operand2) {
        return operators.stream()
                .filter(op -> op.supports(operator))
                .map(op -> op.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아님"));
    }
}
