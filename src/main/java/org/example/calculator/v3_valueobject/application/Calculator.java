package org.example.calculator.v3_valueobject.application;

import org.example.calculator.v3_valueobject.domain.vo.PositiveNumber;
import org.example.calculator.v3_valueobject.domain.operator.*;

import java.util.List;

public class Calculator {
    private static final List<PositiveNumArtimeticOperator> operators = List.of(
            new PositiveNumAddtionOperator(),
            new PositiveNumSubtractionOperator(),
            new PositiveNumMultiplicationOperator(),
            new PositiveNumDivisionOperator()
    );

    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return operators.stream()
                .filter(op -> op.supports(operator))
                .map(op -> op.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아님"));
    }
}
